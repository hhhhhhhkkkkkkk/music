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
public class Songlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "listid", type = IdType.AUTO)
    private Integer listid;

    private String listname;

    private byte[] listpic;

    private String listinfo;

    @TableLogic
    private Integer deleted;


}
