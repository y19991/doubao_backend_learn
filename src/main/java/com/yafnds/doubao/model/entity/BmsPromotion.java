package com.yafnds.doubao.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: doubao
 * @description: 推广栏
 * @author: y19991
 * @create: 2021-03-02 16:20
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("bms_promotion")
public class BmsPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 广告标题
     */
    @TableField("title")
    private String title;

    /**
     * 广告链接
     */
    @TableField("link")
    private String link;

    /**
     * 说明
     */
    @TableField("description")
    private String description;
}
