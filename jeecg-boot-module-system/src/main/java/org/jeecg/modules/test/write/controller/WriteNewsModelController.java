package org.jeecg.modules.test.write.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.test.write.entity.WriteNewsModel;
import org.jeecg.modules.test.write.service.IWriteNewsModelService;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 文章维护
 * @Author: jeecg-boot
 * @Date:   2019-11-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags="文章维护")
@RestController
@RequestMapping("/write/writeNewsModel")
public class WriteNewsModelController extends JeecgController<WriteNewsModel, IWriteNewsModelService> {
	@Autowired
	private IWriteNewsModelService writeNewsModelService;
	 @Value(value = "${jeecg.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param writeNewsModel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "文章维护-分页列表查询")
	@ApiOperation(value="文章维护-分页列表查询", notes="文章维护-分页列表查询")
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
	@AutoLog(value = "文章维护-添加")
	@ApiOperation(value="文章维护-添加", notes="文章维护-添加")
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
	@AutoLog(value = "文章维护-编辑")
	@ApiOperation(value="文章维护-编辑", notes="文章维护-编辑")
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
	@AutoLog(value = "文章维护-通过id删除")
	@ApiOperation(value="文章维护-通过id删除", notes="文章维护-通过id删除")
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
	@AutoLog(value = "文章维护-批量删除")
	@ApiOperation(value="文章维护-批量删除", notes="文章维护-批量删除")
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
	@AutoLog(value = "文章维护-通过id查询")
	@ApiOperation(value="文章维护-通过id查询", notes="文章维护-通过id查询")
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
      return super.exportXls(request, writeNewsModel, WriteNewsModel.class, "文章维护");
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

	 @PostMapping(value = "/upload")
	 public Result<?> upload(HttpServletRequest request, HttpServletResponse response) {
		 Result<?> result = new Result<>();

		 try {
			 String ctxPath = uploadpath;


			 String fileName = null;
			 String bizPath = "files";
			 String nowday = new SimpleDateFormat("yyyyMMdd").format(new Date());
			 File file = new File(ctxPath + File.separator + bizPath + File.separator + nowday);
			 if (!file.exists()) {
				 file.mkdirs();// 创建文件根目录
			 }
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			 MultipartFile mf = multipartRequest.getFile("file");// 获取上传文件对象
			 String orgName = mf.getOriginalFilename();// 获取文件名
			 fileName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
			 String savePath = file.getPath() + File.separator + fileName;
			 File savefile = new File(savePath);
			 FileCopyUtils.copy(mf.getBytes(), savefile);
			 String dbpath = bizPath + File.separator + nowday + File.separator + fileName;
			 if (dbpath.contains("\\")) {
				 dbpath = dbpath.replace("\\", "/");
			 }

			 result.setMessage(dbpath);
			 result.setSuccess(true);
		 } catch (IOException e) {
			 result.setSuccess(false);
			 result.setMessage(e.getMessage());
			 log.error(e.getMessage(), e);
		 }
		 return result;
	 }

	 /**
	  * 预览图片
	  * 请求地址：http://localhost:8080/common/view/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
	  *
	  * @param request
	  * @param response
	  */
	 @GetMapping(value = "/view/**")
	 public void view(HttpServletRequest request, HttpServletResponse response) {
		 // ISO-8859-1 ==> UTF-8 进行编码转换
		 String imgPath = extractPathFromPattern(request);
		 // 其余处理略

		 InputStream inputStream = null;
		 OutputStream outputStream = null;
		 try {
			 imgPath = imgPath.replace("..", "");
			 if (imgPath.endsWith(",")) {
				 imgPath = imgPath.substring(0, imgPath.length() - 1);
			 }
			 response.setContentType("image/jpeg;charset=utf-8");
			 String localPath = uploadpath;
			 String imgurl = localPath + File.separator + imgPath;

			 inputStream = new BufferedInputStream(new FileInputStream(imgurl));
			 outputStream = response.getOutputStream();
			 byte[] buf = new byte[1024];
			 int len;
			 while ((len = inputStream.read(buf)) > 0) {
				 outputStream.write(buf, 0, len);
			 }
			 response.flushBuffer();
		 } catch (IOException e) {
			 log.error("预览图片失败++++++++" + e.getMessage());
			 // e.printStackTrace();
		 } finally {
			 if (inputStream != null) {
				 try {
					 inputStream.close();
				 } catch (IOException e) {
					 log.error(e.getMessage(), e);
				 }
			 }
			 if (outputStream != null) {
				 try {
					 outputStream.close();
				 } catch (IOException e) {
					 log.error(e.getMessage(), e);
				 }
			 }
		 }

	 }

	 /**
	  * 下载文件
	  * 请求地址：http://localhost:8080/common/download/{user/20190119/e1fe9925bc315c60addea1b98eb1cb1349547719_1547866868179.jpg}
	  *
	  * @param request
	  * @param response
	  * @throws Exception
	  */
	 @GetMapping(value = "/download/**")
	 public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 // ISO-8859-1 ==> UTF-8 进行编码转换
		 String filePath = extractPathFromPattern(request);
		 // 其余处理略
		 InputStream inputStream = null;
		 OutputStream outputStream = null;
		 try {
			 filePath = filePath.replace("..", "");
			 if (filePath.endsWith(",")) {
				 filePath = filePath.substring(0, filePath.length() - 1);
			 }
			 String localPath = uploadpath;
			 String downloadFilePath = localPath + File.separator + filePath;
			 File file = new File(downloadFilePath);
			 if (file.exists()) {
				 response.setContentType("application/force-download");// 设置强制下载不打开            
				 response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1"));
				 inputStream = new BufferedInputStream(new FileInputStream(file));
				 outputStream = response.getOutputStream();
				 byte[] buf = new byte[1024];
				 int len;
				 while ((len = inputStream.read(buf)) > 0) {
					 outputStream.write(buf, 0, len);
				 }
				 response.flushBuffer();
			 }

		 } catch (Exception e) {
			 log.info("文件下载失败" + e.getMessage());
			 // e.printStackTrace();
		 } finally {
			 if (inputStream != null) {
				 try {
					 inputStream.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
			 if (outputStream != null) {
				 try {
					 outputStream.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }

	 }
	 /**
	  * @功能：pdf预览Iframe
	  * @param modelAndView
	  * @return
	  */
	 @RequestMapping("/pdf/pdfPreviewIframe")
	 public ModelAndView pdfPreviewIframe(ModelAndView modelAndView) {
		 modelAndView.setViewName("pdfPreviewIframe");
		 return modelAndView;
	 }

	 /**
	  *  把指定URL后的字符串全部截断当成参数
	  *  这么做是为了防止URL中包含中文或者特殊字符（/等）时，匹配不了的问题
	  * @param request
	  * @return
	  */
	 private static String extractPathFromPattern(final HttpServletRequest request) {
		 String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		 String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		 return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
	 }



 }
