package mine.framework.aop.framework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mine.framework.aop.AopProxy;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @author : Vander
 * @date :   2020/6/2
 * @description :
 */
@AllArgsConstructor
@Setter
@Getter
public class JdkDynamicAopProxy implements InvocationHandler, AopProxy {

    private AdvisedSupport advisedSupport;

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                advisedSupport.getTargetSource().getTargetClass(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
        ReflectMethodInvocation reflectMethodInvocation
                = new ReflectMethodInvocation(method, args, advisedSupport.getTargetSource().getTarget());
        Object result = methodInterceptor.invoke(reflectMethodInvocation);
        return result;
    }

}
