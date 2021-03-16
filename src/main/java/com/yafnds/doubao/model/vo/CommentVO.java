package com.yafnds.doubao.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @program: doubao
 * @description: 评论 向前端发送的数据
 * @author: y19991
 * @create: 2021-03-16 17:30
 **/

@Data
public class CommentVO {

    /** 主键 */
    private String id;

    /** 内容 */
    private String content;

    /** 话题id */
    private String topicId;

    /** 用户id */
    private String userId;

    /** 用户名 */
    private String username;

    /** 创建时间 */
    private Date createTime;
}
