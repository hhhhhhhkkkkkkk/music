package com.music.service.impl;

import com.music.domain.Song;
import com.music.dao.SongDao;
import com.music.service.ISongService;
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
public class SongServiceImpl implements ISongService {

    @Autowired
    private SongDao songDao;

    @Override
    public Song getById(int songid) {
        return songDao.selectById(songid);
    }


    @Override
    public List<Song> getAll() {
        return songDao.selectList(null);
    }

    @Override
    public void deleteById(int songid) {
        songDao.deleteById(songid);
    }

    @Override
    public void save(Song song) {
        songDao.insert(song);
    }

    @Override
    public void updateById(Song song) {
        Song song1 = songDao.selectById(song.getMusicid());
        if(song.getMusicpic() == null){
            song.setMusicpic(song1.getMusicpic());
        }
        if(song.getMusicfile() == null){
            song.setMusicfile(song1.getMusicfile());
        }
        if(song.getMusicword() == null){
            song.setMusicword(song1.getMusicword());
        }
        songDao.updateById(song);
    }
}
