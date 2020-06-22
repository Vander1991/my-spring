package framework.test.proxy;

import framework.test.intercept.HelloMethodInterceptor;
import framework.test.intercept.TimeMethodInterceptor;
import framework.test.model.Student;
import framework.test.proxy.reflect.StudentService;
import framework.test.proxy.reflect.StudentServiceImpl;
import mine.framework.aop.AdvisedSupport;
import mine.framework.aop.JdkDynamicAopProxy;
import mine.framework.aop.TargetSource;
import mine.framework.context.ApplicationContext;
import mine.framework.context.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vander
 * @date :   2020/6/2
 * @description :
 */
public class JdkDynamicAopProxyTest {

    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("tiny-ioc.xml");
        Student stu1 = (Student) applicationContext.getBean("stu1");
        StudentServiceImpl studentService = new StudentServiceImpl(stu1);

        // 1. 设置被代理对象(JoinPoint)
        TargetSource targetSource = new TargetSource(StudentService.class, studentService);
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(targetSource);
        // 2. 设置拦截器(Advice)
        TimeMethodInterceptor timeMethodInterceptor = new TimeMethodInterceptor();
        HelloMethodInterceptor helloMethodInterceptor = new HelloMethodInterceptor();
        advisedSupport.setMethodInterceptor(helloMethodInterceptor);
        // 3. 创建代理(Proxy)
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        StudentService studentServiceImpl = (StudentService) jdkDynamicAopProxy.getProxy();
        // 4. 基于AOP的调用
        studentServiceImpl.goToClass();
        studentServiceImpl.doSomeSport();
    }

}
