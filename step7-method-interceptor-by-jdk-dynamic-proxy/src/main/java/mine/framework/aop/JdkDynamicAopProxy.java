package mine.framework.aop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
                new Class[]{advisedSupport.getTargetSource().getTargetClass()},
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
        ReflectMethodInvocation reflectMethodInvocation
                = new ReflectMethodInvocation(method, args, advisedSupport.getTargetSource().getTarget());
        // 此处不直接调用method方法，而要利用ReflectMethodInvocation来调用被代理对象的真实方法
        // 因为要用方法过滤器来在调用被代理对象方法的前后做一些事情
        Object result = methodInterceptor.invoke(reflectMethodInvocation);
        return result;
    }

}
