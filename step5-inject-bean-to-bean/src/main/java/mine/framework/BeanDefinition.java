package mine.framework;

import lombok.Getter;
import lombok.ToString;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description : BeanDefinition-描述Bean的类，增加propertyValues属性
 */
@ToString
@Getter
public class BeanDefinition {

    private Object bean;

    private Class<?> beanClass;

    private String beanClassName;

    private PropertyValues propertyValues = new PropertyValues();

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        Class<?> beanClass = null;
        try {
            beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.beanClass = beanClass;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

}
