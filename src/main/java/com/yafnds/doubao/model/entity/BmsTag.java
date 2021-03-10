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
 * @description: 标签
 * @author: y19991
 * @create: 2021-03-08 17:05
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("bms_tag")
public class BmsTag implements Serializable {

    private static final long serialVersionUID = 3257790983905872243L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 标签名称
     */
    @TableField("name")
    private String name;

    /**
     * 当前标签页下的话题个数
     */
    @Builder.Default
    @TableField("topic_count")
    private Integer topicCount = 1;


}
