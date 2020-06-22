package mine.framework.context;

import mine.framework.beans.factory.AbstractBeanFactory;

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

    protected abstract void refresh() throws Exception;

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

}
