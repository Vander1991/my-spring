package mine.framework;

import lombok.Getter;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description : BeanDefinition-描述Bean的类，增加beanClass、beanClassName两个属性
 */
@Getter
public class BeanDefinition {

    private Object bean;

    private Class<?> beanClass;

    private String beanClassName;

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


}
