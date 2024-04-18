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
public class UserInfoResponse extends DyBaseVo {
    /**
     * 用户在当前应用的唯一标识
     */
    @ApiModelProperty(value = "用户在当前应用的唯一标识")
    @JSONField(alternateNames = "open_id", ordinal = 1)
    private String openId;

    /**
     * 用户在当前开发者账号下的唯一标识（未绑定开发者账号没有该字段）
     */
    @ApiModelProperty(value = "用户在当前开发者账号下的唯一标识（未绑定开发者账号没有该字段）")
    @JSONField(alternateNames = "union_id", ordinal = 2)
    private String unionId;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    @JSONField(alternateNames = "nickname", ordinal = 3)
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    @JSONField(alternateNames = "avatar", ordinal = 4)
    private String avatar;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    @JSONField(alternateNames = "avatar_larger", ordinal = 5)
    private String avatarLarger;

    /**
     * 类型：
     * EAccountM：普通企业号
     * EAccountS：认证企业号
     * EAccountK：品牌企业号
     */
    @ApiModelProperty(value = "类型：\n" +
            "EAccountM：普通企业号\n" +
            "EAccountS：认证企业号\n" +
            "EAccountK：品牌企业号")
    @JSONField(alternateNames = "e_account_role", ordinal = 6)
    private String eAccountRole;

    /**
     * 应用唯一标识
     */
    @ApiModelProperty(value = "应用唯一标识")
    @JSONField(name = "client_key", ordinal = 7)
    private String clientKey;
}
