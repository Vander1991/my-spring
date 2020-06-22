package mine.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vander
 * @date :   2020/5/29
 * @description :
 */

public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

}
