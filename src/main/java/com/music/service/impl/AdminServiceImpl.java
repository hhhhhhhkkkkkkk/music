package com.music.service.impl;

import com.music.domain.Admin;
import com.music.dao.AdminDao;
import com.music.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lsk
 * @since 2023-05-23
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminDao adminDao;
    @Override
    public Admin login(Admin admin) {
        Admin admin1 = adminDao.selectById(admin);
        if(admin1.getAdminpasswd().equals(admin.getAdminpasswd())){
            return admin1;
        }else {
            return null;
        }
    }
}
