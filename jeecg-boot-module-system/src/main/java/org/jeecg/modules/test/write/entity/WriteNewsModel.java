package org.jeecg.modules.test.write.entity;

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
 * @Description: 文章维护
 * @Author: jeecg-boot
 * @Date:   2019-11-15
 * @Version: V1.0
 */
@Data
@TableName("write_news")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="write_news对象", description="文章维护")
public class WriteNewsModel {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**writeName*/
	@Excel(name = "writeName", width = 15)
    @ApiModelProperty(value = "writeName")
	private java.lang.String writeName;
	/**writeContent*/
	@Excel(name = "writeContent", width = 15)
    @ApiModelProperty(value = "writeContent")
	private java.lang.String writeContent;
	/**writeKind*/
	@Excel(name = "writeKind", width = 15)
    @ApiModelProperty(value = "writeKind")
	private java.lang.String writeKind;
	/**writeRtime*/
	@Excel(name = "writeRtime", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "writeRtime")
	private java.util.Date writeRtime;
	/**writePhoto*/
	@Excel(name = "avatar", width = 15)
    @ApiModelProperty(value = "avatar")
	private java.lang.String avatar;
	/**createTime*/
	@Excel(name = "createTime", width = 15)
    @ApiModelProperty(value = "createTime")
	private java.lang.String createTime;
}
