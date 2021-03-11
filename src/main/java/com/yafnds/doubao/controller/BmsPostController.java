package com.yafnds.doubao.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yafnds.doubao.common.api.ApiResult;
import com.yafnds.doubao.model.dto.CreateTopicDTO;
import com.yafnds.doubao.model.entity.BmsPost;
import com.yafnds.doubao.model.entity.UmsUser;
import com.yafnds.doubao.model.vo.PostVO;
import com.yafnds.doubao.service.IBmsPostService;
import com.yafnds.doubao.service.IUmsUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.yafnds.doubao.jwt.JwtUtil.USER_NAME;

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
    @Resource
    private IUmsUserService umsUserService;

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

    /**
     * 创建新话题
     * @param username 创建人
     * @param dto 前端传回的参数
     * @return 保存的话题与成功提示
     */
    @PostMapping("/create")
    public ApiResult<BmsPost> create(@RequestHeader(USER_NAME) String username, @RequestBody CreateTopicDTO dto) {

        UmsUser user = umsUserService.getUserByUsername(username);
        BmsPost topic = bmsPostService.create(dto, user);
        return ApiResult.success(topic);
    }
}
