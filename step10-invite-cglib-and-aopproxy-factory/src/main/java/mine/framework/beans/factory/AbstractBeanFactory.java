package mine.framework.beans.factory;

import mine.framework.beans.BeanPostProcessor;
import mine.framework.beans.factory.config.BeanDefinition;

import java.util.*;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 *  将beanDefinitionMap抽到抽象类中，抽象类实现通用的方法实现，将doCreateBean延迟到子类实现
 *  注册BeanDefinition时，实例化Bean并将其放入Map中
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private final List<String> beanDefinitionNames = new ArrayList<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * 调用此方法才将BeanDefinition中的Bean实例化
     *
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
            // 调用BeanPostProcessor的postProcessBeforeInitialization和postProcessAfterInitialization方法
            bean = initializeBean(bean, name);
            // 将代理过的Bean放回容器
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    protected Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        // TODO:call initialize method
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    /**
     * register仅仅是把解析后的BeanDefinition放入Map中
     *
     * @param name
     * @param beanDefinition
     * @throws Exception
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    /**
     * 预实例化
     *
     * @throws Exception
     */
    public void preInstantiateSingletons() throws Exception {
        for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
            String beanName = (String) it.next();
            getBean(beanName);
        }
    }

    /**
     * 创建Bean并使Bean的属性有效化
     *
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    /**
     * 有效化属性的接口，留待子类实现
     *
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    protected abstract void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception;

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 根据Bean的class类型获取Beans
     *
     * @param type
     * @return
     * @throws Exception
     */
    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList<>();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }

}
