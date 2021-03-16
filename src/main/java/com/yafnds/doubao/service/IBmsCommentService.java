package com.yafnds.doubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yafnds.doubao.model.entity.BmsComment;
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

}
