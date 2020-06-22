package framework.test.proxy.aop;

import java.util.concurrent.TimeUnit;

/**
 * @author : Vander
 * @date :   2020/6/17
 * @description :
 */
public class TeacherService {

    public void havingClass() {
        System.out.println("having class!");
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
