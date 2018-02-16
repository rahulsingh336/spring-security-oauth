package com.rs.spring5.zuulproxy.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rs on 1/31/2018.
 */
public class CustomRouteFilter extends ZuulFilter {

    private static final String HTTP_METHOD_POST = "POST";
    private static final String HTTP_METHOD_GET = "GET";
    private static Logger log = LoggerFactory.getLogger(CustomRouteFilter.class);

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String method = request.getMethod();
        URL domain1 = null;
        URL domain2 = null;
        URL urlGet = null;
        URL urlPost = null;
        //try {
            //domain1 = new URL("google.com");

            //urlGet = new URL(domain + "/get");
            //urlPost = new URL(domain + "/post");
        //} catch (MalformedURLException e) {
          //  e.printStackTrace();
        //}

        log.info(String.format("%s request to Route Filter %s", request.getMethod(),
                request.getRequestURL().toString()));

        if(HTTP_METHOD_GET.equalsIgnoreCase(method)) {
            ctx.setRouteHost(domain1);
        } else if(HTTP_METHOD_POST.equalsIgnoreCase(method)){
            ctx.setRouteHost(domain2);
        } else {
            throw new IllegalStateException("unsupported method ....!!!!");
        }

        return null;
    }

}
