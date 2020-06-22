package mine.framework;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description : BeanDefinition-描述Bean的类
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

}
