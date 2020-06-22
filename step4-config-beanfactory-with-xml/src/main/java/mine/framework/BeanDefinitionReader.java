package mine.framework;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 */
public interface BeanDefinitionReader {

    void loadBeanDefinition(String location) throws Exception;

}
