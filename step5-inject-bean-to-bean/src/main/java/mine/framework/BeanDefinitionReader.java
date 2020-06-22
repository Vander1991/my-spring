package mine.framework;

import java.util.Map;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 */
public interface BeanDefinitionReader {

    Map<String, BeanDefinition> getRegistry();

    void loadBeanDefinition(String location) throws Exception;

}
