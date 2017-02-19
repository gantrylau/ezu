package com.gl.module.system.service;

import com.gl.module.system.domain.SysUser;
import com.gl.module.system.repository.SysUserRepository;
import com.gl.module.system.vo.SysUserForm;
import com.gl.module.system.vo.SysUserQueryParam;
import com.gl.module.system.vo.SysUserQueryParam.Type;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gantrylau
 * @since 2017/1/27
 */
@Component
public class SysUserService {

    @Autowired
    private SysUserRepository userRepo;

    public Page<SysUser> listByPage(SysUserQueryParam param, Pageable pageable) {

        return userRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(param.getQ())) {
                if (Type.USERNAME.equals(param.getType())) {
                    predicates.add(cb.equal(root.get("username"), param.getQ()));
                } else if (Type.EMAIL.equals(param.getType())) {
                    predicates.add(cb.equal(root.get("email"), param.getQ()));
                } else {
                    predicates.add(cb.equal(root.get("telephone"), param.getQ()));
                }
            }
            if (!predicates.isEmpty()) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
            return cb.conjunction();
        }, pageable);
    }

    /**
     * 新建或修改一个用户
     *
     * @param form
     * @return
     */
    public SysUser saveOrUpdate(SysUserForm form) {
        Date now = new Date();
        SysUser user;
        if (form.getId() != null) {
            user = userRepo.findOne(form.getId());
            user.setUsername(form.getUsername());
        } else {
            user = new SysUser();
            user.setUsername(form.getUsername());
            user.setCreateTime(now);
        }
        user.setModifyTime(now);
        user.setTelephone(form.getTelephone());
        user.setEmail(form.getEmail());
        user.setSex(form.getSex());
        userRepo.save(user);
        return user;
    }

    /**
     * 删除一个用户
     *
     * @param id
     * @return
     */
    public void remove(Long id) {
        userRepo.delete(id);
    }
}
