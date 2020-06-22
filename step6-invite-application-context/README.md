## step6-invite-application-context
    说明：引入ApplicationContext，组合XmlBeanDefinitionReader与BeanFactory
### 功能描述
    1）新增ApplicationContext接口继承BeanFactory，AbstractApplicationContext装配AbstractBeanFactory，
      ClassPathXmlApplicationContext用于加载xml文件并刷新到AbstractBeanFactory中的BeanDefinitionMap中
    2）BeanFactory定义getBean方法（上一节是定义了getBean和registerBeanDefinition方法），让职责更加明确
    3）将读取xml文件生成BeanDefinitionMap的工作交由AbstractApplicationContext的子类的refresh方法来实现，即
      ClassPathXmlApplicationContext构造时会加载Xml并生成BeanDefinitionMap，并将BeanDefinition放到AbstractBeanFactory中对应的
      map，这样以后getBean就能获取到对应的BeanDefinition然后实例化Bean
    4）AbstractApplicationContext实现了ApplicationContext，而ApplicationContext又实现了BeanFactory，但是
      AbstractApplicationContext还装配了AbstractBeanFactory，这是因为子类ClassPathXmlApplicationContext用到
      AbstractBeanFactory#registerBeanDefinition方法，而AbstractBeanFactory中有Map<String, BeanDefinition>属性，单纯
      BeanFactory的话没法将从xml中读取到的BeanDefinition刷新到此属性中