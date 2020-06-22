package mine.framework.aop.framework;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Vander
 * @date :   2020/6/2
 * @description :
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TargetSource {

    private Object target;

    private Class<?>[] targetClass;

}
