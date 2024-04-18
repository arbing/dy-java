package io.github.arbing.dy.web.model.user;

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
public class ExternalUserCommentVo {
    /**
     * 日期，yyyy-MM-dd
     */
    @ApiModelProperty(value = "日期，yyyy-MM-dd", example = "yyyy-MM-dd")
    @JSONField(alternateNames = "date", ordinal = 1)
    private String date;

    /**
     * 新增评论
     */
    @ApiModelProperty(value = "新增评论")
    @JSONField(alternateNames = "new_comment", ordinal = 2)
    private Long newComment;

}
