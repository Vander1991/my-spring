package framework.test.model;

import lombok.*;

/**
 * @author : Vander
 * @date :   2020/5/30
 * @description :
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Classroom {

    private Student student;

    public void say() {
        System.out.println(student.getStuName() + " is in my Classroom");
    }

}
