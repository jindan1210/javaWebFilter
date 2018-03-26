package com.king.paramter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author 金丹
 * @since 2018/3/26.
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {
    private Map<String, String[]> params = new HashMap<String, String[]>();

    @SuppressWarnings("unchecked")
    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
        this.params.putAll(request.getParameterMap());
        this.modifyParameterValues();
    }

    /**
     * 将parameter的值去除空格后重写回去
     */
    public void modifyParameterValues() {
        Set<String> set = params.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = it.next();
            String[] values = params.get(key);
            if (StringUtils.isNotBlank(values[0])) {
                values[0] = values[0].trim();
            }
            params.put(key, values);
        }
    }

    /**
     * 重写getParameter，代表参数从当前类中的map获取
     *
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {//同上
        return params.get(name);
    }

    /**
     * 增加多个参数
     *
     * @param otherParams
     */
    public void addAllParameters(Map<String, Object> otherParams) {
        for (Map.Entry<String, Object> entry : otherParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }


    /**
     * 增加参数
     *
     * @param name
     * @param value
     */
    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }
}
