package com.yafnds.doubao.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yafnds.doubao.mapper.BmsPostMapper;
import com.yafnds.doubao.mapper.BmsTagMapper;
import com.yafnds.doubao.model.entity.BmsPost;
import com.yafnds.doubao.model.entity.BmsTag;
import com.yafnds.doubao.model.entity.BmsTopicTag;
import com.yafnds.doubao.model.vo.PostVO;
import com.yafnds.doubao.service.IBmsPostService;
import com.yafnds.doubao.service.IBmsTopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: doubao
 * @description: 话题
 * @author: y19991
 * @create: 2021-03-09 11:08
 **/

@Service
public class IBmsPostServiceImpl extends ServiceImpl<BmsPostMapper, BmsPost> implements IBmsPostService {

    @Resource
    private BmsTagMapper bmsTagMapper;

    @Autowired
    private IBmsTopicTagService bmsTopicTagServicer;

    /**
     * 获取首页话题列表
     * @param page
     * @param tab
     * @return
     */
    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {

        // 查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);

        // 查询话题的标签
        iPage.getRecords().forEach(topic -> {
            // 根据 TopicId 查出关联的标签列表
            List<BmsTopicTag> topicTags = bmsTopicTagServicer.selectByTopicId(topic.getId());
            if (!topicTags.isEmpty()) {
                // 根据查出来的 TagId 按顺序把 tag表里的id转成列表
                List<String> tagIds = topicTags.stream().map(BmsTopicTag::getTagId).collect(Collectors.toList());
                System.out.println(tagIds);
                // 根据 TagId 批量查询 Tag
                List<BmsTag> tags = bmsTagMapper.selectBatchIds(tagIds);
                System.out.println(tags);
                topic.setTags(tags);
            }
        });
        return iPage;
    }
}
