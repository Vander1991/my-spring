package mine.framework.aop;

import lombok.AllArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author : Vander
 * @date :   2020/6/2
 * @description : 使用反射调用method方法
 */
@AllArgsConstructor
public class ReflectMethodInvocation implements MethodInvocation {

    private Method method;

    private Object[] args;

    private Object target;

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return null;
    }
}
