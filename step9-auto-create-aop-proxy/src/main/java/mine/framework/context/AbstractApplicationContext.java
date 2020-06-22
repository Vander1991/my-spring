package mine.framework.context;

import mine.framework.beans.BeanPostProcessor;
import mine.framework.beans.factory.AbstractBeanFactory;

import java.util.List;

/**
 * @author : Vander
 * @date :   2020/6/1
 * @description : refresh的意思是相当于把用XmlBeanDefinitionReader读取到的BeanDefinition刷到BeanFactory中
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected void refresh() throws Exception {
        // 从Xml中读取到的Map<String, BeanDefinition>转移到beanFactory中的Map
        loadBeanDefinitions(beanFactory);
        // 将BeanFactory中的Map<String, BeanDefinition>中的BeanPostProcessor放到BeanFactory的List<BeanPostProcessor>
        registerBeanPostProcessors(beanFactory);
        // 实例化BeanFactory的BeanDefinitionMap中的Bean，并调用BeanPostProcessor的相关方法
        onRefresh();
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    protected void onRefresh() throws Exception {
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

}
