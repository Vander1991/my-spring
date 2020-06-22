package framework.test.proxy.reflect;

import framework.test.model.Student;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author : Vander
 * @date :   2020/6/1
 * @description :
 */
public class StudentServiceProxyTest {

    /**
     * 生成代理对象
     *
     * @param studentService
     * @return
     */
    private StudentService getProxy(StudentService studentService) {
        // 代理对象的处理类
        InvocationHandler invocationHandler = new InvocationHandlerImpl(studentService);
        // 生成代理对象
        StudentService studentServiceImpl2 = (StudentService)Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                //studentServiceImpl.getClass().getInterfaces(),
                new Class[] {StudentService.class},
                invocationHandler
        );
        return studentServiceImpl2;
    }

    @Test
    public void testProxy() {
        Student jason = new Student("2141130401", "Jason");
        StudentService studentService = new StudentServiceImpl(jason);
        StudentService studentServiceImpl = getProxy(studentService);
        studentServiceImpl.goToClass();
        System.out.println();
        studentServiceImpl.doSomeSport();
    }

}
