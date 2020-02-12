package com.cilys.utils.sm;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.nio.charset.Charset;

public class Sm3Utils extends BaseSmUtils{


    public static String encrypt(String paramStr){
        if (paramStr == null || paramStr.length() < 1){
            return paramStr;
        }
        try{
            byte[] srcData = paramStr.getBytes(Charset.forName(ENCODING));
            byte[] resultHash = hash(srcData);
            String result = ByteUtils.toHexString(resultHash);
            out("Sm3Utils待加密：" + paramStr + "\t加密后：" + result);
            return result;
        }catch (Exception e){
            err("Sm3Utils加密出错...");
            printStackTrace(e);
            return paramStr;
        }
    }

    private static byte[] hash(byte[] srcData){
        SM3Digest digest = new SM3Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }
}
