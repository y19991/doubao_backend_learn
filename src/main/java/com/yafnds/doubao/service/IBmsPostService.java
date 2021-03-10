package com.yafnds.doubao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yafnds.doubao.model.entity.BmsPost;
import com.yafnds.doubao.model.vo.PostVO;

/**
 * @program: doubao
 * @description: 话题
 * @author: y19991
 * @create: 2021-03-09 10:40
 **/

public interface IBmsPostService extends IService<BmsPost> {

    /**
     * 获取首页话题列表
     * @param page
     * @param tab
     * @return
     */
    Page<PostVO> getList(Page<PostVO> page, String tab);
}
