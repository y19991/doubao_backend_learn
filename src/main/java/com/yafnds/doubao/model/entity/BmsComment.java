package com.yafnds.doubao.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: doubao
 * @description: 用户评论
 * @author: y19991
 * @create: 2021-03-16 17:19
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("bms_comment")
public class BmsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /** 内容 */
    @NotBlank(message = "内容不能为空")
    @TableField("content")
    private String content;

    /** 作者ID */
    @TableField("user_id")
    private String userId;

    /** 话题ID */
    @TableField("topic_id")
    private String topicId;

    /** 创建时间 */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改时间 */
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date modifyTime;

}
