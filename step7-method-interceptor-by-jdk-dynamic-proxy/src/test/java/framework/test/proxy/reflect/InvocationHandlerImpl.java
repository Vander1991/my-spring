package framework.test.proxy.reflect;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : Vander
 * @date :   2020/6/1
 * @description :
 */
@AllArgsConstructor
public class InvocationHandlerImpl implements InvocationHandler {

    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin invoke proxy method");
        Object result = method.invoke(target, args);
        System.out.println("end invoke proxy method");
        return result;
    }

}
