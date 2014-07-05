package com.tdc;

import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;

/**
 * Created by Alvin on 2014/7/5.
 */
public class loginInterceptor implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map session = actionInvocation.getInvocationContext().getSession();
        String permission = (String) session.get("permission");

        if (permission != null){
            return actionInvocation.invoke();
        }

        return Action.LOGIN;
    }
}
