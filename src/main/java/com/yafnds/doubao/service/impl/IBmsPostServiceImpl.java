package com.yafnds.doubao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.yafnds.doubao.model.vo.ProfileVO;
import com.yafnds.doubao.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
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
    /**
     * Lazy注解：Spring在启动的时候延迟加载bean，在调用该bean的时候再去初始化
     */
    @Lazy
    @Autowired
    private IBmsTagService bmsTagService;
    @Lazy
    @Autowired
    private IUmsUserService umsUserService;


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
                // 根据 TagId 批量查询 Tag
                List<BmsTag> tags = bmsTagMapper.selectBatchIds(tagIds);

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

    /**
     * 查询话题详情
     *  思路：
     *      1. 查数据库，判断话题是否存在
     *          1.1 不存在：报错
     *          1.2 存在：话题浏览量+1
     *      2. 处理内容，如：将emoji表情转码等
     *      3. 处理标签
     *          3.1 根据 第一步查出的TopicId 从 bms_post_tag表 查出 Tag 对象
     *          3.2 根据 中间表的TagId 查出 对应的Tag对象
     *      4. 获取作者对象
     *      5. 将 话题内容、标签对象、作者对象 放入Map，并返回方法调用处
     *
     * @param topicId 话题id
     * @return
     */
    @Override
    public Map<String, Object> viewTopic(String topicId) {

        Map<String, Object> map = new HashMap<>(16);
        Set<String> tagIds = new HashSet<>();

        // 1. 查数据库，判断话题是否存在
        BmsPost topic = this.baseMapper.selectById(topicId);
        Assert.notNull(topic, "当前话题不存在，或已被作者删除");

        // 话题浏览量+1
        topic.setView(topic.getView() + 1);
        this.baseMapper.updateById(topic);

        /*
            2. 处理内容
            EmojiParser.parseToUnicode()：用unicode替换表情符号的别名和html表示
         */
        topic.setContent(EmojiParser.parseToUnicode(topic.getContent()));
        // 把处理好的话题封装到Map中
        map.put("topic", topic);

        // 3. 处理标签
        QueryWrapper<BmsTopicTag> wrapper = new QueryWrapper();
        wrapper.lambda().eq(BmsTopicTag::getTopicId, topic.getId());

        // 从 bms_post_tag表 中循环获取 tagId
        for (BmsTopicTag bmsTopicTag : bmsTopicTagServicer.list(wrapper)) {
            tagIds.add(bmsTopicTag.getTagId());
        }

        // 3.2 根据 中间表的TagId 查出 对应的Tag对象
        List<BmsTag> tags = bmsTagService.listByIds(tagIds);
        // 把处理好的Tag对象放入Map中
        map.put("tags", tags);

        // 4. 获取作者对象
        ProfileVO userProfile = umsUserService.getUserProfile(topic.getUserId());
        map.put("user", userProfile);

        return map;
    }

    /**
     * 获取详情页推荐
     * @param id 当前帖子的id
     * @return 帖子列表 长度：10
     */
    @Override
    public List<BmsPost> selectRecommend(String id) {
        return this.baseMapper.selectRandomTenDifferentFromNow(id);
    }
}
