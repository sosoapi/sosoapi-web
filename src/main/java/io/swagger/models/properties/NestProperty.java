package io.swagger.models.properties;

import java.util.HashMap;
import java.util.Map;

/**
 * 
		* <p>Title: 多层嵌套格式</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月7日下午6:00:49</p>
 */
public class NestProperty extends AbstractProperty implements Property {
    /** 子结构列表*/
    Map<String,Property> properties = new HashMap<String,Property>();
    
    public NestProperty() {

    }

	public Map<String, Property> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Property> properties) {
		this.properties = properties;
	}
}
