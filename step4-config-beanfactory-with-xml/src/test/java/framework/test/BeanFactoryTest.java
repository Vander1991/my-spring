package framework.test;

import framework.test.model.Student;
import mine.framework.BeanDefinition;
import mine.framework.factory.AutowireCapableBeanFactory;
import mine.framework.factory.BeanFactory;
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

    private BeanFactory beanFactory;

    @Before
    public void testRegister() throws Exception {
        // XmlBeanDefinitionReader负责解析出xml生成BeanDefinition
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader();
        xmlBeanDefinitionReader.loadBeanDefinition("tiny-ioc.xml");
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        // 把registry中的BeanDefinition拿出来放到BeanFactory中
        beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> entry : registry.entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void testGet() {
        Student stu1 = (Student)beanFactory.getBean("stu1");
        log.info(stu1.toString());
    }

}
