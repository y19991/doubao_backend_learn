package com.yafnds.doubao.controller;

import com.yafnds.doubao.common.api.ApiResult;
import com.yafnds.doubao.model.vo.CommentVO;
import com.yafnds.doubao.service.IBmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author y19991
 * @version V1.0
 * @Package com.yafnds.doubao.controller
 * @date 2021/3/1621:23
 * @Description:
 */

@Controller
@RequestMapping("/comment")
public class BmsCommentController extends BaseController{

    @Resource
    private IBmsCommentService bmsCommentService;

    @GetMapping("/get_comments")
    public ApiResult<List<CommentVO>> getCommentsByTopicID(@RequestParam(value = "topicid", defaultValue = "1") String topicId) {

        List<CommentVO> comments = bmsCommentService.getCommentsByTopicID(topicId);
        return ApiResult.success(comments);
    }
}
