package mine.framework;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description : BeanFactory持有BeanDefinitionMap
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

}
