package com.yafnds.doubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yafnds.doubao.mapper.BmsPromotionMapper;
import com.yafnds.doubao.model.entity.BmsPromotion;
import com.yafnds.doubao.service.IBmsPromotionService;
import org.springframework.stereotype.Service;

/**
 * @program: doubao
 * @description: 推广栏
 * @author: y19991
 * @create: 2021-03-02 16:42
 **/

@Service
public class IBmsPromotionServiceImpl extends ServiceImpl<BmsPromotionMapper, BmsPromotion>
        implements IBmsPromotionService {
}
