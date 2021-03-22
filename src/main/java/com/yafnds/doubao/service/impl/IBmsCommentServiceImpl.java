package com.yafnds.doubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yafnds.doubao.common.exception.ApiAsserts;
import com.yafnds.doubao.mapper.BmsCommentMapper;
import com.yafnds.doubao.model.dto.CommentDTO;
import com.yafnds.doubao.model.entity.BmsComment;
import com.yafnds.doubao.model.entity.UmsUser;
import com.yafnds.doubao.model.vo.CommentVO;
import com.yafnds.doubao.service.IBmsCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author y19991
 * @version V1.0
 * @Package com.yafnds.doubao.service.impl
 * @date 2021/3/1621:36
 * @Description:
 */

@Slf4j
@Service
public class IBmsCommentServiceImpl extends ServiceImpl<BmsCommentMapper, BmsComment>
        implements IBmsCommentService {

    /**
     * 根据话题Id获取对应评论
     * @param topicId 话题id
     * @return {@link List} 评论列表
     */
    @Override
    public List<CommentVO> getCommentsByTopicID(String topicId) {

        List<CommentVO> comments = new ArrayList<>();

        try {

            comments = this.baseMapper.getCommentVOByTopicId(topicId);
            if (ObjectUtils.isEmpty(comments)) {
                ApiAsserts.fail("评论为空");
            }

        } catch (Exception e) {
            log.info("获取评论失败: "+e);
        } finally {
            return comments;
        }
    }

    /**
     * 创建一条新的评论
     * @param dto 前端传入的评论内容
     * @param user 创建评论的用户
     * @return 新的评论对象
     */
    @Override
    public BmsComment create(CommentDTO dto, UmsUser user) {

        BmsComment comment = BmsComment.builder()
                .userId(user.getId())
                .content(dto.getContent())
                .topicId(dto.getTopic_id())
                .createTime(new Date())
                .build();
        this.baseMapper.insert(comment);

        return comment;
    }
}
