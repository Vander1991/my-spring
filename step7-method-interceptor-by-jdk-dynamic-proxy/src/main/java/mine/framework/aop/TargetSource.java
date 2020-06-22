package mine.framework.aop;

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
    /**
     * 被代理对象的类信息
     */
    private Class<?> targetClass;
    /**
     * 被代理对象
     */
    private Object target;

}
