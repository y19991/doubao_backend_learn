package com.yafnds.doubao.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author y19991
 * @version V1.0
 * @Package com.yafnds.doubao.model.entity
 * @date 2021/3/1321:31
 * @Description: 关注
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("bms_follow")
public class BmsFollow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 被关注人ID
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 关注人ID
     */
    @TableField("follower_id")
    private String followerId;

}
