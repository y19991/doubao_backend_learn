package com.yafnds.doubao.model.vo;

import lombok.Data;

/**
 * @program: doubao
 * @description: 话题详情
 * @author: y19991
 * @create: 2021-03-12 16:09
 **/

@Data
public class ProfileVO {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String alias;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 关注数
     */
    private Integer followCount;

    /**
     * 粉丝数
     */
    private Integer followerCount;

    /**
     * 文章数
     */
    private Integer topicCount;

    /**
     * 专栏数
     */
    private Integer columns;

    /**
     * 评论数
     */
    private Integer commentCount;
}
