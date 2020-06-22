package framework.test.proxy.aop;

import framework.test.intercept.TimeMethodInterceptor;
import framework.test.model.Student;
import framework.test.proxy.reflect.StudentService;
import framework.test.proxy.reflect.StudentServiceImpl;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vander
 * @date :   2020/6/2
 * @description :
 */
public class MethodInterceptorTest {

    @Test
    public void test() throws Throwable {
        Student stu1 = new Student("2141130401", "Jason");
        List<Student> students = new ArrayList<>();
        students.add(stu1);
        StudentService studentService = new StudentServiceImpl(students);
        MethodInterceptor timeMethodInterceptor = new TimeMethodInterceptor();
        MethodInvocationImpl methodInvocation = new MethodInvocationImpl(
                StudentService.class.getMethod("goToClass"), null, studentService);
        timeMethodInterceptor.invoke(methodInvocation);
    }

}
