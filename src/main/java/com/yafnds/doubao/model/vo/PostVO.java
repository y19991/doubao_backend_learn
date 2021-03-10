package com.yafnds.doubao.model.vo;

import com.yafnds.doubao.model.entity.BmsTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @program: doubao
 * @description: 话题列表 传给前端的数据
 * @author: y19991
 * @create: 2021-03-08 17:27
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostVO implements Serializable {

    private static final long serialVersionUID = -261082150965211545L;

    /**
     * 文章ID
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String alias;

    /**
     * 账号
     */
    private String username;

    /**
     * 标题
     */
    private String title;

    /**
     * 评论统计
     */
    private Integer comments;

    /**
     * 置顶
     */
    private Boolean top;

    /**
     * 加精
     */
    private Boolean essence;

    /**
     * 收藏次数
     */
    private Integer collects;

    /**
     * 话题关联标签
     */
    private List<BmsTag> tags;

    /**
     * 浏览量
     */
    private Integer view;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;
}
