package com.yuly.utils;

import com.alibaba.fastjson.JSON;
import com.yuly.model.ArffEntity;
import org.junit.Test;

/**
 * Created by yuliyao on 2017/1/5.
 */
public class ArffUtilTest {


    @Test
    public void testReadArff() {
        ArffEntity arffEntity = ArffUtil.readArff("weathre.arff");
        System.out.println(JSON.toJSON(arffEntity));
    }

}
