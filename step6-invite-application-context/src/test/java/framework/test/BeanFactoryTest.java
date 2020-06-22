package framework.test;

import framework.test.model.Classroom;
import framework.test.model.Student;
import mine.framework.context.AbstractApplicationContext;
import mine.framework.context.ApplicationContext;
import mine.framework.context.ClassPathXmlApplicationContext;
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

    private AbstractApplicationContext applicationContext;

    @Before
    public void testConstructor() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("tiny-ioc.xml");
    }

    @Test
    public void testGet() throws Exception {
        // getBean时才进行实例化
        Student stu1 = (Student) applicationContext.getBean("stu1");
        log.info(stu1.toString());
        Classroom class02 = (Classroom) applicationContext.getBean("class-02");
        log.info(class02.toString());
    }

}
