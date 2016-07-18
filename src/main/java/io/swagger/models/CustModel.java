package io.swagger.models;

import io.swagger.models.AbstractModel;
import io.swagger.models.properties.Property;

import java.util.Map;

/**
 * 
		* <p>Title: 自定义格式</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月7日下午6:30:58</p>
 */
public class CustModel extends AbstractModel {
	/** 类型*/
	private String type;
	
	/** 格式*/
    private String format;
    
    /** 名称*/
    private String name;
    
    /** 内容*/
    private String content;
    
    /** 描述*/
    private String description;

    
    public CustModel() {
        this.type = "cust";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Deprecated
	@Override
	public Map<String, Property> getProperties() {
		return null;
	}

	@Deprecated
	@Override
	public void setProperties(Map<String, Property> properties) {
		
	}

	@Deprecated
	@Override
	public Object getExample() {
		return null;
	}

	@Deprecated
	@Override
	public void setExample(Object example) {
		
	}
}