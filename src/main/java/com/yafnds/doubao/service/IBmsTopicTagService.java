package com.yafnds.doubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yafnds.doubao.model.entity.BmsTopicTag;

import java.util.List;

/**
 * @program: doubao
 * @description: 话题关联标签的中间表
 * @author: y19991
 * @create: 2021-03-09 11:01
 **/

public interface IBmsTopicTagService extends IService<BmsTopicTag> {

    /**
     * 获取话题与标签的关联记录
     * @param topicId
     * @return 传入的 topicId 对应的记录列表
     */
    List<BmsTopicTag> selectByTopicId(String topicId);
}
