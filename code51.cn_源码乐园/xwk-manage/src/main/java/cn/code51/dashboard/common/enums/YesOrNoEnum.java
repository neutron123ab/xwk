package cn.code51.dashboard.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public enum YesOrNoEnum {
    Yes("y","是"),
    No("n","否");
    private String code;
    private String desc;
}
