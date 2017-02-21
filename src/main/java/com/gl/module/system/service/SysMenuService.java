package com.gl.module.system.service;

import com.gl.module.system.domain.SysMenu;
import com.gl.module.system.repository.SysMenuRepository;
import com.gl.module.system.vo.SysMenuQueryParam;
import com.gl.module.system.vo.SysMenuQueryParam.Type;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
            SysMenu condition = new SysMenu();
            if (Type.ALIAS == param.getType()) {
                alias = param.getQ();
            } else {
                condition.setName(param.getQ());
                SysMenu sysMenu = menuRepo.findOne(Example.of(condition));
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
