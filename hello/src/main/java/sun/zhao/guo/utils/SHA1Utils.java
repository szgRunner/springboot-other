package sun.zhao.guo.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: SHA1Utils
 * @Description: sha1加密
 */
public class SHA1Utils {
    /**
     * 加密方法
     */
    private static final String MAC_NAME = "HmacSHA1";
    
    /**
     * 使用HMAC_SHA1 算发进行加密
     * @param paranStr 待加密字符串
     * @param key      秘钥长度（16进制字符串）
     * @return
     */
    public static String encrypt(String paranStr,String key){
        // 用于存储加密后的16进制的字符串
        try {
            byte[] data = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKey = new SecretKeySpec(data, MAC_NAME);
            Mac mac = Mac.getInstance(MAC_NAME);
            mac.init(secretKey);
            byte[] text = paranStr.getBytes(StandardCharsets.UTF_8);
            byte[] bytes = Base64.encodeBase64(mac.doFinal(text));
            return new String(bytes);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String strTo16(byte[] bytes){

        StringBuilder stringBuilder = new StringBuilder();
//        String temp;
//        for (int i = 0; bytes != null && i < bytes.length; i++) {
//            temp = Integer.toHexString(bytes[i] & 0XFF);
//            if (temp.length() == 1) {
//                stringBuilder.append('0');
//            }
//            stringBuilder.append(temp);
//        }
        for (byte aByte : bytes) {
            stringBuilder.append(String.format("%02x", aByte));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(Base64.encodeBase64String("8d880730645fb35b8207d4451eb519cd90c6c357".getBytes()));
    }

}
