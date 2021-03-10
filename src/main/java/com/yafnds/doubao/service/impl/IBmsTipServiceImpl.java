package com.yafnds.doubao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yafnds.doubao.mapper.BmsTipMapper;
import com.yafnds.doubao.model.entity.BmsTip;
import com.yafnds.doubao.service.IBmsTipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: doubao
 * @description: 每日一句服务层实现类
 * @author: y19991
 * @create: 2021-03-02 14:42
 **/

@Slf4j
@Service
public class IBmsTipServiceImpl extends ServiceImpl<BmsTipMapper, BmsTip>
        implements IBmsTipService {

    @Override
    public BmsTip getRandomTip() {
        BmsTip todayTip= null;
        try {
            todayTip = this.baseMapper.getRandomTip();
        } catch (Exception e) {
            System.out.println(e);
            log.info("tip转化失败");
        }

        return todayTip;
    }
}
