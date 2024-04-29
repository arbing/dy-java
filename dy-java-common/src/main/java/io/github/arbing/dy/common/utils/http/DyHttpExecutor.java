package io.github.arbing.dy.common.utils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.github.arbing.dy.common.constant.DyConstants;
import io.github.arbing.dy.common.error.DyError;
import io.github.arbing.dy.common.error.DyException;
import io.github.arbing.dy.common.error.DyWebErrorEnum;
import io.github.arbing.dy.common.model.DyResult;
import io.github.arbing.dy.common.model.IDyBaseVo;
import io.github.arbing.dy.common.model.IDySimpleBaseVo;
import lombok.SneakyThrows;
import lombok.val;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DyHttpExecutor {
    private final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    public <T> T executeGet(String url, Object queryParams, Map<String, String> headers, TypeReference<T> resultTypeReference) {
        return this.executeCall("GET", url, queryParams, null, headers, resultTypeReference);
    }

    public <T> T executePost(String url, Object queryParams, Object data, Map<String, String> headers, TypeReference<T> resultTypeReference) {
        return this.executeCall("POST", url, queryParams, data, headers, resultTypeReference);
    }

    @SneakyThrows
    public <T> T executeCall(String methodStr, String url, Object queryParams, Object data, Map<String, String> headers, TypeReference<T> resultTypeReference) {
        Request.Builder requestBuilder = new Request.Builder();

        HttpUrl httpUrl = HttpUrl.parse(url);
        if (Objects.nonNull(queryParams)) {
            val httpUrlBuilder = httpUrl.newBuilder();
            Map<String, Object> claims = (Map<String, Object>) JSON.toJSON(queryParams);
            for (val entry : claims.entrySet()) {
                String value = "";
                if (Objects.nonNull(entry.getValue())) {
                    value = String.valueOf(entry.getValue());
                }
                httpUrlBuilder.addQueryParameter(entry.getKey(), value);
            }

            httpUrl = httpUrlBuilder.build();
        }
        requestBuilder.url(httpUrl.toString());

        Map<String, String> allHeaders = new HashMap<>();
        allHeaders.put(DyConstants.CONTENT_TYPE, DyConstants.APPLICATION_JSON);
        allHeaders.put("Accept", DyConstants.APPLICATION_JSON);
        if (MapUtils.isNotEmpty(headers)) {
            allHeaders.putAll(headers);
        }

        for (val entry : allHeaders.entrySet()) {
            requestBuilder.header(entry.getKey(), entry.getValue());
        }

        methodStr = methodStr.toUpperCase();
        if ("GET".equalsIgnoreCase(methodStr)) {
            requestBuilder.get();
        } else {
            String contentType = allHeaders.get(DyConstants.CONTENT_TYPE);

            RequestBody body;
            if (DyConstants.APPLICATION_X_WWW_FORM_URLENCODED.equals(contentType)) {
                val formBodyBuilder = new FormBody.Builder();
                for (val entry : JSON.parseObject(JSON.toJSONString(data)).entrySet()) {
                    formBodyBuilder.add(entry.getKey(), entry.getValue().toString());
                }
                body = formBodyBuilder.build();
            } else {
                body = RequestBody.create(JSON.toJSONString(data), MediaType.parse(contentType));
            }
            requestBuilder.method(methodStr, body);
        }
        val resp = HTTP_CLIENT.newCall(requestBuilder.build()).execute();
        String bodyStr = resp.body().string();

        T response = JSON.parseObject(bodyStr, resultTypeReference);

        Long errorCode = 0L;
        String description = "";
        if (response instanceof DyResult) {
            DyResult<?> dyResult = (DyResult<?>) response;
            if (Objects.nonNull(dyResult.getExtra())) {
                errorCode = Objects.nonNull(dyResult.getExtra().getSubErrorCode()) ? dyResult.getExtra().getSubErrorCode() : dyResult.getExtra().getErrorCode();
                description = StringUtils.isNotEmpty(dyResult.getExtra().getSubDescription()) ? dyResult.getExtra().getSubDescription() : dyResult.getExtra().getDescription();
            } else if (dyResult.getData() instanceof IDyBaseVo) {
                IDyBaseVo dyData = (IDyBaseVo) dyResult.getData();
                errorCode = dyData.getErrorCode();
                description = dyData.getDescription();
            }
        } else if (response instanceof IDySimpleBaseVo) {
            IDySimpleBaseVo dySimple = (IDySimpleBaseVo) response;
            errorCode = dySimple.getErrNo();
            description = dySimple.getErrMsg();
        }
        if (!Objects.equals(0L, errorCode)) {
            String msg = DyWebErrorEnum.findMsgByErrorCode(errorCode);
            if (StringUtils.isNotEmpty(msg)) {
                description = msg;
            }
            DyError dyError = DyError.builder().errorCode(errorCode).description(description).json(bodyStr).build();
            throw new DyException(dyError);
        }

        return response;
    }
}
