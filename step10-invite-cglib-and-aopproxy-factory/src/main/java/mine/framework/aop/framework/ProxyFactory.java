package mine.framework.aop.framework;

import mine.framework.aop.AopProxy;

/**
 * @author yihua.huang@dianping.com
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {

	@Override
	public Object getProxy() {
		return createAopProxy().getProxy();
	}

	protected final AopProxy createAopProxy() {
		return new CglibAopProxy(this);
	}
}
