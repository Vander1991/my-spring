package mine.framework.factory;

import mine.framework.BeanDefinition;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 */
public interface BeanFactory {

    Object getBean(String beanName) throws Exception;

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;

}
