package com.tdc;

import com.opensymphony.xwork2.ActionSupport;
import com.tdc.db.UserEntity;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by Alvin on 2014/7/4.
 */
public class UserAction extends ActionSupport implements SessionAware {
    private Map session;

    private String insert;
    private String query;
    private String update;
    private String delete;

    private String oldUsername;

    private String username;
    private String password;
    private Boolean permission1;
    private Boolean permission2;
    private Boolean permission4;
    private Boolean permission8;
    private Boolean permission16;

    private List<UserEntity> list;
//    private String password2;

    @Override
    public String execute() throws Exception {
        String permission = (String) session.get("permission");
        if (permission == null) {
            return ERROR;
        }
        if ((Integer.parseInt(permission) & 16) == 0) {
            return ERROR;
        }
        if (insert != null) {
            return insert();
        }

        if (query != null) {
            return query();
        }

        if (update != null) {
            return update();
        }

        if (delete != null) {
            return delete();
        }

        return ERROR;
    }

    public String insert() throws Exception {
//        if (!password.equals(password2) || "".equals(password)){

        if ("".equals(password) || "".equals(username)) {
            return ERROR;
        }

        Session sess = HibernateUtil.currentSession();
        Transaction transaction = sess.beginTransaction();

        List list1= sess.createQuery("from UserEntity as user where user.username=:username")
                .setString("username",username)
                .list();

        if(list1.size() == 0) {
            UserEntity user = new UserEntity();

            user.setUsername(getUsername());
            user.setPassword(getPassword());
            user.setPermission(genPermission());

            sess.save(user);
        }
        transaction.commit();
        HibernateUtil.closeSession();


        query();
        return "insert";
    }

    public String query() throws Exception {
        String permission = (String) session.get("permission");
        session.clear();
        session.put("permission", permission);

        session.put("username", username);
        session.put("permission1", permission1.toString());
        session.put("permission2", permission2.toString());
        session.put("permission4", permission4.toString());
        session.put("permission8", permission8.toString());
        session.put("permission16", permission16.toString());
        password = "";

        Session sess = HibernateUtil.currentSession();
        Transaction transaction = sess.beginTransaction();

        String hql = "from UserEntity as user ";

        int count = 0;

        if (!"".equals(username)) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "user.username like :username ";
        }

        if (0 != genPermission()) {
            count++;
            if (count == 1) {
                hql += "where ";
            } else {
                hql += "and ";
            }
            hql += "user.permission=:permission ";
        }

        Query query = sess.createQuery(hql);

        if (!username.equals("")) {
            query.setString("username", "%" + username + "%");
        }

        if (0 != genPermission()) {
            query.setString("permission", String.valueOf(genPermission()));
        }

        list = query.list();

        transaction.commit();
        HibernateUtil.closeSession();

        return "query";
    }

    public String update() throws Exception {
        if ("".equals(username) || "".equals(password)) {
            return ERROR;
        }
            Session sess = HibernateUtil.currentSession();
            Transaction transaction = sess.beginTransaction();

            int deletedEntities = sess.createQuery("delete from UserEntity as user where user.username=:username ")
                    .setString("username", oldUsername)
                    .executeUpdate();

            UserEntity user = new UserEntity();

            user.setUsername(getUsername());
            user.setPassword(getPassword());
            user.setPermission(genPermission());

            sess.saveOrUpdate(user);

            transaction.commit();
            HibernateUtil.closeSession();


        username = (String) session.get("username");
        permission1 = Boolean.valueOf((String) session.get("permission1"));
        permission2 = Boolean.valueOf((String) session.get("permission2"));
        permission4 = Boolean.valueOf((String) session.get("permission4"));
        permission8 = Boolean.valueOf((String) session.get("permission8"));
        permission16 = Boolean.valueOf((String) session.get("permission16"));

        query();
        return "update";
    }

    public String delete() throws Exception {

        Session sess = HibernateUtil.currentSession();
        Transaction tx = sess.beginTransaction();

        int deletedEntities = sess.createQuery("delete from UserEntity as user where user.username=:username ")
                .setString("username", username)
                .executeUpdate();

        tx.commit();
        HibernateUtil.closeSession();

        username = (String) session.get("username");
        permission1 = Boolean.valueOf((String) session.get("permission1"));
        permission2 = Boolean.valueOf((String) session.get("permission2"));
        permission4 = Boolean.valueOf((String) session.get("permission4"));
        permission8 = Boolean.valueOf((String) session.get("permission8"));
        permission16 = Boolean.valueOf((String) session.get("permission16"));

        query();
        return "delete";
    }

    private int genPermission() {
        int permission = 0;

        if (permission1) {
            permission += 1;
        }

        if (permission2) {
            permission += 2;
        }

        if (permission4) {
            permission += 4;
        }

        if (permission8) {
            permission += 8;
        }

        if (permission16) {
            permission += 16;
        }

        return permission;
    }

    public Map getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        this.session = stringObjectMap;
    }

    public String getInsert() {
        return insert;
    }

    public void setInsert(String insert) {
        this.insert = insert;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public List<UserEntity> getList() {
        return list;
    }

    public void setList(List<UserEntity> list) {
        this.list = list;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
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

    public Boolean getPermission1() {
        return permission1;
    }

    public void setPermission1(Boolean permission1) {
        this.permission1 = permission1;
    }

    public Boolean getPermission2() {
        return permission2;
    }

    public void setPermission2(Boolean permission2) {
        this.permission2 = permission2;
    }

    public Boolean getPermission4() {
        return permission4;
    }

    public void setPermission4(Boolean permission4) {
        this.permission4 = permission4;
    }

    public Boolean getPermission8() {
        return permission8;
    }

    public void setPermission8(Boolean permission8) {
        this.permission8 = permission8;
    }

    public Boolean getPermission16() {
        return permission16;
    }

    public void setPermission16(Boolean permission16) {
        this.permission16 = permission16;
    }

//    public void setPassword2(String password2) {
//        this.password2 = password2;
//    }
//
//    public String getPassword2() {
//        return password2;
//    }
}
