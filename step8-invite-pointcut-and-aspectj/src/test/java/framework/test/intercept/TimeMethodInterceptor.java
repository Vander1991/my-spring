package framework.test.intercept;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.time.Duration;
import java.time.Instant;

/**
 * @author : Vander
 * @date :   2020/6/1
 * @description :
 */
public class TimeMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Instant sendBefore = Instant.now();
        // 调用被代理对象的方法
        Object proceed = invocation.proceed();
        Instant sendAfter = Instant.now();
        System.out.println("方法耗时：" + Duration.between(sendBefore, sendAfter).toMillis());
        return proceed;
    }

}
