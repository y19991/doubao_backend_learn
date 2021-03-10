package com.yafnds.doubao.controller;

import com.yafnds.doubao.common.api.ApiResult;
import com.yafnds.doubao.model.entity.BmsPromotion;
import com.yafnds.doubao.service.IBmsPromotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: doubao
 * @description: 推广栏
 * @author: y19991
 * @create: 2021-03-02 16:44
 **/

@RestController
@RequestMapping("/promotion")
public class BmsPromotionController extends BaseController{

    @Resource
    private IBmsPromotionService bmsPromotionService;

    /**
     * 查询推广栏列表
     * @return List 所有推广数据
     */
    @GetMapping("/all")
    public ApiResult<List<BmsPromotion>> getPromotionList() {
        List<BmsPromotion> promotionList = bmsPromotionService.list();
        return ApiResult.success(promotionList);
    }
}
