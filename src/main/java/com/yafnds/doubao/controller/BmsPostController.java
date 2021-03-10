package com.yafnds.doubao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yafnds.doubao.common.api.ApiResult;
import com.yafnds.doubao.model.vo.PostVO;
import com.yafnds.doubao.service.IBmsPostService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: doubao
 * @description: 话题列表控制
 * @author: y19991
 * @create: 2021-03-09 11:15
 **/

@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {

    @Resource
    private IBmsPostService bmsPostService;

    /**
     * 分页查询话题
     * @param tab
     * @param pageNo
     * @param pageSize
     * @return 成功提示和分页列表
     */
    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {

        // 获取分页后的列表
        Page<PostVO> list = bmsPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }
}
