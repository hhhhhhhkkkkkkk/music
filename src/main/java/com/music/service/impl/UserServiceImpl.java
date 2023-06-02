package com.music.service.impl;

import com.music.domain.User;
import com.music.dao.UserDao;
import com.music.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lsk
 * @since 2023-05-23
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getById(int userid) {
        return userDao.selectById(userid);
    }

    @Override
    public User login(User user) {
        User user1 = userDao.selectById(user);
        if(user1.getUserpasswd().equals(user.getUserpasswd())){
            return user1;
        }else {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        return userDao.selectList(null);
    }

    @Override
    public void deleteById(int userid) {
        userDao.deleteById(userid);
    }

    @Override
    public void save(User user) {
        userDao.insert(user);
    }

    @Override
    public void updateById(User user) {
        User user1 = userDao.selectById(user.getUserid());
        if(user.getUserpic()==null){
            user.setUserpic(user1.getUserpic());
        }
        userDao.updateById(user);
    }

}
