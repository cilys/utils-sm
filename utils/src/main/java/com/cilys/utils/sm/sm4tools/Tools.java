package com.cilys.utils.sm.sm4tools;

import java.util.ArrayList;

public class Tools {
    //将0到255之间int类型的数转换为8位二进制的String类型表示
    public String IntToBin(int i){
        if(i<0||i>255){
            System.out.println("IntToStr输入错误");
            return null;
        }
        String str = "";
        for(int n=7;n>=0;n--){
            int t = 1<<n;  //1<<n等于2的n次方
            if(i>=t){
                str = str + "1";
                i = i-t;
            }else{
                str = str + "0";
            }
        }
        return str;
    }

    //把8位String类型的二进制表示转换为int类型的数
    public int BinToInt(String str){
        if(str.length()!=8){
            System.out.println("BinToInt输入错误");
            return 0;
        }
        int result = 0;
        for(int i=0;i<8;i++){
            if(str.charAt(i)=='1'){
                result += 1<<(7-i);
            }
        }
        return result;
    }

    //把一个包含4个两位十六进制数的数组转换成32位(二进制表示)的字符串
    public String HexToStr(int[] n1){
        String res = "";
        for(int i=0;i<n1.length;i++){
            res += IntToBin(n1[i]);
        }
        return res;
    }

    //将一个32位的比特串转换成一个int数组
    public int[] BinToIntArray(String str){
        if(str.length()!=32){
            System.out.println("BinToIntArray输入错误");
            return null;
        }
        int[] result = new int[4];
        result[0] = BinToInt(str.substring(0, 8));
        result[1] = BinToInt(str.substring(8, 16));
        result[2] = BinToInt(str.substring(16, 24));
        result[3] = BinToInt(str.substring(24, 32));
        return result;
    }

    //循环左移n位方法
    public String loopleftmove(String str,int n){
        if(n>32){
            return null;
        }
        String s1,s2,res;
        s1 = str.substring(0, n);
        s2 = str.substring(n,32);
        res = s2 + s1;
        return res;
    }

    //String类型的异或运算,相同为零,不同为一
    public String XOR(String str1,String str2){
        if(str1.length()!=str2.length()){
            System.out.println("XOR方法输入错误,参数长度不同");
            return null;
        }
        String res = "";
        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i)==str2.charAt(i)){
                res += "0";
            }else{
                res += "1";
            }
        }
        return res;
    }

    //将String表示的4个两位十六进制数转换成一个int数组
    public int[] HexToIntArray(String str){
        if(str.length()!=8){
            System.out.println("HexToIntArray输入错误");
            return null;
        }
        int[] res = new int[4];
        for(int i=0;i<4;i++){
            int sum = 0;
            if(str.charAt(2*i)>=48&&str.charAt(2*i)<=57){
                sum += (str.charAt(2*i)-48)*16;
            }else{
                sum += (str.charAt(2*i)-87)*16;
            }
            if(str.charAt(2*i+1)>=48&&str.charAt(2*i+1)<=57){
                sum += str.charAt(2*i+1)-48;
            }else{
                sum += str.charAt(2*i+1)-87;
            }
            res[i] = sum;
        }
        return res;
    }

    //将一个长度为4的int数组装换成String类型的十六进制表示
    public String IntArrayToStr(int[] n1){
        if(n1.length!=4){
            System.out.println("IntArrayToStr输入错误");
            return null;
        }
        String res = "";
        for(int i=0;i<4;i++){
            res += IntToHex(n1[i]);
        }
        return res;
    }

    //将一个int数转换成一个两位的十六进制数
    public String IntToHex(int i){
        String res = "";
        //第一位数是n，第二位是变换后的i
        int n=15;
        for(;n>=0;n--){
            if(n*16<=i){
                i = i-n*16;
                break;
            }
        }
        if(n>9){
            res = res + (char)(n+87);
        }else{
            res = res + n;
        }
        if(i>9){
            res = res + (char)(i+87);
        }else{
            res = res + i;
        }
        return res;
    }

    //若str不能整除16则用" "补齐位数
    public String fillNumber(String str){
        int m = str.length()%16;
        if(m!=0){
            for(int n=0;n<(16-m%16);n++){
                str += " ";
            }
        }
        return str;
    }

    //若str末尾有" ",则将末尾的" "全都去掉
    public String restore(String str){
        while(str.charAt(str.length()-1)==' '){
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    //将一个16位的字符串转化成4个长度为4的int数组存入ArrayList中
    public ArrayList<int[]> StrToList(String str){
        if(str.length()!=16){
            System.out.println("StrToList输入错误");
            return null;
        }
        ArrayList<int[]> res = new ArrayList<int[]>();
        for(int n=0;n<4;n++){
            int[] t = new int[4];
            t[0] = str.charAt(n*4);
            t[1] = str.charAt(n*4+1);
            t[2] = str.charAt(n*4+2);
            t[3] = str.charAt(n*4+3);
            res.add(t);
        }
        return res;
    }

    //将ArrayList中的数提出来转换为String
    public String ListToStr(ArrayList<int[]> al){
        String res = "";
        for(int n=0;n<4;n++){
            int[] t = al.get(n);
            for(int i=0;i<4;i++){
                res += (char)t[i];
            }
        }
        return res;
    }

    public static void main(String[] args){
        Tools tool = new Tools();
        String s = "abcdABCDjigi1234";
        ArrayList<int[]> a = tool.StrToList(s);
        System.out.println(tool.ListToStr(a));

    }

}
