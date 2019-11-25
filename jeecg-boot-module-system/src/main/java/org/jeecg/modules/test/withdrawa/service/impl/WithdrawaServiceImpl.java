package org.jeecg.modules.test.withdrawa.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.test.withdrawa.entity.Withdrawa;
import org.jeecg.modules.test.withdrawa.mapper.WithdrawaMapper;
import org.jeecg.modules.test.withdrawa.service.IWithdrawaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 取钱记录
 * @Author: jeecg-boot
 * @Date:   2019-11-21
 * @Version: V1.0
 */
@Service
@Slf4j
public class WithdrawaServiceImpl extends ServiceImpl<WithdrawaMapper, Withdrawa> implements IWithdrawaService {

    @Autowired
    private  WithdrawaMapper withdrawaMapper;


    /**
     * 判断取款记录是否成功
     */
    @Override
    public Boolean add(Withdrawa withdrawa) {
        Boolean x=false;
        if (withdrawaMapper.insert(withdrawa)>0){
            x=true;
        }
            return  x ;

    }
}
