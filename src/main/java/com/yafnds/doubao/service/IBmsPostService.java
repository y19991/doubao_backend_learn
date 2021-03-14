package com.yafnds.doubao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yafnds.doubao.model.dto.CreateTopicDTO;
import com.yafnds.doubao.model.entity.BmsPost;
import com.yafnds.doubao.model.entity.UmsUser;
import com.yafnds.doubao.model.vo.PostVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @program: doubao
 * @description: 话题
 * @author: y19991
 * @create: 2021-03-09 10:40
 **/

public interface IBmsPostService extends IService<BmsPost> {

    /**
     * 获取首页话题列表
     */
    Page<PostVO> getList(Page<PostVO> page, String tab);

    /**
     * 新增话题
     */
    BmsPost create(CreateTopicDTO dto, UmsUser user);

    /**
     * 查看话题详情
     */
    Map<String, Object> viewTopic(String topicId);

    /**
     * 获取详情页推荐
     * @param id 当前帖子的id
     */
    List<BmsPost> selectRecommend(@Param("id") String id);
}
