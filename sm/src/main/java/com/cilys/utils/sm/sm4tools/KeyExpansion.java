package com.cilys.utils.sm.sm4tools;

public class KeyExpansion {
     private String FK0 = "a3b1bac6";
        private String FK1 = "56aa3350";
        private String FK2 = "677d9197";
        private String FK3 = "b27022dc";
        private String[] CK = {"00070e15","1c232a31","383f464d","545b6269",
                "70777e85","8c939aa1","a8afb6bd","c4cbd2d9",
                "e0e7eef5","fc030a11","181f262d","343b4249",
                "50575e65","6c737a81","888f969d","a4abb2b9",
                "c0c7ced5","dce3eaf1","f8ff060d","141b2229",
                "30373e45","4c535a61","686f767d","848b9299",
                "a0a7aeb5","bcc3cad1","d8dfe6ed","f4fb0209",
                "10171e25","2c333a41","484f565d","646b7279"
        };

        //输入加密密钥4个32位字,输出轮密钥
        String[] wheelKey(String[] MK){
            Tools tool = new Tools();
            BasicComponents bc = new BasicComponents();
            String[] k = new String[36];  //k中存的是32位的比特串
            String[] rk = new String[32];
            String tmk = tool.HexToStr(tool.HexToIntArray(MK[0]));
            String tfk = tool.HexToStr(tool.HexToIntArray(FK0));
            k[0] = tool.XOR(tmk, tfk);
            tmk = tool.HexToStr(tool.HexToIntArray(MK[1]));
            tfk = tool.HexToStr(tool.HexToIntArray(FK1));
            k[1] = tool.XOR(tmk, tfk);
            tmk = tool.HexToStr(tool.HexToIntArray(MK[2]));
            tfk = tool.HexToStr(tool.HexToIntArray(FK2));
            k[2] = tool.XOR(tmk, tfk);
            tmk = tool.HexToStr(tool.HexToIntArray(MK[3]));
            tfk = tool.HexToStr(tool.HexToIntArray(FK3));
            k[3] = tool.XOR(tmk, tfk);
            for(int i=0;i<32;i++){
                String ts = "";
                ts = tool.XOR(k[i+1], k[i+2]);
                ts = tool.XOR(ts, k[i+3]);
                ts = tool.XOR(ts, tool.HexToStr(tool.HexToIntArray(CK[i])));
                ts = tool.HexToStr(bc.T_1Change(tool.BinToIntArray(ts)));
                ts = tool.XOR(k[i], ts);
                k[i+4] = ts;
                rk[i] = tool.IntArrayToStr(tool.BinToIntArray(ts));
            }
            return rk;
        }

}
