## step8-invite-pointcut-and-aspectj
    说明：引入AspectJ表达式来判断Bean的方法是否需要切入；增加BeanPostProcessor，在实例化Bean后，再对Bean进行初始化的操作
### 功能描述
    1. 引入AspectJ实现方法过滤、类过滤
    2. BeanPostProcessor是在反射创建完Bean之后，对Bean再进行一些额外的操作
    3. new ClassPathXmlApplication(location)的主要调用链路如下
        ClassPathXmlApplicationContext Constructor->
	        AbstractApplicationContext#refresh->
		        loadBeanDefinition、registerBeanPostProcessors、onRefresh->
			        AbstractBeanFactory#preInstantiateSingletons->
				        AbstractBeanFactory#getBean->
					        doCreateBean、initializeBean（调用BeanPostProcessor method）
###附
####相关概念
    1. Join Point连接点：程序运行中的某个阶段点，比如方法的调用、异常的抛出等。比如方法doSomething()
    2. 通知Advice：某个连接点所采用的处理逻辑，也就是向连接点注入的代码（即增强功能的逻辑）。例如：输出的日志信息就是一个Advice
    3. 切入点Pointcut：Pointcut是JoinPoint的集合，它是程序中需要注入Advice的位置的集合，指明Advice要在什么样的条件下才能被触发。
       org.springframework.aop.Pointcut接口用来指定到特定的类和方法。
    4. Advisor：Advisor提供获取Advice的接口					        