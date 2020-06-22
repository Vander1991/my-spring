## step5-inject-bean-to-bean
    说明：实现Bean的依赖注入
### 功能描述
    1）引入BeanReference，区分xml中的property node的ref标签与value标签
    2）XmlBeanDefinitionReader#processProperty增加处理propertyRef的逻辑，并对应生成BeanReference对象
    3）AutowireCapableBeanFactory#doCreateBean封装反射生成对象，将BeanDefinition中的应用propertyValue的逻辑增加BeanReference类
      型的处理
    4）AbstractBeanFactory增加beanDefinitionNames属性，registerBeanDefinition的功能原有的创建实例并放入map改为仅放入map，真正实例
      化Bean的过程推迟到getBean时才完成。增加preInstantiateSingletons，可对所有beanDefinitionNames中的BeanDefinition中的Bean进行
      实例化
