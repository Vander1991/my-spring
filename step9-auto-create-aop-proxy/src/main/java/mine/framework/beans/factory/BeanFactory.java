package mine.framework.beans.factory;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 */
public interface BeanFactory {

    Object getBean(String beanName) throws Exception;

}