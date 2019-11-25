package org.jeecg.modules.test.withdrawa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: 取钱记录
 * @Author: jeecg-boot
 * @Date:   2019-11-21
 * @Version: V1.0
 */
@Data
@TableName("withdrawal_record")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="withdrawal_record对象", description="取钱记录")
public class Withdrawa {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**saveName*/
	@Excel(name = "saveName", width = 15)
    @ApiModelProperty(value = "saveName")
	private java.lang.String saveName;
	/**withdrawalNumber*/
	@Excel(name = "withdrawalNumber", width = 15)
    @ApiModelProperty(value = "withdrawalNumber")
	private java.math.BigDecimal withdrawalNumber ;
	/**withdrawalTime*/
	@Excel(name = "withdrawalTime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "withdrawalTime")
	private java.util.Date withdrawalTime;
	/**createTime*/
	@Excel(name = "createTime", width = 15)
    @ApiModelProperty(value = "createTime")
	private java.lang.String createTime;
}
