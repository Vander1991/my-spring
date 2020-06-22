package framework.test.cglib;

import framework.test.model.Classroom;
import framework.test.model.Student;
import org.junit.Test;

/**
 * @author : Vander
 * @date :   2020/6/19
 * @description :
 */
public class CglibTest {

    @Test
    public void testCglib1() {
        Student jason = new Student("2010130110", "jason");
        Classroom classroom = new Classroom(jason);
        // 报错，空指针，代理出来的classroom2没法student对象
        //Classroom classroom2 = (Classroom)(new CglibProxy().getProxy(classroom));
        CglibProxy cglibProxy = new CglibProxy();
        Classroom classroom2 = (Classroom)cglibProxy
                .getProxy(
                        classroom,
                        new Class[]{Student.class},
                        new Object[]{jason}
                        );
        classroom2.say();
    }

    @Test
    public void testCglib2() {
        Student jason = new Student("2010130110", "Jason");
        Student panda = new Student("2010130110", "Panda");
        Classroom classroom = new Classroom(jason);
        // 报错，空指针，代理出来的classroom2没法student对象
        //Classroom classroom2 = (Classroom)(new CglibProxy().getProxy(classroom));
        CglibProxy cglibProxy = new CglibProxy();
        Classroom classroom2 = (Classroom)cglibProxy
                .getProxy(
                        classroom,
                        new Class[]{Student.class},
                        new Object[]{panda}
                );
        classroom2.say();
    }

}
