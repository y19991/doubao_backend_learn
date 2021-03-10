package com.yafnds.doubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafnds.doubao.model.entity.BmsTip;
import org.springframework.stereotype.Repository;

/**
 * @program: doubao
 * @description: 公告牌
 * @author: y19991
 * @create: 2021-02-25 10:55
 **/

@Repository
public interface BmsTipMapper extends BaseMapper<BmsTip> {
    /**
     * 查询一条随机的名言
     * @return BmsTip
     */
    BmsTip getRandomTip();
}
