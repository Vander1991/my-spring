package framework.test.xml;

import mine.framework.BeanDefinition;
import mine.framework.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 */
public class XmlBeanDefinitionReaderTest {

    @Test
    public void testXmlParse() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader();
        xmlBeanDefinitionReader.loadBeanDefinition("tiny-ioc.xml");
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        System.out.println(registry);
    }

    @Test
    public void test() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader();
        xmlBeanDefinitionReader.loadBeanDefinition("tiny-ioc.xml");
    }

}
