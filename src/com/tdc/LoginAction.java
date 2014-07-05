package com.tdc;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Alvin on 2014/7/5.
 */
public class LoginAction extends ActionSupport {

    private String username;
    private String password;

    @Override
    public String execute() throws Exception {
        if (isInvalid(getUsername()))
            return INPUT;

        if (isInvalid(getPassword()))
            return INPUT;

        Session sess = HibernateUtil.currentSession();
        Transaction tx = sess.beginTransaction();

        List<UserEntity> list = sess.createQuery("from UserEntity as user where user.username=:username")
                .setString("username", getUsername())
                .list();

        tx.commit();
        HibernateUtil.closeSession();

        if (list.size() == 1) {
            if (list.get(0).getPassword().equals(getPassword())) {
                ActionContext.getContext().getSession().put("permission", list.get(0).getPermission().toString());
            }
            return "success";
        }
        return "error";
    }

    private boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
