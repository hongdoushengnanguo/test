package org.jeecg.modules.test.write.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;




@Data
@TableName("photo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="photo对象", description="文章维护")
public class PhotoContentModel {

    /**id*/
    @TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.String id;


    /**writeName*/
    @Excel(name = "writeName", width = 15)
    @ApiModelProperty(value = "writeName")
    private java.lang.String writeName;

    /**writePhoto*/
    @Excel(name = "avatar", width = 15)
    @ApiModelProperty(value = "avatar")
    private java.lang.String avatar;

}
