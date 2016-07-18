package io.swagger.models.properties;

import io.swagger.models.properties.AbstractProperty;
import io.swagger.models.properties.Property;

/**
 * 
		* <p>Title: 自定义json格式</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月7日下午6:00:49</p>
 */
public class JsonProperty extends AbstractProperty implements Property {
    public static final String TYPE = "cust";
    
    private static final String FORMAT = "json";

    /** 内容*/
    private String content;
    
    
    public JsonProperty() {
        setType(TYPE);
        setFormat(FORMAT);
    }

    public static boolean isType(String type, String format) {
    	return TYPE.equals(type) && FORMAT.equals(format);
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
