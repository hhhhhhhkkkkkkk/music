package com.music.service;

import com.music.domain.Singer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.domain.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lsk
 * @since 2023-05-23
 */
public interface ISingerService {
    public Singer getById(int singerid);
    public List<Singer> getAll();
    public void deleteById(int singerid);
    public void save(Singer singer);
    public void updateById(Singer singer);
}
