package com.yuly;

import com.yuly.model.ArffEntity;
import com.yuly.utils.ArffUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yuliyao on 2016/12/18.
 */
public class Id3 {

    /**
     * 生成决策树
     */
    public void genDecisionTree(String filePath) {
        //1.读取样本数据
        ArffEntity arffEntity = ArffUtil.readArff(filePath);
        //2.样本熵
        System.out.println(calcEntropy(arffEntity.getColumnsGroup("play")));

        //3.各属性的信息增益

        //4.生成根结点

        //5.递归生成结点
    }

    public static double calcEntropy(Integer... attrAmounts) {
        double attrAmountSum = 0;
        for (int attrAmount : attrAmounts) {
            attrAmountSum += attrAmount;
        }

        double sum = 0;
        for (double attrAmount : attrAmounts) {
            if (attrAmount == 0) {
                continue;
            }
            sum += -attrAmount/attrAmountSum * (Math.log(attrAmount/attrAmountSum) / Math.log(2));
        }
        return sum;
    }


    public static void main(String[] args) {

        double entropyS = calcEntropy(9, 5);
        System.out.println("样本集熵为:" + entropyS);
        //Outlook
        Integer[] Outlook_Sunny = {2, 3};
        Integer[] Outlook_Overcast = {4, 0};
        Integer[] Outlook_Rain = {3, 2};
        System.out.println("Outlook的信息增益为:"+calcGain(Arrays.asList(Outlook_Sunny, Outlook_Overcast, Outlook_Rain), entropyS, 14));
        //Temperature
        Integer[] Temp_Hot = {2, 2};
        Integer[] Temp_Mild = {4, 2};
        Integer[] Temp_Cool = {3, 1};
        System.out.println("Temperature的信息增益:"+calcGain(Arrays.asList(Temp_Hot, Temp_Mild, Temp_Cool), entropyS, 14));
        //Humidily
        Integer[] Hum_High = {3, 4};
        Integer[] Hum_Normal = {6, 1};
        System.out.println("Humidily:"+calcGain(Arrays.asList(Hum_High, Hum_Normal), entropyS, 14));
        //Wind
        Integer[] Wind_Weak = {6, 2};
        Integer[] Wind_Strong = {3, 3};
        System.out.println("Wind的信息增益:"+calcGain(Arrays.asList(Wind_Weak, Wind_Strong), entropyS, 14));

        double entropySRain = calcEntropy(3, 2);
        System.out.println("SRain的样子样为:"+entropySRain);
        Integer[] Temp_mild = {2, 1};
        Integer[] Temp_cool = {1, 1};
        System.out.println("SRain样本集下,Temperature的信息增益:" + calcGain(Arrays.asList(Temp_mild, Temp_cool), entropySRain, 5));
        Integer[] Wind_Weak_Rain = {4, 0};
        Integer[] Wind_Strong_Rain = {0, 2};
        System.out.println("SRain样本集下,Wind的信息增益:" + calcGain(Arrays.asList(Wind_Weak_Rain, Wind_Strong_Rain), entropySRain, 6));
        //测试ID3偏向多值属性
        Integer[] id1 = {1, 0};
        Integer[] id2 = {1, 0};
        Integer[] id3 = {1, 0};
        Integer[] id4 = {1, 0};
        Integer[] id5 = {1, 0};
        Integer[] id6 = {1, 0};
        Integer[] id7 = {1, 0};
        Integer[] id8 = {1, 0};
        Integer[] id9 = {1, 0};
        Integer[] id10 = {0, 1};
        Integer[] id11 = {0, 1};
        Integer[] id12 = {0, 1};
        Integer[] id13 = {0, 1};
        Integer[] id14 = {0, 1};
        System.out.println(calcGain(Arrays.asList(id1, id2, id3, id4, id5, id6, id7, id8, id9, id10, id11, id12, id13, id14), entropyS, 14));

        System.out.println("===============================");
        double giniS = gini(9, 5);
        System.out.println("样本基尼系数为:"+giniS);
        System.out.println("Outlook的基尼系统为:" + calcGiniGain(Arrays.asList(Outlook_Sunny, Outlook_Overcast, Outlook_Rain), gini(9, 5), 14));

        System.out.println("Tem:"+calcGiniGain(Arrays.asList(Temp_Hot, Temp_Mild, Temp_Cool), giniS, 14));
    }


    public static double calcGain(List<Integer[]> list,double entropyS,double sampleCount) {
        double sumExpect = 0;
        for (Integer[] attrInfo : list) {
            sumExpect += ((attrInfo[0] + attrInfo[1]) / sampleCount) * calcEntropy(attrInfo);
        }
        return entropyS - sumExpect;
    }

    public static double gini(Integer... attrAmounts) {
        double attrAmountSum = 0;
        double sum = 0;
        for (int attrAmount : attrAmounts) {
            attrAmountSum += attrAmount;
        }

        for (int attrAmount : attrAmounts) {
            sum += Math.pow((attrAmount / attrAmountSum), 2);
        }
        return 1-sum;
    }

    public static double calcGiniGain(List<Integer[]> list, double giniS, double sampleCount) {
        double sumExpect = 0;
        for (Integer[] attrInfo : list) {
            sumExpect += attrInfo[0] / sampleCount * gini(attrInfo);
        }
        return sumExpect;
    }
}
