package io.github.arbing.dy.common.error;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

/**
 * 抖音开发平台状态码表
 * <p>
 * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/status-code
 */
@Getter
public enum DyWebErrorEnum {

    SUCCESS(0L, "成功"),

    /// 通用状态码

    系统繁忙(2100004L, "系统繁忙，此时请开发者稍候再试"),
    参数不合法(2100005L, "参数不合法"),

    /// Oauth2 状态码

    参数错误(10002L, "参数错误"),
    授权码过期(10007L, "授权码过期"),
    access_token无效或过期(10008L, "access_token 无效或过期"),
    refresh_token无效或过期(10010L, "refresh_token 无效或过期"),
    更新refresh_token次数超出限制(10020L, "更新 refresh_token 次数超出限制"),
    access_token无效(2190002L, "access_token 无效"),
    access_token过期请刷新或重新授权(2190008L, "access_token 过期,请刷新或重新授权"),


    ;

    /**
     * 错误码
     */
    private Long errorCode;

    /**
     * 错误码描述
     */
    private String description;

    DyWebErrorEnum(Long errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public DyException buildException() {
        DyError dyError = DyError.builder().errorCode(this.getErrorCode()).description(findMsgByErrorCode(this.getErrorCode())).build();
        return new DyException(dyError);
    }

    static final Map<Long, String> valueMap = Maps.newHashMap();

    static {
        for (DyWebErrorEnum value : DyWebErrorEnum.values()) {
            valueMap.put(value.errorCode, value.description);
        }
    }

    /**
     * 通过错误代码查找其中文含义.
     */
    public static String findMsgByErrorCode(Long errorCode) {
        return valueMap.getOrDefault(errorCode, null);
    }
}
