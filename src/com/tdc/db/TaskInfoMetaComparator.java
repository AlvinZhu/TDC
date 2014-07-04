package com.tdc.db;


import java.util.Comparator;


/**
 * Created by Alvin on 2014/6/22.
 */
public class TaskInfoMetaComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        TaskInfoMetaEntity t1 = (TaskInfoMetaEntity) o1;
        TaskInfoMetaEntity t2 = (TaskInfoMetaEntity) o2;
        int i;
        if (t1.getProcedureId() > t2.getProcedureId()){
            i = 1;
        } else if (t1.getProcedureId() < t2.getProcedureId()){
            i = -1;
        } else {
            i = 0;
        }
        return i;
    }
}

