package com.yuly.utils;

import com.yuly.model.ArffEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by yuliyao on 2017/1/1.
 */
public class ArffUtil {

    public static ArffEntity readArff(String filePath){
        ArffEntity arffEntity = new ArffEntity();
        try {
            InputStreamReader reader = new InputStreamReader(ArffUtil.class.getClassLoader().getResourceAsStream(filePath));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            boolean dataStart = false;
            List<String[]> datas = new ArrayList<String[]>();
            Map map = new LinkedHashMap();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("@relation")) {
                    arffEntity.setDescription(line.substring("@relation".length()).trim());
                }
                if (line.startsWith("@attribute")) {
                    String attrName = line.substring("@attribute".length(),line.indexOf("{")).trim();
                    String[] attrValues = line.substring(line.indexOf("{")+1,line.indexOf("}")).split(",");
                    map.put(attrName, trimArray(attrValues));
                }
                //遇到@data之后，标识为之后的行都为样本数据
                if (line.startsWith("@data")) {
                    dataStart = true;
                    continue;
                }
                if (dataStart) {
                    datas.add(trimArray(line.split(",")));
                }
            }
            arffEntity.setAttributeMap(map);
            arffEntity.setSamples(datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arffEntity;
    }

    /**
     * 将数组中的元素trim
     * @param arrays
     * @return
     */
    private static String[] trimArray(String[] arrays) {
        if (arrays != null) {
            for (int i = 0; i < arrays.length; i++) {
                arrays[i] = arrays[i].trim();
            }
        }
        return arrays;
    }



}
