package org.jeecg.modules.test.write.controller;



import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;

import org.jeecg.modules.test.write.entity.PhotoContentModel;

import org.jeecg.modules.test.write.entity.WriteNewsModel;
import org.jeecg.modules.test.write.service.impl.PhotoContentIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/photo/photocontent")
@Slf4j
public class Upload extends JeecgController<PhotoContentModel, PhotoContentIService> {
    @Autowired
    private PhotoContentIService photoContentIService;

    @ResponseBody
    @GetMapping ("/newslist")
    public Result<?> queryPageList(PhotoContentModel photoContentModel,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {

        QueryWrapper<PhotoContentModel> queryWrapper = QueryGenerator.initQueryWrapper(photoContentModel, req.getParameterMap());
        Page<PhotoContentModel> page = new Page<PhotoContentModel>(pageNo, pageSize);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        IPage<PhotoContentModel> pageList = photoContentIService.page(page,queryWrapper);
        return Result.ok(pageList);
    }
    /**
     * 添加
     *
     * @param photoContentModel
     * @return
     */
    @AutoLog(value = "图片维护-添加")
    @ApiOperation(value="图片维护-添加", notes="图片维护-添加")
    @PostMapping(value = "/photonewsadd")
    public Result<?> add( PhotoContentModel photoContentModel) {
        photoContentModel.setId("");
        photoContentIService.save(photoContentModel);
        return Result.ok("添加成功！");
    }
}



