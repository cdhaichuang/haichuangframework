package pro.haichuang.framework.base.constant;

/**
 * 正则表达式常量
 *
 * <p>该类主要用于存储预置正则表达式信息
 * <p><a href="https://c.runoob.com/front-end/854/">点击查看常用正则表达式</a>
 *
 * @author JiYinchuan
 * @since 1.1.0.211021
 */
public class PatternConstant {

    /**
     * 电话号码正则表达式
     *
     * <p>支持手机号码/3-4位区号/7-8位直播号码/1－4位分机号
     */
    public static final String PHONE = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-" +
            "(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";

    /**
     * 邮箱正则表达式
     */
    public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 身份证号正则表达式
     */
    public static final String ID_NUMBER = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";

    /**
     * 账号正则表达式
     *
     * <p>字母开头, 允许 [5-16] 字节, 允许字母数字下划线
     */
    public static final String ACCOUNT = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";

    /**
     * 密码正则表达式
     *
     * <p>以字母开头, 长度在 [6~18] 之间, 只能包含字母、数字和下划线
     */
    public static final String PASSWORD = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 强密码正则表达式
     *
     * <p>必须包含大小写字母和数字的组合, 不能使用特殊字符, 长度在 [8-10] 之间
     */
    public static final String STRONG_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}$";

    /**
     * 强密码正则表达式
     *
     * <p>必须包含大小写字母和数字的组合, 可以使用特殊字符, 长度在 [8-10] 之间
     */
    public static final String STRONG_PASSWORD_WITH_SPECIAL = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}$";

}
