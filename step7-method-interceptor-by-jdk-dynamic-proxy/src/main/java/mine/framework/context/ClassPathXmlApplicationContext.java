package mine.framework.context;

import mine.framework.beans.factory.AbstractBeanFactory;
import mine.framework.beans.factory.AutowireCapableBeanFactory;
import mine.framework.beans.factory.config.BeanDefinition;
import mine.framework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author : Vander
 * @date :   2020/6/1
 * @description : 此类用于加载xml文件，并将解析出来的BeanDefinition放到beanDefinitionMap
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void refresh() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader();
        xmlBeanDefinitionReader.loadBeanDefinition(configLocation);
        for (Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            // 此处的注册Bean只是把registry中的BeanDefinition放到BeanFactory中，此时并没有实例化Bean
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
    }

}
