package cn.code51.dashboard.framework.security.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围枚举类
 *
 * 用于实现数据级别的权限
 *
 * @author 教研源码
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {

    ALL(1), // 全部数据权限
    DEPT_CUSTOM(2), // 指定学院数据权限
    DEPT_ONLY(3), // 学院数据权限
    DEPT_AND_CHILD(4), // 学院及以下数据权限
    DEPT_SELF(5); // 仅本人数据权限

    /**
     * 范围
     */
    private final Integer score;

}
