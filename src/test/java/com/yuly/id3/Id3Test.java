package com.yuly.id3;

import com.yuly.Id3;
import org.junit.Test;

/**
 * Created by yuliyao on 2017/1/5.
 */
public class Id3Test {

    @Test
    public void testGenDecisionTree() {

        Id3 id3 = new Id3();
        id3.genDecisionTree("weathre.arff");

    }
}
