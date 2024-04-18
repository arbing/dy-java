package io.github.arbing.dy.web.model.oauth;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoRequest {
    /**
     * 通过 /oauth/access_token/ 获取，用户唯一标识
     */
    @ApiModelProperty(value = "通过 /oauth/access_token/ 获取，用户唯一标识", required = true)
    @JSONField(name = "open_id", ordinal = 1)
    private String openId;

    /**
     * 调用 /oauth/access_token/ 生成的 token，此 token 需要用户授权
     */
    @ApiModelProperty(value = "调用 /oauth/access_token/ 生成的 token，此 token 需要用户授权", required = true)
    @JSONField(name = "access_token", ordinal = 2)
    private String accessToken;
}
