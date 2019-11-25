package org.jeecg.modules.test.balance.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 存钱余额
 * @Author: jeecg-boot
 * @Date:   2019-11-21
 * @Version: V1.0
 */
@Data
@TableName("balance_news")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="balance_news对象", description="存钱余额")
public class Balance {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**saveName*/
	@Excel(name = "saveName", width = 15)
    @ApiModelProperty(value = "saveName")
	private java.lang.String saveName;
	/**saveNumber*/
	@Excel(name = "saveNumber", width = 15)
    @ApiModelProperty(value = "saveNumber")
	private java.math.BigDecimal saveNumber;
	/**saveTime*/
	@Excel(name = "saveTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "saveTime")
	private java.util.Date saveTime;
	/**createTime*/
	@Excel(name = "createTime", width = 15)
    @ApiModelProperty(value = "createTime")
	private java.lang.String createTime;
}
