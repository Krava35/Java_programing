package serverModule.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataHasher {
    public static String hash(String data){
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] bytes = md.digest(data.getBytes());
            BigInteger integer = new BigInteger(1, bytes);
            String newData = integer.toString(16);
            while (newData.length() < 32 ){
                newData = "0" + newData;
            }
            return newData;
        } catch (NoSuchAlgorithmException e){
            System.out.println("Hash algorithm not found!");
            throw new IllegalStateException(e);
        }
    }
}
