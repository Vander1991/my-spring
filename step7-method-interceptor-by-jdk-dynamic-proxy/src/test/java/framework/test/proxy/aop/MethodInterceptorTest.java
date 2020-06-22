package framework.test.proxy.aop;

import framework.test.intercept.TimeMethodInterceptor;
import framework.test.model.Student;
import framework.test.proxy.reflect.StudentService;
import framework.test.proxy.reflect.StudentServiceImpl;
import lombok.ToString;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;

/**
 * @author : Vander
 * @date :   2020/6/2
 * @description :
 */
public class MethodInterceptorTest {

    @Test
    public void test() throws Throwable {
        Student jason = new Student("2141130401", "Jason");
        StudentService studentServiceImpl = new StudentServiceImpl(jason);
        MethodInterceptor timeMethodInterceptor = new TimeMethodInterceptor();
        MethodInvocation methodInvocation = new MethodInvocationImpl(
                StudentService.class.getMethod("goToClass"), null, studentServiceImpl);
        timeMethodInterceptor.invoke(methodInvocation);
    }

    @Test
    public void test2() throws Throwable {
        TeacherService teacherService = new TeacherService();
        MethodInterceptor timeMethodInterceptor = new TimeMethodInterceptor();
        MethodInvocation methodInvocation = new MethodInvocationImpl(
                TeacherService.class.getMethod("havingClass"), null, teacherService);
        timeMethodInterceptor.invoke(methodInvocation);
    }

}
