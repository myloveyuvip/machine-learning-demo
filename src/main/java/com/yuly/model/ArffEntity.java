package com.yuly.model;

import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * Created by yuliyao on 2017/1/5.
 */
public class ArffEntity {

    private String description;

    private Map attributeMap;

    private List<String[]> samples;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(Map attributeMap) {
        this.attributeMap = attributeMap;
    }

    public List<String[]> getSamples() {
        return samples;
    }

    public void setSamples(List<String[]> samples) {
        this.samples = samples;
    }

    /**
     * 获取某一属性的样本
     * @param attrName
     * @return
     */
    public String[] getColumns(@NotNull String attrName) {
        Object[] keys = this.attributeMap.keySet().toArray();
        int columnIndex = 0;
        for(int i=0;i<keys.length;i++) {
            if (attrName.equals(keys[i])) {
                columnIndex = i;
            }
        }
        List<String> columns = new ArrayList<>();
        //遍历样本数据取出对应列的值
        for (String[] rows : samples) {
            columns.add(rows[columnIndex]);
        }
        return columns.toArray(new String[0]);
    }

    /**
     * 获取
     * @param attrName
     * @return
     */
    public Integer[] getColumnsGroup(@NotNull String attrName) {
        Map<String,Integer> countMap = new HashMap<>();
        String[] columns = getColumns(attrName);
        if (columns != null) {
            for (String column : columns) {
                if (!countMap.containsKey(column)) {
                    countMap.put(column, 1);
                } else {
                    countMap.put(column, countMap.get(column) + 1);
                }
            }
        }
        Integer[] groups = new Integer[countMap.values().size()];
        return countMap.values().toArray(groups);
    }
}
