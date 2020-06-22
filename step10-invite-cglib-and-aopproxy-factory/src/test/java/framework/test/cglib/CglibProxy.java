package framework.test.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : Vander
 * @date :   2020/6/19
 * @description :
 */
public class CglibProxy implements MethodInterceptor {

    public Object getProxy(Object targetObj) {
        Enhancer enhancer = new Enhancer();
        Class<?>[] interfaces = targetObj.getClass().getInterfaces();
        if ((interfaces).length > 0) {
            enhancer.setInterfaces(interfaces);
        }
        enhancer.setSuperclass(targetObj.getClass());
        enhancer.setCallback(this);
        Object proxy = enhancer.create();
        return proxy;
    }

    public Object getProxy(Object targetObj, Class[] argumentTypes, Object[] arguments) {
        Enhancer enhancer = new Enhancer();
        Class<?>[] interfaces = targetObj.getClass().getInterfaces();
        if ((interfaces).length > 0) {
            enhancer.setInterfaces(interfaces);
        }
        enhancer.setSuperclass(targetObj.getClass());
        enhancer.setCallback(this);
        Object proxy = enhancer.create(argumentTypes, arguments);
        return proxy;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("——————调用真实对象前");
        Object result = proxy.invokeSuper(obj, args);
        // 使用此方法调用会陷入无限循环
//        Object result = method.invoke(obj, args);
        System.out.println("——————调用真实对象后");
        return result;
    }
}
