package com.efounder.JEnterprise.timer;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2003</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public interface KeyValue {
  public Object getValue(Object key,Object def);
  public void   setValue(Object key,Object val);
  public java.util.Map getAttriMap();
}
