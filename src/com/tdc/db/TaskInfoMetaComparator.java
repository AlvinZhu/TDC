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
        return Integer.toString(t1.getProcedureId()).compareTo(Integer.toString(t2.getProcedureId()));
    }
}

