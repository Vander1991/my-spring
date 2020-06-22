package mine.framework.factory;

import mine.framework.BeanDefinition;
import mine.framework.BeanReference;
import mine.framework.PropertyValue;

import java.lang.reflect.Field;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description : 增加反射调用设置Bean的属性
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object doCreateBean(BeanDefinition bd) throws Exception {
        Object bean = createBeanInstance(bd);
        bd.setBean(bean);
        applyPropertyValues(bean, bd);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            Class<?> beanClass = bean.getClass();
            try {
                Field field = beanClass.getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                Object value = propertyValue.getValue();
                if(value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference)value;
                    value = getBean(beanReference.getName());
                }
                field.set(bean, value);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
