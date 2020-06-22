## step7-method-interceptor-by-jdk-dynamic-proxy
    说明：JDK Dynamic Proxy+Aopalliance实现不同的MethodInterceptor来增强被代理类
### 功能描述
    1）此处既用到了动态代理，又用到了aopalliance
    2）当代理对象被当做真实对象执行相关方法时，会进入到以下的调用链路
      JdkDynamicAopProxy#invoke->
	       MethodInterceptor#invoke->
		    MethodInvocation#proceed->
			    Method#invoke(target, args)
    3）JdkDynamicAopProxy#getProxy根据接口生成代理对象，当代理对象Proxy调用方法时，会调用JdkDynamicAopProxy#invoke，而invoke里创建
    ReflectiveMethodInvocation，并且拿到对应的MethodInterceptor，调用MethodInterceptor#invoke(methodInvocation)然后会调用
    methodInvocation#proceed->method.proceed(target, args)
    4）其实直接用aopalliance或动态代理都能起到增强被代理类的作用，但是结合起来，只需要替换掉AdvisedSupport中的MethodInterceptor即可实
    现不同功能的增强类