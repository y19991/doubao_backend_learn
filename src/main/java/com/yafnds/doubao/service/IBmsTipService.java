package com.yafnds.doubao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yafnds.doubao.model.entity.BmsTip;

/**
 * @program: doubao
 * @description: 每日一句
 * @author: y19991
 * @create: 2021-03-02 14:39
 **/

public interface IBmsTipService extends IService<BmsTip> {
    /**
     * 查询一条随机的名言
     * @return BmsTip
     */
    BmsTip getRandomTip();
}
