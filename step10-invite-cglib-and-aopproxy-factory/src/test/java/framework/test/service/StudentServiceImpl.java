package framework.test.service;

import framework.test.model.Student;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Vander
 * @date :   2020/6/1
 * @description :
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class StudentServiceImpl implements StudentService {

    private Student student;

    public void goToClass() {
        System.out.println(student.getStuName() + " go to class!");
    }

    public void doSomeSport() {
        System.out.println(student.getStuName() + " do some sport!");
    }

}
