package com.yafnds.doubao.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: doubao
 * @description: 每日一句
 * @author: y19991
 * @create: 2021-03-02 09:13
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("bms_tip")
public class BmsTip implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 作者
     */
    @TableField("author")
    private String author;

    /**
     * 1：使用 0：过期
     */
    @Builder.Default
    @TableField("type")
    private boolean type = true;

}
