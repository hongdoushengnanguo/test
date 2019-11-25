package org.jeecg.modules.test.write.service.impl;

import org.jeecg.modules.test.write.entity.WriteNewsModel;
import org.jeecg.modules.test.write.mapper.WriteNewsModelMapper;
import org.jeecg.modules.test.write.service.IWriteNewsModelService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 文章维护
 * @Author: jeecg-boot
 * @Date:   2019-11-15
 * @Version: V1.0
 */
@Service
public class WriteNewsModelServiceImpl extends ServiceImpl<WriteNewsModelMapper, WriteNewsModel> implements IWriteNewsModelService {


}
