package mine.framework.beans.factory;

import mine.framework.aop.BeanFactoryAware;
import mine.framework.beans.PropertyValue;
import mine.framework.beans.factory.config.BeanDefinition;
import mine.framework.beans.factory.config.BeanReference;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description : 增加反射调用设置Bean的属性
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        // 若Xml中配置的Bean的类型是BeanFactoryAware就将BeanFactory放入，所以注入的AspectJAwareAdvisorAutoProxyCreator对象
        // 的beanFactory属性才不为null
        if (bean instanceof BeanFactoryAware) {
            // 所以这个方法没有抽象到父类，由父类做默认实现
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            Class<?> beanClass = bean.getClass();
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            try {
                // 修改为优先使用反射调用set方法来进行属性值设值，找不到set方法才进行field的反射设值
                String methodName = "set"
                        + propertyValue.getName().substring(0, 1).toUpperCase()
                        + propertyValue.getName().substring(1);
                Method declaredMethod = beanClass.getDeclaredMethod(methodName, value.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }

}
