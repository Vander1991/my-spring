package framework.test.proxy;

import framework.test.intercept.TimeMethodInterceptor;
import framework.test.model.Student;
import framework.test.proxy.reflect.StudentService;
import framework.test.proxy.reflect.StudentServiceImpl;
import mine.framework.aop.framework.AdvisedSupport;
import mine.framework.aop.framework.JdkDynamicAopProxy;
import mine.framework.aop.framework.TargetSource;
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
        List<Student> students = new ArrayList<>();
        students.add(stu1);
        StudentServiceImpl studentService = new StudentServiceImpl(students);

        // 1. 设置被代理对象(JoinPoint)
        TargetSource targetSource = new TargetSource(studentService, new Class[]{StudentService.class});
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(targetSource);
        // 2. 设置拦截器(Advice)
        TimeMethodInterceptor timeMethodInterceptor = new TimeMethodInterceptor();
        advisedSupport.setMethodInterceptor(timeMethodInterceptor);
        // 3. 创建代理(Proxy)
        StudentService studentServiceProxy = (StudentService) (new JdkDynamicAopProxy(advisedSupport)).getProxy();
        // 4. 基于AOP的调用
        studentServiceProxy.goToClass();
    }

}
