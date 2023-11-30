package cn.code51.dashboard.modules.system.controller.dept.vo.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("学院列表 Request VO")
@Data
public class SysDeptListReqVO {

    @ApiModelProperty(value = "学院名称", example = "教研", notes = "模糊匹配")
    private String name;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 SysCommonStatusEnum 枚举类")
    private Integer status;

    private Long parentId;


}
