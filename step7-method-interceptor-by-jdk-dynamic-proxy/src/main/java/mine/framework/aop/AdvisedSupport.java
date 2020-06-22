package mine.framework.aop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author : Vander
 * @date :   2020/6/2
 * @description :
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AdvisedSupport {

    private MethodInterceptor methodInterceptor;

    private TargetSource targetSource;

}
