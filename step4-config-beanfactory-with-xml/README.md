## step4-config-beanfactory-with-xml
    说明：加载并读取xml文件，生成BeanDefinition
### 功能描述
    1）AbstractBeanDefinitionReader中定义了名为registry的BeanDefinitionMap用来存放从Xml中解析生成的BeanDefinition
    2）XmlBeanDefinitionReader将文件按流读取后，生成Document对象，从Document中读取出BeanDefinition后放入到registry中
    3）到这一步还没有把registry中的BeanDefinition注册到BeanFactory