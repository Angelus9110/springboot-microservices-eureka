package com.springboot.microservices.zuul.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostSpendTimeFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(PostSpendTimeFilter.class);

    @Override
    public String filterType() {
        return "post";
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
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.info("Entering post filter");

        Long startTime = (Long)request.getAttribute("startTime");
        Long endTime = System.currentTimeMillis();
        Long usedTime = endTime - startTime;

        logger.info("Time used in seconds " + usedTime.doubleValue()/1000.00 + " seg");
        logger.info("Time used in miliseconds " + usedTime + " ms");

        return null;
    }
}
