package pro.haichuang.framework.base.config.interceptor;

/**
 * 自定义权限验证
 *
 * @author JiYinchuan
 * @version 1.0
 */
public abstract class AbstractSecurityValidate {

    /**
     * 角色前置拦截
     *
     * @param roles 允许的角色名称
     * @return 拦截结果
     */
    public abstract boolean preRole(String[] roles);

    /**
     * 权限前置拦截
     *
     * @param permissions 允许的权限名称
     * @return 拦截结果
     */
    public abstract boolean prePermission(String[] permissions);

}
