package com.yafnds.doubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yafnds.doubao.model.entity.BmsTag;

import java.util.List;

/**
 * @program: doubao
 * @description: 标签
 * @author: y19991
 * @create: 2021-03-11 16:21
 **/

public interface IBmsTagService extends IService<BmsTag> {

    /**
     * 保存多个标签
     */
    List<BmsTag> insertTags(List<String> tagNames);
}
