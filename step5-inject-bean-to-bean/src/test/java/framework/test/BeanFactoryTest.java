package framework.test;

import framework.test.model.Classroom;
import framework.test.model.Student;
import mine.framework.BeanDefinition;
import mine.framework.factory.AbstractBeanFactory;
import mine.framework.factory.AutowireCapableBeanFactory;
import mine.framework.xml.XmlBeanDefinitionReader;
import org.junit.Before;
import org.junit.Test;
import szu.vander.log.Logger;

import java.util.Map;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 */
public class BeanFactoryTest {

    private static final Logger log = new Logger();

    private AbstractBeanFactory beanFactory;

    @Before
    public void testRegister() throws Exception {
        // XmlBeanDefinitionReader负责解析出xml生成BeanDefinition
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader();
        xmlBeanDefinitionReader.loadBeanDefinition("tiny-ioc.xml");
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        // 把registry中的BeanDefinition拿出来放到BeanFactory中，下一节，下面的部分将交由ApplicationContext的子类完成
        beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> entry : registry.entrySet()) {
            // 此处的注册Bean只是把registry中的BeanDefinition放到BeanFactory中，此时并没有实例化Bean
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void testGet() throws Exception {
        // getBean时才进行实例化
        Student stu1 = (Student)beanFactory.getBean("stu1");
        log.info(stu1.toString());
        Classroom class02 = (Classroom)beanFactory.getBean("class-02");
        log.info(class02.toString());
    }

    @Test
    public void testPreInstantiate() throws Exception {
        // 调用preInstantiate也能达到实例化的目的
        beanFactory.preInstantiateSingletons();
    }

}
