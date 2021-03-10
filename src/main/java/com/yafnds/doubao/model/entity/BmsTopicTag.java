package com.yafnds.doubao.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: doubao
 * @description: 话题和标签的中间关联表
 * @author: y19991
 * @create: 2021-03-08 17:20
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("bms_post_tag")
@Accessors(chain = true)
public class BmsTopicTag implements Serializable {

    private static final long serialVersionUID = -5028599844989220715L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签表的id
     */
    @TableField("tag_id")
    private String tagId;

    /**
     * 话题（post）表的id
     */
    @TableField("topic_id")
    private String topicId;
}
