package mine.framework.aop.framework;

import lombok.AllArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author : Vander
 * @date :   2020/6/2
 * @description :
 */
@AllArgsConstructor
public class ReflectiveMethodInvocation implements MethodInvocation {

    protected Object target;

    protected Method method;

    protected Object[] args;

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
