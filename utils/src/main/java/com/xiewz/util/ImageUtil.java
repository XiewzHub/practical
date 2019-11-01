package com.xiewz.util;

/**
 * @author Xiewz
 */
public class ImageUtil {

    /**
     *base64编码库
     */
    public static final char[] BASE_64_STR = {
            'A','B','C','D','E','F','G','H','I','J',
            'K','L','M','N','O','P','Q','R','S','T',
            'U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j',
            'k','l','m','n','o','p','q','r','s','t',
            'u','v','w','x','y','z',
            '0','1','2','3','4','5','6','7','8','9',
            '+','/','='
    };

    //把有符号转成无符号
    public static int convertBinaryUnsigned(byte b){
        return ((int)b) & 0XFF;
    }

    /**
     * 把三个字节按6位一个字节转成四个字节
     * @param bytes
     * @return
     */
    public static int[] convertBinaryBase64(byte[] bytes){
        int[] result = new int[4];

        if(bytes.length==3){
            byte a = bytes[0];
            byte b = bytes[1];
            byte c = bytes[2];
            byte a_1 = (byte) (a & 0XFC);//取左六位，0XFC用计算器得出，下同
            int out1 = (byte) (convertBinaryUnsigned(a_1) >> 2);//转成无符号数字
            result[0] = out1;

            byte a_2 = (byte) ((a & 0X3) << 6);//取右两位
            byte b_1 =  (byte) ((b & 0XF0) >> 2);//取第二个字符左四位
            int out2 = (convertBinaryUnsigned((byte) (a_2 | b_1)) >> 2);//得出的两位与四位组合，生成新的base64数字编码，下面基本大同小异
            result[1] = out2;

            byte b_2 = (byte) ((b & 0XF) << 4);
            byte c_1 = (byte) ((c & 0XC0) >>4);
            int out3 = (convertBinaryUnsigned((byte) (b_2 | c_1)) >> 2);
            result[2] = out3;

            byte c_2 = (byte) (c & 0X3F);
            byte out4 = c_2;
            result[3] = out4;
        }else if(bytes.length==1){
            byte a = bytes[0];
            byte a_1 = (byte) (a & 0XFC);
            int out1 = (byte) (convertBinaryUnsigned(a_1) >> 2);
            result[0] = out1;

            byte a_2 = (byte) ((a & 0X3) << 6);
            int out2 = (convertBinaryUnsigned((byte) (a_2)) >> 2);
            result[1] = out2;

            result[2] = 64;
            result[3] = 64;
        }else if(bytes.length==2){
            byte a = bytes[0];
            byte b = bytes[1];
            byte a_1 = (byte) (a & 0XFC);
            int out1 = (byte) (convertBinaryUnsigned(a_1) >> 2);
            result[0] = out1;

            byte a_2 = (byte) ((a & 0X3) << 6);
            byte b_1 =  (byte) ((b & 0XF0) >> 2);
            int out2 = (convertBinaryUnsigned((byte) (a_2 | b_1)) >> 2);
            result[1] = out2;

            byte b_2 = (byte) ((b & 0XF) << 4);
            int out3 = (convertBinaryUnsigned((byte) (b_2)) >> 2);
            result[2] = out3;
            result[3] = 64;
        }
        return result;
    }

    /**
     *这个方法是为了把base64编码转成图片或二进制数据准备的
     */
    public static int indexOfBase64(char c){
        int result = -1;
        for (int i = 0; i < BASE_64_STR.length; i++) {
            if(c == BASE_64_STR[i]){
                result = i;
                break;
            }
        }
        return result;
    }


    /**
     * byte数组转base64字符串的方法
     * @param bytes
     * @return
     */
    public static String convertBase64StrForBytes(byte[] bytes){
        int remainder = bytes.length % 3;

        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<bytes.length-2;i+=3){
            int[] temp = convertBinaryBase64(new byte[]{bytes[i],bytes[i+1],bytes[i+2]});
            for (int j : temp) {
                buffer.append(BASE_64_STR[j]);
            }

        }


        if(remainder!=0){
            int doFor = (bytes.length - remainder) / 3;
            int index = doFor * 3;
            int[] end = null;
            if(bytes.length-index==1){
                end = convertBinaryBase64(new byte[]{bytes[index]});
            }else if(bytes.length-index==2){
                end = convertBinaryBase64(new byte[]{bytes[index],bytes[index+1]});
            }
            for (int i : end) {
                buffer.append(BASE_64_STR[i]);
            }
        }

        return buffer.toString();

    }


}
