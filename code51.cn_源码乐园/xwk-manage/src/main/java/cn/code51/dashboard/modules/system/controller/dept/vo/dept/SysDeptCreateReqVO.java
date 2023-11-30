package cn.code51.dashboard.modules.system.controller.dept.vo.dept;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("学院创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysDeptCreateReqVO extends SysDeptBaseVO {
}
