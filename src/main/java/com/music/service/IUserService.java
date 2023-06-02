package com.music.service;

import com.music.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lsk
 * @since 2023-05-23
 */
public interface IUserService {
    public User getById(int userid);
    public User login(User user);
    public List<User> getAll();
    public void deleteById(int userid);
    public void save(User user);
    public void updateById(User user);
}
