package mine.framework;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yihua.huang@dianping.com
 */
@Setter
@Getter
public class BeanReference {

    private String name;

    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

}
