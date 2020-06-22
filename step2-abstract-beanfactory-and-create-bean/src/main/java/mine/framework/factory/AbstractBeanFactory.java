package mine.framework.factory;

import mine.framework.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 *  将beanDefinitionMap抽到抽象类中，抽象类实现通用的方法实现，将doCreateBean延迟到子类实现
 *  注册BeanDefinition时，实例化Bean并将其放入Map中
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(name, beanDefinition);
    }

    protected abstract Object doCreateBean(BeanDefinition bd);

}
