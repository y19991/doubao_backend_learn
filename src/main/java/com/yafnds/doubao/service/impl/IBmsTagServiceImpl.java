package com.yafnds.doubao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yafnds.doubao.mapper.BmsTagMapper;
import com.yafnds.doubao.model.entity.BmsTag;
import com.yafnds.doubao.service.IBmsTagService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: doubao
 * @description: 标签
 * @author: y19991
 * @create: 2021-03-11 16:25
 **/

@Service
public class IBmsTagServiceImpl extends ServiceImpl<BmsTagMapper, BmsTag> implements IBmsTagService {

    /**
     * 保存多个标签
     *  思路：
     *      1. 遍历传进来的标签列表
     *      2. 去库里查是否有相同的标签
     *          2.1 没有：新增标签
     *          2.2 有：标签所属话题数+1
     *      3. 保存至新的标签列表，并 return
     * @param tagNames List<String> 标签名称
     * @return 保存的标签列表
     */
    @Override
    public List<BmsTag> insertTags(List<String> tagNames) {
        List<BmsTag> tagList = new ArrayList<>();
        
        // 1. 遍历传进来的标签列表
        for (String tagName : tagNames) {
            // 2. 去库里查是否有相同的标签
            BmsTag newTag = this.baseMapper.selectOne(new LambdaQueryWrapper<BmsTag>().eq(BmsTag::getName, tagName));
            if (!ObjectUtils.isEmpty(newTag)) {
                // 新增标签
                newTag = BmsTag.builder().name(tagName).build();
                this.baseMapper.insert(newTag);
            } else {
                // 标签所属话题数+1
                newTag.setTopicCount(newTag.getTopicCount() + 1);
                this.baseMapper.updateById(newTag);
            }
            tagList.add(newTag);

        }

        return tagList;
    }
}
