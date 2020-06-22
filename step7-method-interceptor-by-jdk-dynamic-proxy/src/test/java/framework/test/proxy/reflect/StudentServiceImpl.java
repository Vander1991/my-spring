package framework.test.proxy.reflect;

import framework.test.model.Student;
import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * @author : Vander
 * @date :   2020/6/1
 * @description :
 */
@AllArgsConstructor
@Setter
public class StudentServiceImpl implements StudentService {

    private Student stu;

    public void goToClass() {
        System.out.println(stu.getStuName() + " go to class!");
    }

    public void doSomeSport() {
        System.out.println(stu.getStuName() + " do some sport!");
    }

}
