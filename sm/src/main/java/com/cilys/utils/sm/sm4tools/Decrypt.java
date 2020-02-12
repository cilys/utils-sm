package com.cilys.utils.sm.sm4tools;

import java.util.ArrayList;

public class Decrypt {

    private ArrayList<int[]> ciphertext;  //密文
    private String[] MK;  //密钥

    public Decrypt(){

    }

    public Decrypt(ArrayList<int[]> ciphertext, String[] MK){
        this.ciphertext = ciphertext;
        this.MK = MK;
    }

    //解密入口
    public String decryptEntrance(){
        KeyExpansion ke = new KeyExpansion();
        String[] rk = ke.wheelKey(MK);  //根据密钥拓展得出轮密钥
        Tools tool = new Tools();
        String res = "";
        for(int n=0;n<ciphertext.size()/4;n++){
            ArrayList<int[]> tal = new ArrayList<int[]>();
            tal = decryptProcess(ciphertext.get(n*4),ciphertext.get(n*4+1),ciphertext.get(n*4+2),ciphertext.get(n*4+3),rk);
            res += tool.ListToStr(tal);
        }
        res = tool.restore(res);
        return res;
    }



    //解密过程,输入128位密文,4个长度为4的int数组(每一个元素为一个两位的十六进制数),输入轮密钥,输出128位明文
    ArrayList<int[]> decryptProcess(int[] n0, int[] n1, int[] n2,int[] n3, String[] rk){
        if(n0.length!=4||n1.length!=4||n2.length!=4||n3.length!=4){
            System.out.println("decrypt输入错误");
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
            int[] trk = tool.HexToIntArray(rk[31-i]);
            x.add(wf.F(x.get(i), x.get(i+1), x.get(i+2), x.get(i+3), trk));
        }
        ArrayList<int[]> res = new ArrayList<int[]>();
        res.add(x.get(35));
        res.add(x.get(34));
        res.add(x.get(33));
        res.add(x.get(32));
        return res;
    }


    public void setCiphertext(ArrayList<int[]> ciphertext) {
        this.ciphertext = ciphertext;
    }

    public ArrayList<int[]> getCiphertext() {
        return ciphertext;
    }

    public String[] getMK() {
        return MK;
    }

    public void setMK(String[] mK) {
        MK = mK;
    }
}
