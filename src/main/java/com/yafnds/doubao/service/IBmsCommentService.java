package com.yafnds.doubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yafnds.doubao.model.dto.CommentDTO;
import com.yafnds.doubao.model.entity.BmsComment;
import com.yafnds.doubao.model.entity.UmsUser;
import com.yafnds.doubao.model.vo.CommentVO;

import java.util.List;

/**
 * @author y19991
 * @version V1.0
 * @Package com.yafnds.doubao.service
 * @date 2021/3/1621:31
 * @Description: 评论
 */

public interface IBmsCommentService extends IService<BmsComment> {

    /**
     * 根据话题id查找对应评论
     */
    List<CommentVO> getCommentsByTopicID(String topicId);

    /**
     * 创建一条新评论
     * @param dto 前端传入的评论内容
     * @param user 创建评论的用户
     */
    BmsComment create(CommentDTO dto, UmsUser user);

}
