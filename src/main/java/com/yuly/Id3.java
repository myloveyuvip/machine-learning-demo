package com.yuly;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yuliyao on 2016/12/18.
 */
public class Id3 {

    public static double calcEntropy(int... attrAmounts) {
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
        int[] Outlook_Sunny = {2, 3};
        int[] Outlook_Overcast = {4, 0};
        int[] Outlook_Rain = {3, 2};
        System.out.println("Outlook的信息增益为:"+calcGain(Arrays.asList(Outlook_Sunny, Outlook_Overcast, Outlook_Rain), entropyS, 14));
        //Temperature
        int[] Temp_Hot = {2, 2};
        int[] Temp_Mild = {4, 2};
        int[] Temp_Cool = {3, 1};
        System.out.println("Temperature的信息增益:"+calcGain(Arrays.asList(Temp_Hot, Temp_Mild, Temp_Cool), entropyS, 14));
        //Humidily
        int[] Hum_High = {3, 4};
        int[] Hum_Normal = {6, 1};
        System.out.println("Humidily:"+calcGain(Arrays.asList(Hum_High, Hum_Normal), entropyS, 14));
        //Wind
        int[] Wind_Weak = {6, 2};
        int[] Wind_Strong = {3, 3};
        System.out.println("Wind的信息增益:"+calcGain(Arrays.asList(Wind_Weak, Wind_Strong), entropyS, 14));

        double entropySRain = calcEntropy(3, 2);
        System.out.println("SRain的样子样为:"+entropySRain);
        int[] Temp_mild = {2, 1};
        int[] Temp_cool = {1, 1};
        System.out.println("SRain样本集下,Temperature的信息增益:" + calcGain(Arrays.asList(Temp_mild, Temp_cool), entropySRain, 5));
        int[] Wind_Weak_Rain = {4, 0};
        int[] Wind_Strong_Rain = {0, 2};
        System.out.println("SRain样本集下,Wind的信息增益:" + calcGain(Arrays.asList(Wind_Weak_Rain, Wind_Strong_Rain), entropySRain, 6));
        //测试ID3偏向多值属性
        int[] id1 = {1, 0};
        int[] id2 = {1, 0};
        int[] id3 = {1, 0};
        int[] id4 = {1, 0};
        int[] id5 = {1, 0};
        int[] id6 = {1, 0};
        int[] id7 = {1, 0};
        int[] id8 = {1, 0};
        int[] id9 = {1, 0};
        int[] id10 = {0, 1};
        int[] id11 = {0, 1};
        int[] id12 = {0, 1};
        int[] id13 = {0, 1};
        int[] id14 = {0, 1};
        System.out.println(calcGain(Arrays.asList(id1, id2, id3, id4, id5, id6, id7, id8, id9, id10, id11, id12, id13, id14), entropyS, 14));

        System.out.println("===============================");
        double giniS = gini(9, 5);
        System.out.println("样本基尼系数为:"+giniS);
        System.out.println("Outlook的基尼系统为:" + calcGiniGain(Arrays.asList(Outlook_Sunny, Outlook_Overcast, Outlook_Rain), gini(9, 5), 14));

        System.out.println("Tem:"+calcGiniGain(Arrays.asList(Temp_Hot, Temp_Mild, Temp_Cool), giniS, 14));
    }


    public static double calcGain(List<int[]> list,double entropyS,double sampleCount) {
        double sumExpect = 0;
        for (int[] attrInfo : list) {
            sumExpect += ((attrInfo[0] + attrInfo[1]) / sampleCount) * calcEntropy(attrInfo);
        }
        return entropyS - sumExpect;
    }

    public static double gini(int... attrAmounts) {
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

    public static double calcGiniGain(List<int[]> list, double giniS, double sampleCount) {
        double sumExpect = 0;
        for (int[] attrInfo : list) {
            sumExpect += attrInfo[0] / sampleCount * gini(attrInfo);
        }
        return sumExpect;
    }
}
