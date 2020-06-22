package mine.framework.beans.factory;

import mine.framework.beans.factory.config.BeanDefinition;

import java.util.*;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 *  将beanDefinitionMap抽到抽象类中，抽象类实现通用的方法实现，将doCreateBean延迟到子类实现
 *  注册BeanDefinition时，实例化Bean并将其放入Map中
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private final List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            // 反射实例化Bean，并设置属性值
            bean = doCreateBean(beanDefinition);
        }
        return bean;
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    public void preInstantiateSingletons() throws Exception {
        for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
            String beanName = (String) it.next();
            // 实例化Bean
            getBean(beanName);
        }
    }

    protected abstract Object doCreateBean(BeanDefinition bd) throws Exception;

}
