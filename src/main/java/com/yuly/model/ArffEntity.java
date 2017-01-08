package com.yuly.model;

import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * Created by yuliyao on 2017/1/5.
 */
public class ArffEntity {

    private String description;

    private Map<String,Object> attributeMap;

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
     * 得到结果的样本
     * @return
     */
    public String[] getResultColumns() {
        String resultKey = (String)this.attributeMap.keySet().toArray()[this.attributeMap.keySet().size() - 1];
        return getColumns(resultKey);
    }

    /**
     * 获取属性的值分组统计
     * @param attrName
     * @return
     */
    public List<Integer[]> getColumnsGroup(@NotNull String attrName) {
        Map<String,Map> countMap = new HashMap<>();
        String[] columns = getColumns(attrName);
        String[] resultColumns = getResultColumns();
        if (columns != null) {
            for (int i = 0; i < columns.length; i++) {
                if (!countMap.containsKey(columns[i])) {
                    Map<String, Integer> resultMap = new HashMap<>();
                    resultMap.put(resultColumns[i], 1);
                    countMap.put(columns[i], resultMap);
                } else {
                    Map<String, Integer> resultMap = countMap.get(columns[i]);
                    if (!resultMap.containsKey(resultColumns[i])) {
                        resultMap.put(resultColumns[i], 1);
                    } else {
                        resultMap.put(resultColumns[i], resultMap.get(resultColumns[i] + 1));
                    }
                    countMap.put(columns[i], resultMap);
                }
            }
        }
        Map<String, Integer[]> countMap2 = new HashMap<>();
        Iterator iterator = countMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Map resultMap = countMap.get(key);
            List valueList = new ArrayList();
            valueList.addAll(resultMap.values());
            countMap2.put(key, (Integer[]) valueList.toArray());
        }
        List<Integer[]> groupList = new ArrayList<>();
        groupList.addAll(countMap2.values());
        return groupList;
    }

    /**
     * 获取结果集的分组统计
     * @return
     */
    public Integer[] getResultColumnsGroup() {
        getResultColumns()
        String resultKey = (String)this.attributeMap.keySet().toArray()[this.attributeMap.keySet().size() - 1];
        return getColumnsGroup(resultKey);
    }
}
