package pro.haichuang.sdk.wxmp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import pro.haichuang.framework.base.enums.BaseEnum;
import pro.haichuang.framework.base.enums.base.SexEnum;

import java.util.List;

/**
 * 微信用户信息
 * {
 * "subscribe": 1,
 * "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M",
 * "nickname": "Band",
 * "sex": 1,
 * "language": "zh_CN",
 * "city": "广州",
 * "province": "广东",
 * "country": "中国",
 * "headimgurl":"http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
 * "subscribe_time": 1382694957,
 * "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
 * "remark": "",
 * "groupid": 0,
 * "tagid_list":[128,2],
 * "subscribe_scene": "ADD_SCENE_QR_CODE",
 * "qr_scene": 98765,
 * "qr_scene_str": ""
 * }
 *
 * @author JiYinchuan
 * @version 1.0
 */
public class UserInfoDTO {

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
     */
    private Integer subscribe;

    /**
     * 用户的标识，对当前公众号唯一
     */
    private String openId;

    /**
     * 用户的昵称
     */
    private String nickname;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private SexEnum sex;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    private String headImageUrl;

    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    private Long subscribeTime;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
     */
    private String unionId;

    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;

    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    private Integer groupId;

    /**
     * 用户被打上的标签ID列表
     */
    private List<Integer> tagIdList;

    /**
     * 用户关注的渠道来源
     */
    private SubscribeSceneEnum subscribeScene;

    /**
     * 二维码扫码场景（开发者自定义）
     */
    private Integer qrScene;

    /**
     * 二维码扫码场景描述（开发者自定义）
     */
    private String qrSceneStr;

    /**
     * 微信用户关注的渠道来源枚举
     */
    public enum SubscribeSceneEnum implements BaseEnum {

        /**
         * 公众号搜索
         */
        ADD_SCENE_SEARCH("ADD_SCENE_SEARCH", "公众号搜索"),

        /**
         * 公众号迁移
         */
        ADD_SCENE_ACCOUNT_MIGRATION("ADD_SCENE_ACCOUNT_MIGRATION", "公众号迁移"),

        /**
         * 名片分享
         */
        ADD_SCENE_PROFILE_CARD("ADD_SCENE_PROFILE_CARD", "名片分享"),

        /**
         * 扫描二维码
         */
        ADD_SCENE_QR_CODE("ADD_SCENE_QR_CODE", "扫描二维码"),

        /**
         * 图文页内名称点击
         */
        ADD_SCENE_PROFILE_LINK("ADD_SCENE_PROFILE_LINK", "图文页内名称点击"),

        /**
         * 图文页右上角菜单
         */
        ADD_SCENE_PROFILE_ITEM("ADD_SCENE_PROFILE_ITEM", "图文页右上角菜单"),

        /**
         * 支付后关注
         */
        ADD_SCENE_PAID("ADD_SCENE_PAID", "支付后关注"),

        /**
         * 微信广告
         */
        ADD_SCENE_WECHAT_ADVERTISEMENT("ADD_SCENE_WECHAT_ADVERTISEMENT", "微信广告"),

        /**
         * 其他
         */
        ADD_SCENE_OTHERS("ADD_SCENE_OTHERS", "其他");

        /**
         * 枚举值
         */
        private final String value;

        /**
         * 枚举信息
         */
        private final String reasonPhrase;

        /**
         * 构造器
         *
         * @param value        枚举值
         * @param reasonPhrase 枚举信息
         */
        SubscribeSceneEnum(String value, String reasonPhrase) {
            this.value = value;
            this.reasonPhrase = reasonPhrase;
        }

        @Override
        public String value() {
            return this.value;
        }

        @Override
        public String getReasonPhrase() {
            return this.reasonPhrase;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        public static SubscribeSceneEnum resolve(String value) {
            return BaseEnum.resolve(value, SubscribeSceneEnum.class);
        }
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public UserInfoDTO setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public UserInfoDTO setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserInfoDTO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public SexEnum getSex() {
        return sex;
    }

    public UserInfoDTO setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserInfoDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserInfoDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public UserInfoDTO setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public UserInfoDTO setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public UserInfoDTO setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
        return this;
    }

    public Long getSubscribeTime() {
        return subscribeTime;
    }

    public UserInfoDTO setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
        return this;
    }

    public String getUnionId() {
        return unionId;
    }

    public UserInfoDTO setUnionId(String unionId) {
        this.unionId = unionId;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public UserInfoDTO setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public UserInfoDTO setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    public UserInfoDTO setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
        return this;
    }

    public SubscribeSceneEnum getSubscribeScene() {
        return subscribeScene;
    }

    public UserInfoDTO setSubscribeScene(SubscribeSceneEnum subscribeScene) {
        this.subscribeScene = subscribeScene;
        return this;
    }

    public Integer getQrScene() {
        return qrScene;
    }

    public UserInfoDTO setQrScene(Integer qrScene) {
        this.qrScene = qrScene;
        return this;
    }

    public String getQrSceneStr() {
        return qrSceneStr;
    }

    public UserInfoDTO setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr;
        return this;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "subscribe=" + subscribe +
                ", openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", language='" + language + '\'' +
                ", headImageUrl='" + headImageUrl + '\'' +
                ", subscribeTime=" + subscribeTime +
                ", unionId='" + unionId + '\'' +
                ", remark='" + remark + '\'' +
                ", groupId=" + groupId +
                ", tagIdList=" + tagIdList +
                ", subscribeScene=" + subscribeScene +
                ", qrScene=" + qrScene +
                ", qrSceneStr='" + qrSceneStr + '\'' +
                '}';
    }
}
