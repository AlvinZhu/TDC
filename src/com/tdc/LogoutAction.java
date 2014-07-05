package com.tdc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Alvin on 2014/7/5.
 */
public class LogoutAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        ActionContext.getContext().getSession().clear();

        return SUCCESS;
    }
}
