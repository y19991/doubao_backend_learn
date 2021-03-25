package com.yafnds.doubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafnds.doubao.model.entity.BmsComment;
import com.yafnds.doubao.model.vo.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author y19991
 * @version V1.0
 * @Package com.yafnds.doubao.mapper
 * @date 2021/3/1621:36
 * @Description:
 */

@Repository
public interface BmsCommentMapper extends BaseMapper<BmsComment> {

    /**
     * 根据话题id获得对应的 CommentVO 对象
     * @param topicId 话题id
     * @return {@link CommentVO}
     */
    List<CommentVO> getCommentVOByTopicId(@Param("topicId") String topicId);

}
