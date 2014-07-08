package com.tdc;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

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

        try {
            if (permission != null) {
                return actionInvocation.invoke();
            }
        } catch (Exception e) {
            //Transaction tx = HibernateUtil.currentSession().getTransaction();
            HibernateUtil.currentSession();
            HibernateUtil.closeSession();
            e.printStackTrace();
            return "failed";
        }

        return Action.LOGIN;
    }
}
