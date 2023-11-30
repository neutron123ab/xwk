package cn.code51.dashboard.modules.dev.workinfo.controller.workInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 参赛作品 Excel VO
 *
 * @author 教研源码
 */
@Data
public class EnrollWorkInfoExcelVO {

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

    @ExcelProperty("参赛作品")
    private String workInfo;

    @ExcelProperty("评分")
    private String getScore;

}
