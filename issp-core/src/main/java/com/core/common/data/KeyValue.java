package com.core.common.data;

public interface KeyValue {
	public Object getValue(Object key, Object def);

	public void setValue(Object key, Object val);

	public java.util.Map getAttriMap();
}
