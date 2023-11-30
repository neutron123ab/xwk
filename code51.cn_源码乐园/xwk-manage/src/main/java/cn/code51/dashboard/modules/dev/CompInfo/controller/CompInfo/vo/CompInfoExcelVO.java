package cn.code51.dashboard.modules.dev.CompInfo.controller.CompInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 竞赛 Excel VO
 *
 * @author coder
 */
@Data
public class CompInfoExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("报名条件")
    private String conditionInfo;

    @ExcelProperty("学科类别")
    private String xkType;

    @ExcelProperty("所属学院")
    private String deptId;

    @ExcelProperty("审核状态")
    private String checkStatus;

    @ExcelProperty("竞赛描述信息")
    private String descInfo;

    @ExcelProperty("竞赛名称")
    private String name;

}
