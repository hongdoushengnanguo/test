package org.jeecg.modules.test.write.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.test.write.entity.PhotoContentModel;
import org.jeecg.modules.test.write.mapper.PhotoContentMapper;

import org.jeecg.modules.test.write.service.PhotoContentService;
import org.springframework.stereotype.Service;

@Service
public class PhotoContentIService  extends ServiceImpl<PhotoContentMapper, PhotoContentModel> implements PhotoContentService {
}
