package com.yafnds.doubao.controller;

import com.yafnds.doubao.common.api.ApiResult;
import com.yafnds.doubao.model.entity.BmsTip;
import com.yafnds.doubao.service.IBmsTipService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: doubao
 * @description: 每日一句
 * @author: y19991
 * @create: 2021-03-02 14:44
 **/

@RestController
@RequestMapping("/tip")
public class BmsTipController extends BaseController{

    @Resource
    private IBmsTipService bmsTipService;

    /**
     * 查询随机名言
     * @return 随机的一条名言
     */
    @GetMapping("/today")
    public ApiResult<BmsTip> getBmsTip() {
        // 调用方法获取到随机的名言，传入ApiResult.success() 返回成功值和成功信息
        BmsTip todayTip = bmsTipService.getRandomTip();
        return ApiResult.success(todayTip);
    }


}
