package framework.test.intercept;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author : Vander
 * @date :   2020/6/17
 * @description :
 */
public class HelloMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("hello ~");
        Object result = invocation.proceed();
        System.out.println("bye bye");
        return result;
    }
}
