package mine.framework.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class PropertyValue {

    private String name;

    private Object value;
}
