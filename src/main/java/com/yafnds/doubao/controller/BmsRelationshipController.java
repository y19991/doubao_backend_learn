package com.yafnds.doubao.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yafnds.doubao.common.api.ApiResult;
import com.yafnds.doubao.common.exception.ApiAsserts;
import com.yafnds.doubao.model.entity.BmsFollow;
import com.yafnds.doubao.model.entity.UmsUser;
import com.yafnds.doubao.service.IBmsFollowService;
import com.yafnds.doubao.service.IUmsUserService;
import org.apache.ibatis.annotations.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.yafnds.doubao.jwt.JwtUtil.USER_NAME;

/**
 * @author y19991
 * @version V1.0
 * @Package com.yafnds.doubao.controller
 * @date 2021/3/1321:42
 * @Description: 关注
 */
@RestController
@RequestMapping("/relationship")
public class BmsRelationshipController {

    @Autowired
    private IUmsUserService umsUserService;
    @Autowired
    private IBmsFollowService bmsFollowService;

    /**
     * 关注用户
     *  思路：
     *      1. 判断是否已经关注
     *          1.1 是：返回错误提示
     *      2. 判断被关注者是否等于关注者
     *          2.1 是：返回失败提示
     *      3. 执行关注。在关注表保存 关注者 与 被关注者 的ID
     *
     * @param username 执行关注的人，即当前用户
     * @param parentId 被关注者
     * @return 成功提示
     */
    @GetMapping("/subscribe/{userId}")
    public ApiResult<Object> handleFollow(@RequestHeader(USER_NAME) String username
            , @PathVariable("userId") String parentId) {

        UmsUser user = umsUserService.getUserByUsername(username);
        Boolean hasFollow = this.hasFollow(username, parentId);

        // 1.1 是：返回错误提示
        if(hasFollow) {
            ApiAsserts.fail("已关注");
        }

        // 2. 判断被关注者是否等于关注者
        if (parentId.equals(user.getId())) {
            ApiAsserts.fail("您脸皮太厚了，怎么可以关注自己呢");
        }

        // 3. 执行关注。在关注表保存 关注者 与 被关注者 的ID
        BmsFollow bmsFollow = BmsFollow.builder()
                .followerId(user.getId())
                .parentId(parentId)
                .build();
        bmsFollowService.save(bmsFollow);

        return ApiResult.success(null ,"已关注");
    }

    /**
     * 取消关注
     *  思路：
     *      1. 判断当前用户是否关注 被取消关注者
     *      2. 否：弹出提示
     *      3. 是：执行取关操作
     * @param username 取消关注的执行人ID，即当前用户
     * @param parentId 被取消关注者
     * @return 成功提示
     */
    @GetMapping("/unsubscribe/{userId}")
    public ApiResult<Object> handleUnFollow(@RequestHeader(USER_NAME) String username
            , @PathVariable("userId") String parentId) {

        UmsUser user = umsUserService.getUserByUsername(username);
        Boolean hasFollow = this.hasFollow(username, parentId);

        if (!hasFollow) {
            ApiAsserts.fail("未关注");
        }

        // 3. 是：执行取关操作
        bmsFollowService.remove(
                new LambdaQueryWrapper<BmsFollow>()
                        .eq(BmsFollow::getFollowerId, user.getId())
                        .eq(BmsFollow::getParentId, parentId)
        );

        return ApiResult.success(null ,"取关成功");
    }

    /**
     * 判断是否关注该作者
     * @param username 当前用户ID
     * @param topicUserId 话题作者ID
     * @return 判断结果 Map
     */
    @GetMapping("/validate/{topicUserId}")
    public ApiResult<Map<String, Object>> isFollow(@RequestHeader(USER_NAME) String username
            , @PathVariable("topicUserId") String topicUserId) {

        Boolean hasFollow = this.hasFollow(username, topicUserId);
        Map<String, Object> map = new HashMap<>(16);

        map.put("hasFollow", hasFollow);

        return ApiResult.success(map);
    }

    /**
     * 判断是否已关注
     * @param username 用户
     * @param parentId 被关注者Id
     * @return 当 username 为空 或 无法根据 username 和 parentId 在 bms_follow 表中查出值时，返回 false
     */
    private Boolean hasFollow(String username, String parentId) {

        UmsUser user = umsUserService.getUserByUsername(username);

        // 判断是否已经关注
        if (!ObjectUtils.isEmpty(username)) {

            // where follower_id = user.getId() and parent_id = parentId
            BmsFollow getFollowOne = bmsFollowService.getOne(
                    new LambdaQueryWrapper<BmsFollow>()
                            .eq(BmsFollow::getFollowerId, user.getId())
                            .eq(BmsFollow::getParentId, parentId)
            );

            if (!ObjectUtils.isEmpty(getFollowOne)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

}
