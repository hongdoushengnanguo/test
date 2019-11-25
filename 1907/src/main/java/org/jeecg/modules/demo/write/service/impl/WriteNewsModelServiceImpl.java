package org.jeecg.modules.demo.write.service.impl;

import org.jeecg.modules.demo.write.entity.WriteNewsModel;
import org.jeecg.modules.demo.write.mapper.WriteNewsModelMapper;
import org.jeecg.modules.demo.write.service.IWriteNewsModelService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 文章标题
 * @Author: jeecg-boot
 * @Date:   2019-11-14
 * @Version: V1.0
 */
@Service
public class WriteNewsModelServiceImpl extends ServiceImpl<WriteNewsModelMapper, WriteNewsModel> implements IWriteNewsModelService {

}
