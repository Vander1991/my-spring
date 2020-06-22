package mine.framework;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 */
@Getter
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry;

    public AbstractBeanDefinitionReader() {
        this.registry = new HashMap<>();
    }

}
