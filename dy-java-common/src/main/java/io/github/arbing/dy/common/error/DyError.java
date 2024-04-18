package io.github.arbing.dy.common.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 抖音开发平台状态码
 * <p>
 * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/status-code
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DyError {
    /**
     * 错误码
     */
    private Long errorCode;

    /**
     * 错误码描述
     */
    private String description;

    /**
     * 原始报文
     */
    private String json;

    @Override
    public String toString() {
        if (this.json == null) {
            return "错误代码：" + this.errorCode + ", 错误信息：" + this.description;
        }

        return "错误代码：" + this.errorCode + ", 错误信息：" + this.description + "，抖音原始报文：" + this.json;
    }
}
