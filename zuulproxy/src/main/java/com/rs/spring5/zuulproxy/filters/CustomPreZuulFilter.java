package com.rs.spring5.zuulproxy.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by rs on 2/16/2018.
 */
@Component
public class CustomPreZuulFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(CustomPreZuulFilter.class);

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getRequestURI().contains("oauth/token")) {
            byte[] encoded;
            try {
                encoded = Base64.encode("fooClientIdPassword:secret".getBytes("UTF-8"));
                ctx.addZuulRequestHeader("Authorization", "Basic " + new String(encoded));
            } catch (UnsupportedEncodingException e) {
                log.error("Error occured in pre filter", e);
            }
        }
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return -2;
    }

    @Override
    public String filterType() {
        return "pre";
    }
}