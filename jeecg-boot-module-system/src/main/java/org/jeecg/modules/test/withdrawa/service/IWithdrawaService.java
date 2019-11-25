package org.jeecg.modules.test.withdrawa.service;

import org.jeecg.modules.test.withdrawa.entity.Withdrawa;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 取钱记录
 * @Author: jeecg-boot
 * @Date:   2019-11-21
 * @Version: V1.0
 */
public interface IWithdrawaService extends IService<Withdrawa> {
    public Boolean add(Withdrawa withdrawa);
}
