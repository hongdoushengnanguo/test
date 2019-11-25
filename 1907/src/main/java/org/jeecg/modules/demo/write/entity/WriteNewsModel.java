package org.jeecg.modules.demo.write.entity;

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
 * @Description: 文章标题
 * @Author: jeecg-boot
 * @Date:   2019-11-14
 * @Version: V1.0
 */
@Data
@TableName("write_news")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="write_news对象", description="文章标题")
public class WriteNewsModel {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.Integer id;
	/**wName*/
	@Excel(name = "wName", width = 15)
    @ApiModelProperty(value = "wName")
	private java.lang.String wName;
	/**wContent*/
	@Excel(name = "wContent", width = 15)
    @ApiModelProperty(value = "wContent")
	private java.lang.String wContent;
	/**wKind*/
	@Excel(name = "wKind", width = 15)
    @ApiModelProperty(value = "wKind")
	private java.lang.String wKind;
	/**wRtime*/
	@Excel(name = "wRtime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "wRtime")
	private java.util.Date wRtime;
	/**wPhoto*/
	@Excel(name = "wPhoto", width = 15)
    @ApiModelProperty(value = "wPhoto")
	private java.lang.String wPhoto;
}
