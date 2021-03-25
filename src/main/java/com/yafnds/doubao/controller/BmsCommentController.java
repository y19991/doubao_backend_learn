package com.yafnds.doubao.controller;

import com.yafnds.doubao.common.api.ApiResult;
import com.yafnds.doubao.model.dto.CommentDTO;
import com.yafnds.doubao.model.entity.BmsComment;
import com.yafnds.doubao.model.entity.UmsUser;
import com.yafnds.doubao.model.vo.CommentVO;
import com.yafnds.doubao.service.IBmsCommentService;
import com.yafnds.doubao.service.IUmsUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.yafnds.doubao.jwt.JwtUtil.USER_NAME;

/**
 * @author y19991
 * @version V1.0
 * @Package com.yafnds.doubao.controller
 * @date 2021/3/1621:23
 * @Description:
 */

@RestController
@RequestMapping("/comment")
public class BmsCommentController extends BaseController{

    @Resource
    private IBmsCommentService bmsCommentService;
    @Resource
    private IUmsUserService umsUserService;

    @GetMapping("/get_comments")
    public ApiResult<List<CommentVO>> getCommentsByTopicID(@RequestParam(value = "topicid", defaultValue = "1") String topicId) {

        List<CommentVO> comments = bmsCommentService.getCommentsByTopicID(topicId);
        return ApiResult.success(comments);
    }

    /**
     * 添加评论
     * @param userName 创建评论的用户名
     * @param dto 传入的评论对象
     * @return 成功提示和添加的评论对象
     */
    @PostMapping("/add_comment")
    public ApiResult<BmsComment> addComment(@RequestHeader(USER_NAME) String userName, @RequestBody CommentDTO dto) {

        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsComment bmsComment = bmsCommentService.create(dto, user);

        return ApiResult.success(bmsComment);
    }
}
