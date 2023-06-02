package com.music.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Time;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lsk
 * @since 2023-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "musicid", type = IdType.AUTO)
    private Integer musicid;

    private String musicname;

    private String singerid;

    private byte[] musicpic;

    private byte[] musicfile;

    private byte[] musicword;

    private Time musictime;


}
