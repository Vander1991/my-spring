package framework.test;

import framework.test.model.Student;
import mine.framework.BeanDefinition;
import mine.framework.factory.AutowireCapableBeanFactory;
import mine.framework.factory.BeanFactory;
import org.junit.Before;
import org.junit.Test;
import szu.vander.log.Logger;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 */
public class BeanFactoryTest {

    private static final Logger log = new Logger();

    private BeanFactory beanFactory;

    @Before
    public void testRegister() {
        BeanDefinition studentDefinition = new BeanDefinition();
        studentDefinition.setBeanClassName("framework.test.model.Student");
        beanFactory = new AutowireCapableBeanFactory();
        beanFactory.registerBeanDefinition("stu1", studentDefinition);
    }

    @Test
    public void testGet() {
        Student stu1 = (Student)beanFactory.getBean("stu1");
        log.info(stu1.toString());
    }

}
