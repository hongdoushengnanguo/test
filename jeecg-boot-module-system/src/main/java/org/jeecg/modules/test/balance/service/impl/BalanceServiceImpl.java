package org.jeecg.modules.test.balance.service.impl;

import org.jeecg.modules.test.balance.entity.Balance;
import org.jeecg.modules.test.balance.mapper.BalanceMapper;
import org.jeecg.modules.test.balance.service.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Description: 存钱余额
 * @Author: jeecg-boot
 * @Date:   2019-11-21
 * @Version: V1.0
 */
@Service
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance> implements IBalanceService {

    @Autowired
    private BalanceMapper balanceMapper;

    /**
     *
     * @param blance
     * @return Boolean
     * 判断取款金额是否大于余额.若大于余额返回false,反之为true
     */
    @Override
    public Boolean chanceMoney(Balance blance) {
        Boolean x=true;

        if (findBalanceNumber(blance).getSaveNumber().compareTo(blance.getSaveNumber())>= 0){
            x=true;
        }else{
            x=false;
        }
        return x;
    }

    /**
     *
     * @param blance
     * @return BigDecimal
     * 根据传入得id查找余额返回余额数量
     */
    @Override
    public Balance findBalanceNumber(Balance blance) {
        Balance news = balanceMapper.selectById(blance.getId());
        return news;
    }
}
