package com.yafnds.doubao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiParser;
import com.yafnds.doubao.mapper.BmsPostMapper;
import com.yafnds.doubao.mapper.BmsTagMapper;
import com.yafnds.doubao.mapper.UmsUserMapper;
import com.yafnds.doubao.model.dto.CreateTopicDTO;
import com.yafnds.doubao.model.entity.BmsPost;
import com.yafnds.doubao.model.entity.BmsTag;
import com.yafnds.doubao.model.entity.BmsTopicTag;
import com.yafnds.doubao.model.entity.UmsUser;
import com.yafnds.doubao.model.vo.PostVO;
import com.yafnds.doubao.service.IBmsPostService;
import com.yafnds.doubao.service.IBmsTagService;
import com.yafnds.doubao.service.IBmsTipService;
import com.yafnds.doubao.service.IBmsTopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
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
    @Resource
    private UmsUserMapper umsUserMapper;

    @Autowired
    private IBmsTopicTagService bmsTopicTagServicer;
    @Autowired
    private IBmsTagService bmsTagService;

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

    /**
     * 新增话题
     * @param dto 从前端接收的话题数据
     * @param user 创建话题的用户
     * @return 被创建的话题
     */
    @Override
    public BmsPost create(CreateTopicDTO dto, UmsUser user) {

        // 判断新增的话题是否与库中数据重复
        BmsPost topic = this.baseMapper.selectOne(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getTitle, dto.getTitle()));
        Assert.isNull(topic, "话题已存在，请修改");

        /*
            封装话题
            使用了 Lombok 的 @build 的构建方法
            EmojiParser.parseToAliases() : 将表情转换成对应别名字符
         */
        BmsPost newTopic = BmsPost.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .build();
        this.baseMapper.insert(newTopic);

        // 用户积分增加
        int newScore = user.getScore() + 1;
        umsUserMapper.updateById(user.setScore(newScore));

        // 标签的处理
        if (!ObjectUtils.isEmpty(dto.getTags())) {
            // 保存标签
            List<BmsTag> newTags = bmsTagService.insertTags(dto.getTags());
            // 处理中间表
            bmsTopicTagServicer.createTopicTag(newTopic.getId(), newTags);
        }

        return newTopic;
    }
}
