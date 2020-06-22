package mine.framework.factory;

import mine.framework.BeanDefinition;
import mine.framework.PropertyValue;

import java.lang.reflect.Field;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description : 增加反射调用设置Bean的属性
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object doCreateBean(BeanDefinition bd) {
        Class<?> beanClass = bd.getBeanClass();
        Object bean = null;
        try {
             bean = beanClass.newInstance();
             applyPropertyValues(bean, bd);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            Class<?> beanClass = bean.getClass();
            try {
                Field field = beanClass.getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                field.set(bean, propertyValue.getValue());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
