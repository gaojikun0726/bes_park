package org.ace.httpclient.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiepufeng
 */
public class RequestParams
{

    public Map<String, Object> params = new ConcurrentHashMap<>();

    public RequestParams()
    {
    }

    public RequestParams(String key, Object object)
    {
        if (key != null || object != null)
        {
            params.put(key, object);
        }
    }

    public RequestParams(Map<String, Object> source)
    {
        if (source != null)
        {
            source.forEach(this::put);
        }
    }

    public void put(String key, Object value)
    {
        if (key != null && value != null)
        {
            params.put(key, value);
        }
    }
}