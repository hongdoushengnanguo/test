package org.jeecg.modules.test.balance.service;

import org.jeecg.modules.test.balance.entity.Balance;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * @Description: 存钱余额
 * @Author: jeecg-boot
 * @Date:   2019-11-21
 * @Version: V1.0
 */
public interface IBalanceService extends IService<Balance> {
     Boolean chanceMoney (Balance blance);
     Balance findBalanceNumber(Balance blance);


}
