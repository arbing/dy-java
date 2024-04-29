package io.github.arbing.dy.web;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import io.github.arbing.dy.common.constant.DyConstants;
import io.github.arbing.dy.common.error.DyError;
import io.github.arbing.dy.common.error.DyException;
import io.github.arbing.dy.common.error.DyWebErrorEnum;
import io.github.arbing.dy.common.model.DyResult;
import io.github.arbing.dy.common.model.DySimpleResult;
import io.github.arbing.dy.common.model.ResultListResponse;
import io.github.arbing.dy.common.model.ResultResponse;
import io.github.arbing.dy.common.utils.http.DyHttpExecutor;
import io.github.arbing.dy.web.config.DyWebUserConfig;
import io.github.arbing.dy.web.model.oauth.AccessTokenResponse;
import io.github.arbing.dy.web.model.oauth.RefreshTokenRequest;
import io.github.arbing.dy.web.model.oauth.RenewRefreshTokenRequest;
import io.github.arbing.dy.web.model.oauth.RenewRefreshTokenResponse;
import io.github.arbing.dy.web.model.oauth.UserInfoRequest;
import io.github.arbing.dy.web.model.oauth.UserInfoResponse;
import io.github.arbing.dy.web.model.room.GetRoomIdListResponse;
import io.github.arbing.dy.web.model.room.LiveRoomResponse;
import io.github.arbing.dy.web.model.user.ExternalUserCommentVo;
import io.github.arbing.dy.web.model.user.ExternalUserFansVo;
import io.github.arbing.dy.web.model.user.ExternalUserItemVo;
import io.github.arbing.dy.web.model.user.ExternalUserLikeVo;
import io.github.arbing.dy.web.model.user.ExternalUserProfileVo;
import io.github.arbing.dy.web.model.user.ExternalUserShareVo;
import io.github.arbing.dy.web.model.video.ExternalItemBaseVo;
import io.github.arbing.dy.web.model.video.ExternalItemCommentVo;
import io.github.arbing.dy.web.model.video.ExternalItemLikeVo;
import io.github.arbing.dy.web.model.video.ExternalItemPlayVo;
import io.github.arbing.dy.web.model.video.ExternalItemShareVo;
import io.github.arbing.dy.web.model.video.GetIframeByItemResponse;
import io.github.arbing.dy.web.model.video.QueryVideoDataRequest;
import io.github.arbing.dy.web.model.video.QueryVideoDataResponse;
import io.github.arbing.dy.web.model.video.QueryVideoListResponse;
import io.github.arbing.dy.web.model.video.VideoInfoVo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Getter
public class DyWebUserClient {
    private final DyWebUserConfig userConfig;

    private final DyHttpExecutor httpExecutor;

    // region 用户管理

    /**
     * 获取用户公开信息
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/account-management/get-account-open-info
     *
     * @return
     */
    public UserInfoResponse getUserInfo() {
        String path = "/oauth/userinfo/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        val response = this.executePost(url,
                null,
                UserInfoRequest.builder()
                        .openId(this.getUserConfig().getOpenId())
                        .accessToken(accessToken)
                        .build(),
                ImmutableMap.of(DyConstants.CONTENT_TYPE, DyConstants.APPLICATION_X_WWW_FORM_URLENCODED),
                new TypeReference<DyResult<UserInfoResponse>>() {
                });
        return response.getData();
    }

    // endregion

    // region 数据开放服务-用户数据

    /**
     * 获取用户视频情况
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/user-data/get-user-video-status
     * <p>
     * 测试应用无法申请该权限。
     *
     * @param dateType 近7/15/30天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    public List<ExternalUserItemVo> externalUserItem(int dateType) {
        String path = "/data/external/user/item/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(), DyConstants.DATE_TYPE, dateType),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<ResultListResponse<ExternalUserItemVo>>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<ResultListResponse<ExternalUserItemVo>>builder().data(new ResultListResponse<>()).build();
        }
        if (Objects.isNull(response.getData().getResultList())) {
            response.getData().setResultList(new ArrayList<>());
        }
        return response.getData().getResultList();
    }

    /**
     * 获取用户粉丝数
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/user-data/get-user-fans-count
     * <p>
     * 测试应用无法申请该权限。
     *
     * @param dateType 近7/15/30天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    public List<ExternalUserFansVo> externalUserFans(int dateType) {
        String path = "/data/external/user/fans/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(), DyConstants.DATE_TYPE, dateType),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<ResultListResponse<ExternalUserFansVo>>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<ResultListResponse<ExternalUserFansVo>>builder().data(new ResultListResponse<>()).build();
        }
        if (Objects.isNull(response.getData().getResultList())) {
            response.getData().setResultList(new ArrayList<>());
        }
        return response.getData().getResultList();
    }

    /**
     * 获取用户点赞数
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/user-data/get-user-like-number
     * <p>
     * 测试应用无法申请该权限。
     *
     * @param dateType 近7/15/30天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    public List<ExternalUserLikeVo> externalUserLike(int dateType) {
        String path = "/data/external/user/like/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(), DyConstants.DATE_TYPE, dateType),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<ResultListResponse<ExternalUserLikeVo>>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<ResultListResponse<ExternalUserLikeVo>>builder().data(new ResultListResponse<>()).build();
        }
        if (Objects.isNull(response.getData().getResultList())) {
            response.getData().setResultList(new ArrayList<>());
        }
        return response.getData().getResultList();
    }

    /**
     * 获取用户评论数
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/user-data/get-user-comment-count
     * <p>
     * 测试应用无法申请该权限。
     *
     * @param dateType 近7/15/30天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    public List<ExternalUserCommentVo> externalUserComment(int dateType) {
        String path = "/data/external/user/comment/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(), DyConstants.DATE_TYPE, dateType),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<ResultListResponse<ExternalUserCommentVo>>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<ResultListResponse<ExternalUserCommentVo>>builder().data(new ResultListResponse<>()).build();
        }
        if (Objects.isNull(response.getData().getResultList())) {
            response.getData().setResultList(new ArrayList<>());
        }
        return response.getData().getResultList();
    }

    /**
     * 获取用户分享数
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/user-data/get-user-share-count
     * <p>
     * 测试应用无法申请该权限。
     *
     * @param dateType 近7/15/30天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    public List<ExternalUserShareVo> externalUserShare(int dateType) {
        String path = "/data/external/user/share/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(), DyConstants.DATE_TYPE, dateType),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<ResultListResponse<ExternalUserShareVo>>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<ResultListResponse<ExternalUserShareVo>>builder().data(new ResultListResponse<>()).build();
        }
        if (Objects.isNull(response.getData().getResultList())) {
            response.getData().setResultList(new ArrayList<>());
        }
        return response.getData().getResultList();
    }

    /**
     * 获取用户主页访问数
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/user-data/get-user-home-pv
     * <p>
     * 测试应用无法申请该权限。
     *
     * @param dateType 近7/15/30天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    public List<ExternalUserProfileVo> externalUserProfile(int dateType) {
        String path = "/data/external/user/profile/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(), DyConstants.DATE_TYPE, dateType),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<ResultListResponse<ExternalUserProfileVo>>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<ResultListResponse<ExternalUserProfileVo>>builder().data(new ResultListResponse<>()).build();
        }
        if (Objects.isNull(response.getData().getResultList())) {
            response.getData().setResultList(new ArrayList<>());
        }
        return response.getData().getResultList();
    }

    // endregion

    // region 视频管理

    /**
     * 查询授权账号视频列表
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/video-management/douyin/search-video/account-video-list
     *
     * @param count  每页数量
     * @param cursor 分页游标, 第一页请求cursor是0, response中会返回下一页请求用到的cursor, 同时response还会返回has_more来表明是否有更多的数据。
     * @return
     */
    public QueryVideoListResponse queryVideoList(Integer count, Long cursor) {
        String path = "/api/douyin/v1/video/video_list/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        val response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(), "count", count, "cursor", cursor),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<QueryVideoListResponse>>() {
                });
        return response.getData();
    }

    public List<VideoInfoVo> queryVideoListAll() {
        List<VideoInfoVo> outputs = Lists.newArrayList();
        Integer count = 10;
        Long cursor = 0L;
        while (true) {
            QueryVideoListResponse response = this.queryVideoList(count, cursor);
            val list = response.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                outputs.addAll(list);
            }
            if (Boolean.TRUE.equals(response.getHasMore())) {
                cursor = response.getCursor();
            } else {
                break;
            }
        }

        return outputs;
    }

    public List<VideoInfoVo> queryVideoListStart(Date startDate) {
        List<VideoInfoVo> outputs = Lists.newArrayList();
        Integer count = 10;
        Long cursor = 0L;
        while (true) {
            QueryVideoListResponse response = this.queryVideoList(count, cursor);
            val list = response.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                val filter = list.stream().filter(item -> item.getCreateTime() >= startDate.getTime() / 1000).collect(Collectors.toList());
                outputs.addAll(filter);
                if (filter.size() != list.size()) {
                    break;
                }
            }
            if (Boolean.TRUE.equals(response.getHasMore())) {
                cursor = response.getCursor();
            } else {
                break;
            }
        }

        return outputs;
    }

    /**
     * 查询特定视频的视频数据
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/video-management/douyin/search-video/video-data
     *
     * @param request
     * @return
     */
    public List<VideoInfoVo> queryVideoData(QueryVideoDataRequest request) {
        String path = "/api/douyin/v1/video/video_data/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executePost(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId()),
                request,
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<QueryVideoDataResponse>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<QueryVideoDataResponse>builder().data(new QueryVideoDataResponse()).build();
        }
        if (Objects.isNull(response.getData().getList())) {
            response.getData().setList(new ArrayList<>());
        }
        return response.getData().getList();
    }

    /**
     * 通过ItemID获取IFrame代码
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/video-management/douyin/iframe-player/get-iframe-by-item
     *
     * @param itemId 视频ItemID(请注意使用Base64URL编码)
     * @return
     */
    public GetIframeByItemResponse getIframeByItem(String itemId) {
        String path = "/api/douyin/v1/video/get_iframe_by_item";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        val response = this.executeGet(url,
                ImmutableMap.of(DyConstants.ITEM_ID, itemId,
                        DyConstants.CLIENT_KEY, this.getUserConfig().getClientKey()),
                null,
                new TypeReference<DyResult<GetIframeByItemResponse>>() {
                });
        return response.getData();
    }

    public String getVideoIdByItemId(String itemId) {
        val response = this.getIframeByItem(itemId);
        val code = response.getIframeCode();
        val videoId = code.split("vid==")[1].split("&")[0];
        return videoId;
    }

    // endregion

    // region 数据开放服务-视频数据

    /**
     * 获取视频基础数据
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/video-data/get-basic-data
     *
     * @param itemId 视频ID，仅能查询access_token对应用户上传的视频
     * @return
     */
    public ExternalItemBaseVo externalItemBase(String itemId) {
        String path = "/data/external/item/base/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(),
                        DyConstants.ITEM_ID, itemId
                ),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<ResultResponse<ExternalItemBaseVo>>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<ResultResponse<ExternalItemBaseVo>>builder().data(new ResultResponse<>()).build();
        }
        return response.getData().getResult();
    }

    /**
     * 获取视频点赞数据
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/video-data/get-like-data
     *
     * @param itemId   视频ID，仅能查询access_token对应用户上传的视频
     * @param dateType 近7/15天；输入7代表7天、15代表15天
     * @return
     */
    public List<ExternalItemLikeVo> externalItemLike(String itemId, int dateType) {
        String path = "/data/external/item/like/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(),
                        DyConstants.ITEM_ID, itemId,
                        DyConstants.DATE_TYPE, dateType
                ),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<ResultListResponse<ExternalItemLikeVo>>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<ResultListResponse<ExternalItemLikeVo>>builder().data(new ResultListResponse<>()).build();
        }
        if (Objects.isNull(response.getData().getResultList())) {
            response.getData().setResultList(new ArrayList<>());
        }
        return response.getData().getResultList();
    }

    /**
     * 获取视频评论数据
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/video-data/get-comment-data
     *
     * @param itemId   视频ID，仅能查询access_token对应用户上传的视频
     * @param dateType 近7/15天；输入7代表7天、15代表15天
     * @return
     */
    public List<ExternalItemCommentVo> externalItemComment(String itemId, int dateType) {
        String path = "/data/external/item/comment/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(),
                        DyConstants.ITEM_ID, itemId,
                        DyConstants.DATE_TYPE, dateType
                ),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<ResultListResponse<ExternalItemCommentVo>>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<ResultListResponse<ExternalItemCommentVo>>builder().data(new ResultListResponse<>()).build();
        }
        if (Objects.isNull(response.getData().getResultList())) {
            response.getData().setResultList(new ArrayList<>());
        }
        return response.getData().getResultList();
    }

    /**
     * 获取视频播放数据
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/video-data/get-play-data
     *
     * @param itemId   视频ID，仅能查询access_token对应用户上传的视频
     * @param dateType 近7/15天；输入7代表7天、15代表15天
     * @return
     */
    public List<ExternalItemPlayVo> externalItemPlay(String itemId, int dateType) {
        String path = "/data/external/item/play/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(),
                        DyConstants.ITEM_ID, itemId,
                        DyConstants.DATE_TYPE, dateType
                ),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<ResultListResponse<ExternalItemPlayVo>>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<ResultListResponse<ExternalItemPlayVo>>builder().data(new ResultListResponse<>()).build();
        }
        if (Objects.isNull(response.getData().getResultList())) {
            response.getData().setResultList(new ArrayList<>());
        }
        return response.getData().getResultList();
    }

    /**
     * 获取视频分享数据
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/video-data/get-share-data
     *
     * @param itemId   视频ID，仅能查询access_token对应用户上传的视频
     * @param dateType 近7/15天；输入7代表7天、15代表15天
     * @return
     */
    public List<ExternalItemShareVo> externalItemShare(String itemId, int dateType) {
        String path = "/data/external/item/share/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(),
                        DyConstants.ITEM_ID, itemId,
                        DyConstants.DATE_TYPE, dateType
                ),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DyResult<ResultListResponse<ExternalItemShareVo>>>() {
                });
        if (Objects.isNull(response)) {
            response = DyResult.<ResultListResponse<ExternalItemShareVo>>builder().data(new ResultListResponse<>()).build();
        }
        if (Objects.isNull(response.getData().getResultList())) {
            response.getData().setResultList(new ArrayList<>());
        }
        return response.getData().getResultList();
    }

    // endregion

    // region 数据开放服务-直播数据

    /**
     * 获取主播历史开播过的房间ID
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/live-data/live-id
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Long> getRoomIdList(Date startTime, Date endTime) {
        String path = "/room/data/room_id/get/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(),
                        DyConstants.START_TIME, startTime.getTime() / 1000,
                        DyConstants.END_TIME, endTime.getTime() / 1000
                ),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<GetRoomIdListResponse>() {
                });
        if (Objects.isNull(response)) {
            response = new GetRoomIdListResponse();
        }
        if (Objects.isNull(response.getRoomIds())) {
            response.setRoomIds(new ArrayList<>());
        }
        return response.getRoomIds();
    }

    /**
     * 获取直播间互动数据
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/live-data/live-interactive-data
     *
     * @param roomId 房间id
     * @param liveId 业务线id： 1-抖火 3-西瓜头条
     * @return
     */
    public LiveRoomResponse getRoomInteractiveData(Long roomId, Integer liveId) {
        String path = "/room/data/interactive/get/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(),
                        DyConstants.ROOM_ID, roomId.toString(),
                        DyConstants.LIVE_ID, liveId.toString()
                ),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DySimpleResult<LiveRoomResponse>>() {
                });
        if (Objects.isNull(response)) {
            response = DySimpleResult.<LiveRoomResponse>builder().data(new LiveRoomResponse()).build();
        }
        if (Objects.isNull(response.getData().getStats())) {
            response.getData().setStats(new ArrayList<>());
        }
        return response.getData();
    }

    /**
     * 获取直播间看播数据
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/live-data/live-audience-data
     *
     * @param roomId 房间id
     * @param liveId 业务线id： 1-抖火 3-西瓜头条
     * @return
     */
    public LiveRoomResponse getRoomAudienceData(Long roomId, Integer liveId) {
        String path = "/room/data/audience/get/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(),
                        DyConstants.ROOM_ID, roomId.toString(),
                        DyConstants.LIVE_ID, liveId.toString()
                ),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DySimpleResult<LiveRoomResponse>>() {
                });
        if (Objects.isNull(response)) {
            response = DySimpleResult.<LiveRoomResponse>builder().data(new LiveRoomResponse()).build();
        }
        if (Objects.isNull(response.getData().getStats())) {
            response.getData().setStats(new ArrayList<>());
        }
        return response.getData();
    }

    /**
     * 获取直播间基础数据
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/data-open-service/live-data/live-base-data
     *
     * @param roomId 房间id
     * @param liveId 业务线id： 1-抖火 3-西瓜头条
     * @return
     */
    public LiveRoomResponse getRoomBaseData(Long roomId, Integer liveId) {
        String path = "/room/data/base/get/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String accessToken = this.getAccessToken();
        var response = this.executeGet(url,
                ImmutableMap.of(DyConstants.OPEN_ID, this.getUserConfig().getOpenId(),
                        DyConstants.ROOM_ID, roomId.toString(),
                        DyConstants.LIVE_ID, liveId.toString()
                ),
                ImmutableMap.of(DyConstants.ACCESS_TOKEN, accessToken),
                new TypeReference<DySimpleResult<LiveRoomResponse>>() {
                });
        if (Objects.isNull(response)) {
            response = DySimpleResult.<LiveRoomResponse>builder().data(new LiveRoomResponse()).build();
        }
        if (Objects.isNull(response.getData().getStats())) {
            response.getData().setStats(new ArrayList<>());
        }
        return response.getData();
    }

    // endregion

    /**
     * 获取 access_token
     *
     * @return
     */
    public String getAccessToken() {
        String accessToken = this.getUserConfig().getAccessToken();
        if (StringUtils.isNotEmpty(accessToken)) {
            return accessToken;
        }

        Lock lock = this.getUserConfig().getAccessTokenLock();
        boolean locked = false;
        try {
            do {
                locked = lock.tryLock(100, TimeUnit.MILLISECONDS);
                accessToken = this.getUserConfig().getAccessToken();
                if (StringUtils.isNotEmpty(accessToken)) {
                    return accessToken;
                }
            } while (!locked);

            AccessTokenResponse accessTokenResponse = this.doGetAccessToken();
            return accessTokenResponse.getAccessToken();
        } catch (InterruptedException e) {
            throw new DyException(e);
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    /**
     * 刷新 access_token
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/account-permission/refresh-access-token
     *
     * @return
     */
    protected AccessTokenResponse doGetAccessToken() {
        String path = "/oauth/refresh_token/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String refreshToken = this.getUserConfig().getRefreshToken();
        if (StringUtils.isEmpty(refreshToken)) {
            throw DyWebErrorEnum.refresh_token无效或过期.buildException();
        }

        val response = this.executePost(url,
                null,
                RefreshTokenRequest.builder()
                        .clientKey(this.getUserConfig().getClientKey())
                        .grantType("refresh_token")
                        .refreshToken(refreshToken)
                        .build(),
                ImmutableMap.of(DyConstants.CONTENT_TYPE, DyConstants.APPLICATION_X_WWW_FORM_URLENCODED),
                new TypeReference<DyResult<AccessTokenResponse>>() {
                });
        val res = response.getData();

        this.getUserConfig().updateAccessToken(res.getAccessToken(), res.getExpiresIn());

        return res;
    }

    /**
     * 刷新 refresh_token
     * <p>
     * https://developer.open-douyin.com/docs/resource/zh-CN/dop/develop/openapi/account-permission/refresh-token
     *
     * @return
     */
    public RenewRefreshTokenResponse renewRefreshToken() {
        String path = "/oauth/renew_refresh_token/";
        String url = this.getUserConfig().getBaseUrl().concat(path);
        String refreshToken = this.getUserConfig().getRefreshToken();
        if (StringUtils.isEmpty(refreshToken)) {
            throw DyWebErrorEnum.refresh_token无效或过期.buildException();
        }

        val response = this.executePost(url,
                null,
                RenewRefreshTokenRequest.builder()
                        .clientKey(this.getUserConfig().getClientKey())
                        .refreshToken(refreshToken)
                        .build(),
                ImmutableMap.of(DyConstants.CONTENT_TYPE, DyConstants.APPLICATION_X_WWW_FORM_URLENCODED),
                new TypeReference<DyResult<RenewRefreshTokenResponse>>() {
                });
        val res = response.getData();

        this.getUserConfig().updateRefreshToken(res.getRefreshToken(), res.getExpiresIn());

        return res;
    }

    public <T> T executeGet(String url, Object queryParams, Map<String, String> headers, TypeReference<T> resultTypeReference) {
        return this.executeCall("GET", url, queryParams, null, headers, resultTypeReference);
    }

    public <T> T executePost(String url, Object queryParams, Object data, Map<String, String> headers, TypeReference<T> resultTypeReference) {
        return this.executeCall("POST", url, queryParams, data, headers, resultTypeReference);
    }

    public <T> T executeCall(String methodStr, String url, Object queryParams, Object data, Map<String, String> headers, TypeReference<T> resultTypeReference) {
        return this.executeCallInternal(methodStr, url, queryParams, data, headers, resultTypeReference);
    }

    protected <T> T executeCallInternal(String methodStr, String url, Object queryParams, Object data, Map<String, String> headers, TypeReference<T> resultTypeReference) {
        try {
            return this.getHttpExecutor().executeCall(methodStr, url, queryParams, data, headers, resultTypeReference);
        } catch (DyException ex) {
            DyError dyError = ex.getError();
            // 若 access_token 已过期，调用接口会报错（error_code=10008 或 2190008），refresh_token 后会获取一个新的 access_token 以及新的超时时间。
            if (DyWebErrorEnum.access_token无效或过期.getErrorCode().equals(dyError.getErrorCode())
                    || DyWebErrorEnum.access_token过期请刷新或重新授权.getErrorCode().equals(dyError.getErrorCode())
            ) {
                this.getUserConfig().expireAccessToken();
                return this.executeCall(methodStr, url, queryParams, data, headers, resultTypeReference);
            }
            // 若 refresh_token 过期，获取 access_token 会报错（error_code=10010），此时需要重新走用户授权流程。
            if (DyWebErrorEnum.refresh_token无效或过期.getErrorCode().equals(dyError.getErrorCode())
            ) {
                this.getUserConfig().expireRefreshToken();
            }
            throw ex;
        }
    }
}
