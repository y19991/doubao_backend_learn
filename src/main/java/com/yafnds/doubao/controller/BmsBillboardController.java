package com.yafnds.doubao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yafnds.doubao.common.api.ApiResult;
import com.yafnds.doubao.model.entity.BmsBillboard;
import com.yafnds.doubao.service.IBmsBillboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: doubao
 * @description: 公告栏
 * @author: y19991
 * @create: 2021-02-25 16:49
 **/

@RestController
@RequestMapping("/billboard")
public class BmsBillboardController extends BaseController{

    @Resource
    private IBmsBillboardService bmsBillboardService;

    /**
     * 查询公告栏
     * @return 最新的一条公告
     */
    @GetMapping("/show")
    public ApiResult<BmsBillboard> getNotices(){
        List<BmsBillboard> list = bmsBillboardService.list(new
                LambdaQueryWrapper<BmsBillboard>().eq(BmsBillboard::isShow, true));
        return ApiResult.success(list.get(list.size()-1));
    }

}
