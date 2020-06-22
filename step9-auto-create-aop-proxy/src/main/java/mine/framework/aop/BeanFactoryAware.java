package mine.framework.aop;


import mine.framework.beans.factory.BeanFactory;

/**
 * @author yihua.huang@dianping.com
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
