package mine.framework.beans;

/**
 * @author : Vander
 * @date :   2020/6/15
 * @description :
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String name) throws Exception;

    Object postProcessAfterInitialization(Object bean, String name) throws Exception;
}
