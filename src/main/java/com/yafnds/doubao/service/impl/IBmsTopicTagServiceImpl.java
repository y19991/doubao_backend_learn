package com.yafnds.doubao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yafnds.doubao.mapper.BmsTopicTagMapper;
import com.yafnds.doubao.model.entity.BmsTopicTag;
import com.yafnds.doubao.service.IBmsTopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: doubao
 * @description: 话题 标签 中间表
 * @author: y19991
 * @create: 2021-03-09 11:10
 **/

@Service
public class IBmsTopicTagServiceImpl extends ServiceImpl<BmsTopicTagMapper, BmsTopicTag>
        implements IBmsTopicTagService {

    /**
     * 获取话题与标签的关联记录
     */
    @Override
    public List<BmsTopicTag> selectByTopicId(String topicId) {
        QueryWrapper<BmsTopicTag> wrapper = new QueryWrapper<>();
        // where条件：表中的 TopicId = 传入的 topicId
        wrapper.lambda().eq(BmsTopicTag::getTopicId, topicId);
        // 执行sql查询列表
        return this.baseMapper.selectList(wrapper);
    }
}
