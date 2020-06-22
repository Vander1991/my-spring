package mine.framework.aop.framework;

import lombok.AllArgsConstructor;
import mine.framework.aop.AopProxy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yihua.huang@dianping.com
 */
@AllArgsConstructor
public class CglibAopProxy implements AopProxy {

	private AdvisedSupport advised;

	@Override
	public Object getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(advised.getTargetSource().getTargetClass());
		enhancer.setInterfaces(advised.getTargetSource().getInterfaces());
		enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
		Object enhanced = enhancer.create();
		return enhanced;
	}

	private static class DynamicAdvisedInterceptor implements MethodInterceptor {

		private AdvisedSupport advised;

		private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

		private DynamicAdvisedInterceptor(AdvisedSupport advised) {
			this.advised = advised;
			this.delegateMethodInterceptor = advised.getMethodInterceptor();
		}

		@Override
		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			// 用于执行被代理对象的方法
			CglibMethodInvocation cglibMethodInvocation
					= new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, proxy);
			if (advised.getMethodMatcher() == null
					|| advised.getMethodMatcher().matches(method, advised.getTargetSource().getTargetClass())) {
				return delegateMethodInterceptor.invoke(cglibMethodInvocation);
			}
			return cglibMethodInvocation.proceed();
		}
	}

	/**
	 * AOP的MethodInvocation的实现类，用于执行被代理对象的方法
	 */
	private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

		private final MethodProxy methodProxy;

		public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
			super(target, method, args);
			this.methodProxy = methodProxy;
		}

		/**
		 * 此处若不使用methodProxy.invoke调用，而直接用method.invoke将会一直被拦截
		 *
		 * @return
		 * @throws Throwable
		 */
		@Override
		public Object proceed() throws Throwable {
			return methodProxy.invoke(target, args);
		}
	}

}
