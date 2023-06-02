package com.music.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "singerid", type = IdType.AUTO)
    private Integer singerid;

    private String singername;

    private byte[] singerpic;

    private String singerinfo;

    private String singersex;

    private String singerage;

    @TableLogic
    private Integer deleted;


}
