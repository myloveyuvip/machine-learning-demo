package com.yuly.id3;

import com.yuly.Id3;
import com.yuly.model.ArffEntity;
import com.yuly.utils.ArffUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuliyao on 2017/1/5.
 */
public class Id3Test {

    @Test
    public void testGenDecisionTree() {

        Id3 id3 = new Id3();
        ArffEntity arffEntity = ArffUtil.readArff("weathre.arff");
        List<String> notAttrList = new ArrayList<>();

        id3.genDecisionTree(arffEntity, notAttrList);

    }
}
