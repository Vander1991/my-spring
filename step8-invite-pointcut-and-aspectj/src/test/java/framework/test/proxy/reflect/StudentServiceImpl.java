package framework.test.proxy.reflect;

import framework.test.model.Student;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author : Vander
 * @date :   2020/6/1
 * @description :
 */
@AllArgsConstructor
@Setter
public class StudentServiceImpl implements StudentService {

    private List<Student> students;

    public void goToClass() {
        for (Student stu : students) {
            System.out.println(stu.getStuName() + " go to class!");
        }
    }

    public void sayHello() {
        System.out.println("hello~");
    }

}
