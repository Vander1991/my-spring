package mine.framework.aop.aspectj;

import mine.framework.aop.BeanFactoryAware;
import mine.framework.aop.framework.ProxyFactory;
import mine.framework.aop.framework.TargetSource;
import mine.framework.beans.BeanPostProcessor;
import mine.framework.beans.factory.AbstractBeanFactory;
import mine.framework.beans.factory.BeanFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * @author yihua.huang@dianping.com
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private AbstractBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        List<AspectJExpressionPointcutAdvisor> advisors
                = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        // 匹配到满足的AspectJExpressionPointcutAdvisor后，生成相应的代理类
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
                ProxyFactory config = new ProxyFactory();
                config.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                config.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                config.setTargetSource(targetSource);
                return config.getProxy();
            }
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }
}
