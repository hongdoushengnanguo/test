package org.jeecg.modules.demo.write.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.write.entity.WriteNewsModel;
import org.jeecg.modules.demo.write.service.IWriteNewsModelService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 文章标题
 * @Author: jeecg-boot
 * @Date:   2019-11-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="文章标题")
@RestController
@RequestMapping("/write/writeNewsModel")
public class WriteNewsModelController extends JeecgController<WriteNewsModel, IWriteNewsModelService> {
	@Autowired
	private IWriteNewsModelService writeNewsModelService;
	
	/**
	 * 分页列表查询
	 *
	 * @param writeNewsModel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "文章标题-分页列表查询")
	@ApiOperation(value="文章标题-分页列表查询", notes="文章标题-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(WriteNewsModel writeNewsModel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WriteNewsModel> queryWrapper = QueryGenerator.initQueryWrapper(writeNewsModel, req.getParameterMap());
		Page<WriteNewsModel> page = new Page<WriteNewsModel>(pageNo, pageSize);
		IPage<WriteNewsModel> pageList = writeNewsModelService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param writeNewsModel
	 * @return
	 */
	@AutoLog(value = "文章标题-添加")
	@ApiOperation(value="文章标题-添加", notes="文章标题-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody WriteNewsModel writeNewsModel) {
		writeNewsModelService.save(writeNewsModel);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param writeNewsModel
	 * @return
	 */
	@AutoLog(value = "文章标题-编辑")
	@ApiOperation(value="文章标题-编辑", notes="文章标题-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody WriteNewsModel writeNewsModel) {
		writeNewsModelService.updateById(writeNewsModel);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文章标题-通过id删除")
	@ApiOperation(value="文章标题-通过id删除", notes="文章标题-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		writeNewsModelService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "文章标题-批量删除")
	@ApiOperation(value="文章标题-批量删除", notes="文章标题-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.writeNewsModelService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文章标题-通过id查询")
	@ApiOperation(value="文章标题-通过id查询", notes="文章标题-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		WriteNewsModel writeNewsModel = writeNewsModelService.getById(id);
		return Result.ok(writeNewsModel);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param writeNewsModel
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, WriteNewsModel writeNewsModel) {
      return super.exportXls(request, writeNewsModel, WriteNewsModel.class, "文章标题");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, WriteNewsModel.class);
  }

}
