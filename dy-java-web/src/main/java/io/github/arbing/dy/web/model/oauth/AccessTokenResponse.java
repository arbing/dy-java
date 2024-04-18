package io.github.arbing.dy.web.model.oauth;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.arbing.dy.common.model.DyBaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AccessTokenResponse extends DyBaseVo {
    /**
     * 授权用户唯一标识
     */
    @ApiModelProperty(value = "授权用户唯一标识")
    @JSONField(alternateNames = "open_id", ordinal = 1)
    private String openId;

    /**
     * 用户授权的作用域（Scope），使用逗号（,）分隔，开放平台几乎每个接口都需要特定的 Scope。
     */
    @ApiModelProperty(value = "用户授权的作用域（Scope），使用逗号（,）分隔，开放平台几乎每个接口都需要特定的 Scope。")
    @JSONField(alternateNames = "scope", ordinal = 2)
    private String scope;

    /**
     * 接口调用凭证
     */
    @ApiModelProperty(value = "接口调用凭证")
    @JSONField(alternateNames = "access_token", ordinal = 3)
    private String accessToken;

    /**
     * access_token 接口调用凭证超时时间，单位（秒)
     */
    @ApiModelProperty(value = "access_token 接口调用凭证超时时间，单位（秒)")
    @JSONField(alternateNames = "expires_in", ordinal = 4)
    private Integer expiresIn;

    /**
     * 用户刷新 access_token
     */
    @ApiModelProperty(value = "用户刷新 access_token")
    @JSONField(alternateNames = "refresh_token", ordinal = 5)
    private String refreshToken;

    /**
     * refresh_token 凭证超时时间，单位（秒)
     */
    @ApiModelProperty(value = "refresh_token 凭证超时时间，单位（秒)")
    @JSONField(alternateNames = "refresh_expires_in", ordinal = 6)
    private Integer refreshExpiresIn;
}
