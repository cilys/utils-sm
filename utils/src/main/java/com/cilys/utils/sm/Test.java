package com.cilys.utils.sm;

public class Test {

    public static void main(String[] args) throws Exception{
//        BaseSmUtils.setEnableLog(true);
        System.out.println("sm3加密：" + Sm3Utils.encrypt("123456"));

        String key = "12345678901234567890123456789012";
        String data = "abc123456";
        String encodeData = com.cilys.utils.sm.Sm4Utils.encryEcb(key, data);
        System.out.println(encodeData);
        System.out.println(com.cilys.utils.sm.Sm4Utils.decryptEcb(key, encodeData));
    }

}
