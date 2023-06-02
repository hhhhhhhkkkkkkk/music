package com.music.service;

import com.music.domain.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lsk
 * @since 2023-05-23
 */
public interface IAdminService {
    public Admin login(Admin admin);
}
