package com.music.service;

import com.music.domain.Song;
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
public interface ISongService {
    public Song getById(int songid);
    public List<Song> getAll();
    public void deleteById(int songid);
    public void save(Song song);
    public void updateById(Song song);
}
