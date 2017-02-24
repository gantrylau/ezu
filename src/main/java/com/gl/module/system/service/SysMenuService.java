package com.gl.module.system.service;

import com.gl.infra.util.Conditions;
import com.gl.module.system.domain.SysMenu;
import com.gl.module.system.repository.SysMenuRepository;
import com.gl.module.system.vo.SysMenuQueryParam;
import com.gl.module.system.vo.SysMenuQueryParam.Type;
import com.gl.module.system.vo.SysMenuVO;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gantrylau
 * @since 2017/2/21
 */
@Component
public class SysMenuService {

    @Autowired
    private SysMenuRepository menuRepo;

    public SysMenu save(SysMenuVO vo) {
        Preconditions.checkNotNull(vo, "传参有误！");
        Preconditions.checkNotNull(vo.getName(), "菜单名不可为空！");
        Preconditions.checkNotNull(vo.getAlias(), "别名不可为空！");
        List<SysMenu> list = menuRepo.findByNameOrAlias(vo.getName(), vo.getAlias());
        Preconditions.checkArgument(list.size() < 2 && (list.size() <= 0 || list.get(0).getId().equals(vo.getId())), "菜单名或别名已经被使用！");

        SysMenu menu;
        if (vo.getId() != null) {
            menu = menuRepo.findOne(vo.getId());
        } else {
            menu = new SysMenu();
        }
        menu.setName(vo.getName());
        menu.setAlias(vo.getAlias());
        menu.setParentAlias(vo.getParentAlias());
        menuRepo.save(menu);
        return menu;
    }

    public void remove(Long id) {
        SysMenu menu = menuRepo.findOne(id);
        if (menu != null) {
            long count = menuRepo.count((root, query, cb) -> cb.and(cb.equal(root.get("parentAlias"), menu.getAlias())));
            Conditions.checkArgument(count == 0, "请先删除【{}】下的所有子菜单！", menu.getName());
            menuRepo.delete(menu);
        }
    }

    public List<SysMenu> listAll(SysMenuQueryParam param) {
        return menuRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (param.getId() != null)
                predicates.add(cb.notEqual(root.get("id"), param.getId()));
            if (!predicates.isEmpty()) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
            return cb.conjunction();
        });
    }

    public Page<SysMenu> listByPage(SysMenuQueryParam param, Pageable pageable) {
        final String alias;
        if (StringUtils.isNotBlank(param.getQ())) {
            if (Type.ALIAS == param.getType()) {
                alias = param.getQ();
            } else {
                SysMenu sysMenu = menuRepo.findOne((root, query, cb) -> cb.and(cb.equal(root.get("name"), param.getQ())));
                alias = sysMenu.getAlias();
            }
        } else {
            alias = null;
        }
        return menuRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (StringUtils.isNotBlank(alias)) {
                List<Predicate> or = Lists.newArrayList();
                or.add(cb.equal(root.get("alias"), alias));
                or.add(cb.equal(root.get("parentAlias"), alias));
                predicates.add(cb.or(or.toArray(new Predicate[or.size()])));
            }
            if (!predicates.isEmpty()) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
            return cb.conjunction();
        }, pageable);
    }
}
