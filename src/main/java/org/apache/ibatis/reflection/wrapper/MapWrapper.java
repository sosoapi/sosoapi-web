/*
 *    Copyright 2009-2012 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.reflection.wrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;

public class MapWrapper extends BaseWrapper {

	private Map<String, Object> map;

	public MapWrapper(MetaObject metaObject, Map<String, Object> map) {
		super(metaObject);
		this.map = map;
	}

	public Object get(PropertyTokenizer prop) {
		if (prop.getIndex() != null) {
			Object collection = resolveCollection(prop, map);
			return getCollectionValue(prop, collection);
		} else {
			return map.get(prop.getName());
		}
	}

	public void set(PropertyTokenizer prop, Object value) {
		if (prop.getIndex() != null) {
			Object collection = resolveCollection(prop, map);
			setCollectionValue(prop, collection, value);
		} else {
			// map.put(prop.getName(), value);
			
			// 默认情况下，映射事例如下"shop_name" -> "shopName"
			map.put(changDBColumnToPropertyName(prop.getName()),value);
		}
	}

	/**
     * 将数据库字段转换为Camel命名形式
     * 事例：
     * input: shop_name
     * output:shopName
     * @param column
     * @return
     */
    private String changDBColumnToPropertyName(String column){
        if (StringUtils.isBlank(column) 
        		|| column.equals("_parameter")//默认内置参数名
        		|| column.startsWith("__frch")//foreach标签相关
        		|| column.indexOf("_") == -1) {//不含下划线
            return column;
        }

        String[] words = column.toLowerCase().split("_");//全部先转换为小写再拆分
        StringBuilder builder = new StringBuilder(words[0]);
        for (int i = 1; i < words.length; i++) {
            builder.append(StringUtils.capitalize(words[i]));
        }

        return builder.toString();
    }
    
	public String findProperty(String name, boolean useCamelCaseMapping) {
		return name;
	}
	
	public String[] getGetterNames() {
		return map.keySet().toArray(new String[map.keySet().size()]);
	}

	public String[] getSetterNames() {
		return map.keySet().toArray(new String[map.keySet().size()]);
	}

	public Class<?> getSetterType(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			MetaObject metaValue = metaObject.metaObjectForProperty(prop
					.getIndexedName());
			if (metaValue == SystemMetaObject.NULL_META_OBJECT) {
				return Object.class;
			} else {
				return metaValue.getSetterType(prop.getChildren());
			}
		} else {
			if (map.get(name) != null) {
				return map.get(name).getClass();
			} else {
				return Object.class;
			}
		}
	}

	public Class<?> getGetterType(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			MetaObject metaValue = metaObject.metaObjectForProperty(prop
					.getIndexedName());
			if (metaValue == SystemMetaObject.NULL_META_OBJECT) {
				return Object.class;
			} else {
				return metaValue.getGetterType(prop.getChildren());
			}
		} else {
			if (map.get(name) != null) {
				return map.get(name).getClass();
			} else {
				return Object.class;
			}
		}
	}

	public boolean hasSetter(String name) {
		return true;
	}

	public boolean hasGetter(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			if (map.containsKey(prop.getIndexedName())) {
				MetaObject metaValue = metaObject.metaObjectForProperty(prop
						.getIndexedName());
				if (metaValue == SystemMetaObject.NULL_META_OBJECT) {
					return true;
				} else {
					return metaValue.hasGetter(prop.getChildren());
				}
			} else {
				return false;
			}
		} else {
			return map.containsKey(prop.getName());
		}
	}

	public MetaObject instantiatePropertyValue(String name,
			PropertyTokenizer prop, ObjectFactory objectFactory) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		set(prop, map);
		return MetaObject.forObject(map, metaObject.getObjectFactory(),
				metaObject.getObjectWrapperFactory());
	}

	public boolean isCollection() {
		return false;
	}

	public void add(Object element) {
		throw new UnsupportedOperationException();
	}

	public <E> void addAll(List<E> element) {
		throw new UnsupportedOperationException();
	}

}
