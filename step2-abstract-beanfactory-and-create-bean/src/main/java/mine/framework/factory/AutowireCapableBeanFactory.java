package mine.framework.factory;

import mine.framework.BeanDefinition;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description : 使用JDK反射来实现创建Bean的过程
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object doCreateBean(BeanDefinition bd) {
        Class<?> beanClass = bd.getBeanClass();
        Object bean = null;
        try {
             bean = beanClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return bean;
    }


}
