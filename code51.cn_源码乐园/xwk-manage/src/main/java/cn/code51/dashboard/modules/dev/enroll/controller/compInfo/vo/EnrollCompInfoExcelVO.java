package cn.code51.dashboard.modules.dev.enroll.controller.compInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 竞赛报名 Excel VO
 *
 * @author 教研源码
 */
@Data
public class EnrollCompInfoExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("竞赛名称")
    private String compName;

    @ExcelProperty("参赛人编号")
    private String enrollerId;

    @ExcelProperty("参赛人姓名")
    private String enrollerName;

    @ExcelProperty("状态")
    private String statusInfo;

}
