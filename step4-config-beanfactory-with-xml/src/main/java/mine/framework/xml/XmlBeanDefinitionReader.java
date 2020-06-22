package mine.framework.xml;

import mine.framework.AbstractBeanDefinitionReader;
import mine.framework.BeanDefinition;
import mine.framework.PropertyValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description : 完成从Xml中读取Bean的信息，生成BeanDefinition放入到
 * 抽象类的registry(BeanDefinitionMap)中
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    @Override
    public void loadBeanDefinition(String location) throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(location);
        // 读取Xml并注册BeanDefinition
        doLoadBeanDefinitions(resourceAsStream);
    }

    public void doLoadBeanDefinitions(InputStream xmlFileInputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document document = docBuilder.parse(xmlFileInputStream);
        registerBeanDefinitions(document);
        xmlFileInputStream.close();
    }

    public void registerBeanDefinitions(Document document) {
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    public void parseBeanDefinitions(Element root) {
        NodeList beansNode = root.getChildNodes();
        int length = beansNode.getLength();
        for (int i = 0; i < length; i++) {
            Node node= beansNode.item(i);
            if(node instanceof Element) {
                Element beanEle = (Element)node;
                processBeanDefinition(beanEle);
            }
        }
    }

    public void processBeanDefinition(Element ele) {
        BeanDefinition beanDefinition = new BeanDefinition();
        // 处理Bean标签
        String beanName = ele.getAttribute("name");
        String beanClassName = ele.getAttribute("class");
        beanDefinition.setBeanClassName(beanClassName);
        // 处理xml中的bean标签下的属性标签
        processProperty(ele, beanDefinition);
        // 从Xml中解析出来的BeanDefinition注册到registry中
        registry.put(beanName, beanDefinition);
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        // 从xml中读取属性到BeanDefinition
        NodeList beanPropertyNodes = ele.getChildNodes();
        for (int i = 0; i < beanPropertyNodes.getLength(); i++) {
            Node beanPropertyNode = beanPropertyNodes.item(i);
            if(beanPropertyNode instanceof Element) {
                Element beanPropertyEle = (Element)beanPropertyNode;
                String propertyName = beanPropertyEle.getAttribute("name");
                String propertyValue = beanPropertyEle.getAttribute("value");
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(propertyName, propertyValue));
            }
        }
    }

}
