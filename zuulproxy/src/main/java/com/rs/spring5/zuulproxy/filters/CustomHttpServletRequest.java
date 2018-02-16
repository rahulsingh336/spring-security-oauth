package com.rs.spring5.zuulproxy.filters;

import com.netflix.zuul.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rs on 2/16/2018.
 */
public class CustomHttpServletRequest extends HttpServletRequestWrapper {
    private Map<String, String[]> additionalParams;
    private HttpServletRequest request;

    public CustomHttpServletRequest(
            HttpServletRequest request, Map<String, String[]> additionalParams) {
        super(request);
        this.request = request;
        this.additionalParams = additionalParams;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String[]> param = new HashMap<String, String[]>();
        param.putAll(map);
        param.putAll(additionalParams);
        return param;
    }
}