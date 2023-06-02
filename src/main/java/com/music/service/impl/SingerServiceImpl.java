package com.music.service.impl;

import com.music.domain.Singer;
import com.music.dao.SingerDao;
import com.music.service.ISingerService;
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
public class SingerServiceImpl implements ISingerService {

    @Autowired
    private SingerDao singerDao;
    @Override
    public Singer getById(int singerid) {
        return singerDao.selectById(singerid);
    }

    @Override
    public List<Singer> getAll() {
        return singerDao.selectList(null);
    }

    @Override
    public void deleteById(int singerid) {
        singerDao.deleteById(singerid);
    }

    @Override
    public void save(Singer singer) {
        singerDao.insert(singer);
    }

    @Override
    public void updateById(Singer singer) {
        Singer singer1 = singerDao.selectById(singer.getSingerid());
        if(singer.getSingerpic() == null){
            singer.setSingerpic(singer1.getSingerpic());
        }
        System.out.println(singer);
        singerDao.updateById(singer);
    }
}
