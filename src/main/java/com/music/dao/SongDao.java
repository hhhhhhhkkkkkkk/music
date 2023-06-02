package com.music.dao;

import com.music.domain.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lsk
 * @since 2023-05-23
 */
@Mapper
public interface SongDao extends BaseMapper<Song> {

}
