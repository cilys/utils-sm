package com.cilys.utils.sm.sm4tools;

import java.util.ArrayList;

public class Encrypt {

    private String plaintext;  //明文
    private String[] MK;  //密钥

    public Encrypt(){

    }

    public Encrypt(String plaintext, String[] MK){
        this.plaintext = plaintext;
        this.MK = MK;
    }

    //加密入口,接受一个String类型的明文,将其依次转换成4个int数组放入encryptProcess中加密,获得输出,输出最后结果
    public ArrayList<int[]> encryptEntrance(){
        KeyExpansion ke = new KeyExpansion();
        String[] rk = ke.wheelKey(MK);  //根据密钥拓展得出轮密钥
        Tools tool = new Tools();
        plaintext = tool.fillNumber(plaintext);
        ArrayList<int[]> res = new ArrayList<int[]>();
        for(int n=0;n<plaintext.length()/16;n++){
            ArrayList<int[]> t = new ArrayList<int[]>();
            ArrayList<int[]> tres = new ArrayList<int[]>();
            String s = plaintext.substring(n*16, (n+1)*16);
            t = tool.StrToList(s);
            tres = encryptProcess(t.get(0),t.get(1),t.get(2),t.get(3),rk);
            res.add(tres.get(0));
            res.add(tres.get(1));
            res.add(tres.get(2));
            res.add(tres.get(3));
        }

        return res;
    }

    //加密过程,输入128位明文,4个长度为4的int数组(每一个元素为一个两位的十六进制数),输入轮密钥,输出128位密文
    ArrayList<int[]> encryptProcess(int[] n0, int[] n1, int[] n2, int[] n3, String[] rk){
        if(n0.length!=4||n1.length!=4||n2.length!=4||n3.length!=4){
            System.out.println("encrypt输入错误");
            return null;
        }
        Tools tool = new Tools();
        WheelFunction wf = new WheelFunction();
        ArrayList<int[]> x = new ArrayList<int[]>();
        x.add(n0);
        x.add(n1);
        x.add(n2);
        x.add(n3);
        for(int i=0;i<32;i++){
            int[] trk = tool.HexToIntArray(rk[i]);
            x.add(wf.F(x.get(i), x.get(i+1), x.get(i+2), x.get(i+3), trk));
        }
        ArrayList<int[]> res = new ArrayList<int[]>();
        res.add(x.get(35));
        res.add(x.get(34));
        res.add(x.get(33));
        res.add(x.get(32));
        return res;
    }


    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String[] getMK() {
        return MK;
    }

    public void setMK(String[] mK) {
        MK = mK;
    }
}
