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
 * @description: 话题
 * @author: y19991
 * @create: 2021-03-08 14:45
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("bms_post")
public class BmsPost implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    @TableField("title")
    private String title;

    /**
     * markdown
     */
    @NotBlank(message = "内容不能为空")
    @TableField("content")
    private String content;

    /**
     * 作者ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 评论数
     */
    @Builder.Default
    @TableField("comments")
    private Integer comments = 0;

    /**
     * 收藏数
     */
    @Builder.Default
    @TableField("collects")
    private Integer collects = 0;

    /**
     * 浏览数
     */
    @Builder.Default
    @TableField("view")
    private Integer view = 0;

    /**
     * 专栏ID，默认不分栏
     */
    @Builder.Default
    @TableField("section_id")
    private Integer sectionId = 0;

    /**
     * 置顶
     */
    @Builder.Default
    @TableField("top")
    private Boolean top = false;

    /**
     * 加精
     */
    @Builder.Default
    @TableField("essence")
    private Boolean essence = false;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time", fill = FieldFill.UPDATE)
    private Date updateTime;

}
