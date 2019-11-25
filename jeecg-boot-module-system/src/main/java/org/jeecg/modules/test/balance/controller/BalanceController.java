package org.jeecg.modules.test.balance.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.test.balance.entity.Balance;
import org.jeecg.modules.test.balance.service.IBalanceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.test.withdrawa.entity.Withdrawa;
import org.jeecg.modules.test.withdrawa.service.IWithdrawaService;
import org.jeecg.modules.test.withdrawa.service.impl.WithdrawaServiceImpl;
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
 * @Description: 存钱余额
 * @Author: jeecg-boot
 * @Date:   2019-11-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="存钱余额")
@RestController
@RequestMapping("/balance/balance")
public class BalanceController extends JeecgController<Balance, IBalanceService> {

	 @Autowired
	private IBalanceService balanceService;
	@Autowired
	private WithdrawaServiceImpl withdrawaService;
	/**
	 * 分页列表查询
	 *
	 * @param balance
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "存钱余额-分页列表查询")
	@ApiOperation(value="存钱余额-分页列表查询", notes="存钱余额-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Balance balance,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {

		QueryWrapper<Balance> queryWrapper = QueryGenerator.initQueryWrapper(balance, req.getParameterMap());
		Page<Balance> page = new Page<Balance>(pageNo, pageSize);
		IPage<Balance> pageList = balanceService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param balance
	 * @return
	 */
	@AutoLog(value = "存钱余额-添加")
	@ApiOperation(value="存钱余额-添加", notes="存钱余额-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Balance balance) {
		balanceService.save(balance);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 取钱
	 *
	 * @param balance
	 * @return
	 */
	@AutoLog(value = "存钱余额-取钱")
	@ApiOperation(value="存钱余额-取钱", notes="存钱余额-取钱")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Balance balance) {
		log.info("取款+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+balance.getSaveNumber());
//		将记录存进记录表得实体类
		Withdrawa withdrawa=new Withdrawa();
		withdrawa.setSaveName(balance.getSaveName());
		withdrawa.setWithdrawalNumber(balance.getSaveNumber());
		withdrawa.setWithdrawalTime(new Date());
//		判断取款金是否大于余额
		if(balanceService.chanceMoney(balance)){
//			将余额取出和取款金额做差
			balance.setSaveNumber(balanceService.findBalanceNumber(balance).getSaveNumber().subtract(balance.getSaveNumber()));
//			如果添加成功就将记录存进记录表中
			if(withdrawaService.add(withdrawa)){
				balanceService.updateById(balance);
				return Result.ok("取款成功!");
			}else{
				return Result.error("操作有误");
			}
		}else{
			return Result.error("余额不足");
		}

	}
	 @AutoLog(value = "存钱余额-存钱")
	 @ApiOperation(value="存钱余额-存钱", notes="存钱余额-存钱")
	 @PutMapping(value ="/inmoney")
	public Result<?>  editIn(@RequestBody Balance balance){
//		存钱  将金额和余额做和
		balance.setSaveNumber(balanceService.findBalanceNumber(balance).getSaveNumber().add(balance.getSaveNumber()));
		balanceService.updateById(balance);
		return Result.ok("存款成功");
	 }


	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存钱余额-通过id删除")
	@ApiOperation(value="存钱余额-通过id删除", notes="存钱余额-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		Balance balance=new Balance();
		balance.setId(id);
		if(balanceService.findBalanceNumber(balance).getSaveNumber().compareTo(BigDecimal.ZERO)==0){
			balanceService.removeById(id);
			return Result.ok("删除成功!");
		}else{
			return Result.error("余额不为零,无法删除");
		}

	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "存钱余额-批量删除")
	@ApiOperation(value="存钱余额-批量删除", notes="存钱余额-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.balanceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "存钱余额-通过id查询")
	@ApiOperation(value="存钱余额-通过id查询", notes="存钱余额-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Balance balance = balanceService.getById(id);
		return Result.ok(balance);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param balance
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Balance balance) {
      return super.exportXls(request, balance, Balance.class, "存钱余额");
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
      return super.importExcel(request, response, Balance.class);
  }

}
