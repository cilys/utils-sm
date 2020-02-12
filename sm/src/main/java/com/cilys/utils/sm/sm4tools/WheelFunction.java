package com.cilys.utils.sm.sm4tools;

public class WheelFunction {
    //轮函数方法,输入128位,输出32位,输入4个32位字的数据和一个32位的轮密钥,输出一个32位字
    int[] F(int[] tn0, int[] tn1, int[] tn2, int[] tn3, int[] rk) {
        if (tn0.length != 4 || tn1.length != 4 || tn2.length != 4 || tn3.length != 4 || rk.length != 4) {
            System.out.println("轮函数输入错误");
            return null;
        }
        Tools tool = new Tools();
        BasicComponents bc = new BasicComponents();
        String str = "";
        str = tool.XOR(tool.HexToStr(tn1), tool.HexToStr(tn2));
        str = tool.XOR(str, tool.HexToStr(tn3));
        str = tool.XOR(str, tool.HexToStr(rk));
        int[] res = new int[4];
        res = tool.BinToIntArray(str);
        res = bc.TChange(res);
        str = tool.HexToStr(res);
        str = tool.XOR(tool.HexToStr(tn0), str);
        res = tool.BinToIntArray(str);
        return res;
    }

}
