package framework.test.aspectj;

import framework.test.proxy.reflect.StudentServiceImpl;
import mine.framework.aop.aspectj.AspectJExpressionPointcut;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : Vander
 * @date :   2020/6/15
 * @description :
 */
public class AspectJExpressionPointcutTest {

    @Test
    public void testMatchClass() {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* framework.test.proxy.reflect.StudentServiceImpl.*(..))");
        Assert.assertTrue(aspectJExpressionPointcut.matches(StudentServiceImpl.class));
    }

    @Test
    public void testMatchMethod() throws NoSuchMethodException {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* framework.test.proxy.reflect.StudentServiceImpl.hello(..))");
        Assert.assertTrue(
                aspectJExpressionPointcut
                        .matches(StudentServiceImpl.class.getMethod("goToClass", null),
                                StudentServiceImpl.class)
        );
    }

}
