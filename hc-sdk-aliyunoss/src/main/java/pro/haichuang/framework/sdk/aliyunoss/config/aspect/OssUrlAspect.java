package pro.haichuang.framework.sdk.aliyunoss.config.aspect;

import io.jsonwebtoken.lang.Collections;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import pro.haichuang.framework.base.response.vo.MultiVO;
import pro.haichuang.framework.base.response.vo.PageVO;
import pro.haichuang.framework.base.response.vo.SingleVO;
import pro.haichuang.framework.sdk.aliyunoss.annotation.EnableOssUrlConvert;
import pro.haichuang.framework.sdk.aliyunoss.annotation.OssUrl;
import pro.haichuang.framework.sdk.aliyunoss.config.properties.AliYunOssProperties;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 阿里云对象存储地址解析切面
 *
 * <p>该类会将带有 {@link EnableOssUrlConvert @EnableOssUrlConvert} 的请求方法中带有 {@link OssUrl @OssUrl}参数进行解析,
 * 入参时去掉其访问前缀, 出参时加上其访问前缀, 避免过多人工干预
 * <p>注意: {@link OssUrl @OssUrl} 注解只支持加载 {@link String} 类型的字段或请求方法形参中, 否则将不生效
 * <hr>
 * <p> {@link EnableOssUrlConvert @EnableOssUrlConvert} 注解与 {@link OssUrl @OssUrl} 注解必须同时使用才会生效,
 * 当请求方法形参为复杂对象时将会采用反射机制进行改变其字段的值
 * <hr>
 * <p>ps: 加 {@link EnableOssUrlConvert @EnableOssUrlConvert} 是为了将转换控制全权交由开发灵活控制, 避免AOP减慢不需要转换方法的处理时间
 *
 * @author JiYinchuan
 * @see EnableOssUrlConvert
 * @since 1.1.0.211021
 */
@Aspect
public class OssUrlAspect {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private AliYunOssProperties aliYunOssProperties;

    @Around("@annotation(pro.haichuang.framework.sdk.aliyunoss.annotation.EnableOssUrlConvert)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        String addPrefixUrl = aliYunOssProperties.getBucketDomain();
        String delPrefixUrl = aliYunOssProperties.getBucketDomain();

        // 处理请求参数
        Object[] args = point.getArgs();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                if (args[i] instanceof String && parameters[i].getAnnotation(OssUrl.class) != null) {
                    String tempUrl = (String) args[i];
                    args[i] = tempUrl.replace(delPrefixUrl, "");
                } else {
                    ossUrlConvert(args[i], true, addPrefixUrl);
                }
            }
        }

        Object proceed = point.proceed();

        // 处理响应结果
        if (proceed instanceof SingleVO) {
            ossUrlConvert(((SingleVO<?>) proceed).getData(), false, addPrefixUrl);
        } else if (proceed instanceof MultiVO) {
            Collection<?> collection = ((MultiVO<?>) proceed).getData();
            if (!Collections.isEmpty(collection)) {
                for (Object obj : collection) {
                    ossUrlConvert(obj, false, addPrefixUrl);
                }
            }
        } else if (proceed instanceof PageVO) {
            Collection<?> collection = ((PageVO<?>) proceed).getData();
            if (!Collections.isEmpty(collection)) {
                for (Object obj : collection) {
                    ossUrlConvert(obj, false, addPrefixUrl);
                }
            }
        }
        return proceed;
    }

    /**
     * 将指定对象中带有 {@link OssUrl @OssUrl} 注解的字段默认添加上OSS请求前缀
     *
     * @param obj          调用对象
     * @param isRequestObj 是否为请求对象
     * @param addPrefixUrl 添加的访问前缀
     * @throws InvocationTargetException 调用目标方法异常
     * @throws IllegalAccessException    非法访问异常
     * @throws IntrospectionException    属性描述构造器参数检查异常
     * @since 1.1.0.211021
     */
    private void ossUrlConvert(Object obj, boolean isRequestObj, String addPrefixUrl)
            throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        if (obj != null) {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.getAnnotation(OssUrl.class) != null) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(declaredField.getName(), obj.getClass());
                    Method readMethod = propertyDescriptor.getReadMethod();
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    Object originUrl = readMethod.invoke(obj);
                    if (originUrl != null) {
                        writeMethod.invoke(obj, getNewOssUrl(originUrl, isRequestObj, addPrefixUrl));
                    }
                }
            }
        }
    }

    /**
     * 获取新OSS地址
     *
     * @param originOssUrl 原始OSS地址
     * @param isRequestObj 是否为请求对象
     * @param addPrefixUrl 添加的访问前缀
     * @return 新OSS地址
     * @since 1.1.0.211021
     */
    private Object getNewOssUrl(Object originOssUrl, boolean isRequestObj, String addPrefixUrl) {
        Object newOssUrl = originOssUrl;
        if (originOssUrl instanceof Collection) {
            Collection<?> originOssUrls = (Collection<?>) originOssUrl;
            List<Object> newOssUrls = new ArrayList<>(originOssUrls.size());
            for (Object tempOriginOssUrl : originOssUrls) {
                Object tempOssUrl = tempOriginOssUrl;
                if (tempOriginOssUrl instanceof String) {
                    tempOssUrl = isRequestObj
                            ? ((String) tempOriginOssUrl).replace(addPrefixUrl, "")
                            : addPrefixUrl + tempOriginOssUrl;
                }
                newOssUrls.add(tempOssUrl);
            }
            newOssUrl = newOssUrls;
        } else if (originOssUrl instanceof String) {
            newOssUrl = isRequestObj
                    ? ((String) originOssUrl).replace(addPrefixUrl, "")
                    : addPrefixUrl + originOssUrl;
        }
        return newOssUrl;
    }
}
