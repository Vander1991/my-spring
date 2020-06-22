package framework.test.proxy.reflect;

import framework.test.model.Student;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vander
 * @date :   2020/6/1
 * @description :
 */
public class StudentServiceProxyTest {

    @Test
    public void testProxy() {
        Student stu1 = new Student("2141130401", "Jason");
        List<Student> students = new ArrayList<>();
        students.add(stu1);
        StudentService studentServiceImpl = new StudentServiceImpl(students);
        // 代理对象的处理类
        InvocationHandler invocationHandler = new InvocationHandlerImpl(studentServiceImpl);
        // 生成代理对象
        StudentService proxyObject = (StudentService)Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                studentServiceImpl.getClass().getInterfaces(),
                invocationHandler
        );
        proxyObject.goToClass();
    }

}
