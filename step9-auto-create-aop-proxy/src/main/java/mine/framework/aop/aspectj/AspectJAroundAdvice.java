package mine.framework.aop.aspectj;

import lombok.Getter;
import lombok.Setter;
import mine.framework.beans.factory.BeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author yihua.huang@dianping.com
 */
@Getter
@Setter
public class AspectJAroundAdvice implements Advice, MethodInterceptor {

	private BeanFactory beanFactory;

	private Method aspectJAdviceMethod;

	private String aspectInstanceName;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
        return aspectJAdviceMethod.invoke(beanFactory.getBean(aspectInstanceName), invocation);
	}

}
