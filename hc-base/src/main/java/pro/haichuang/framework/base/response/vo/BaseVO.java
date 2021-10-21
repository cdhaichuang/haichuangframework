package pro.haichuang.framework.base.response.vo;

import io.swagger.annotations.ApiModelProperty;
import pro.haichuang.framework.base.enums.BaseEnum;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

/**
 * VO基类
 *
 * <p>该类为全局VO响应基类, 提供了默认响应参数, 用于无数据返回的情况下使用,
 * 使用时必须使用 {@link pro.haichuang.framework.base.response.ResultVO} 中相关方法进行返回
 *
 * @author JiYinchuan
 * @see pro.haichuang.framework.base.response.ResultVO
 * @since 1.1.0.211021
 */
@ApiIgnore
public class BaseVO implements Serializable {
    private static final long serialVersionUID = -4184414745184461718L;

    public static final String ERROR_CODE = "errorCode";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String USER_TIP = "userTip";

    /**
     * 错误码
     */
    @ApiModelProperty("错误码")
    private String errorCode;

    /**
     * 错误信息
     */
    @ApiModelProperty("错误信息")
    private String errorMessage;

    /**
     * 用户提示信息
     */
    @ApiModelProperty("用户提示信息")
    private String userTip;

    public BaseVO(BaseEnum baseEnum) {
        this.errorCode = baseEnum.value();
        this.errorMessage = baseEnum.getReasonPhrase();
    }

    public BaseVO(BaseEnum baseEnum, String userTip) {
        this.errorCode = baseEnum.value();
        this.errorMessage = baseEnum.getReasonPhrase();
        this.userTip = userTip;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUserTip() {
        return userTip;
    }

    public void setUserTip(String userTip) {
        this.userTip = userTip;
    }

    @Override
    public String toString() {
        return "BaseVO{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", userTip='" + userTip + '\'' +
                '}';
    }
}
