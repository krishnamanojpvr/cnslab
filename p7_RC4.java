// 7.Write a java program the RC4 logic using cryptography; encrypt the text "Hello World" using RC4. 
// Create your own key using java key tool

import java.util.*;

public class p7_RC4 {

    public static byte[] rc4(byte[] data, byte[] key) {
        int[] S = new int[256];
        int j = 0;

        // Key Scheduling Algorithm (KSA)
        for (int i = 0; i < 256; i++)
            S[i] = i;

        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + key[i % key.length]) & 255;
            int temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }

        // Pseudo-Random Generation Algorithm (PRGA)
        byte[] result = new byte[data.length];
        int i = 0;
        j = 0;

        for (int k = 0; k < data.length; k++) {
            i = (i + 1) & 255;
            j = (j + S[i]) & 255;

            int temp = S[i];
            S[i] = S[j];
            S[j] = temp;

            int keyStream = S[(S[i] + S[j]) & 255];
            result[k] = (byte) (data[k] ^ keyStream);
        }

        return result;
    }

    public static void main(String[] args) {

        String key = "mysecretkey"; // user-defined key
        String plaintext = "Hello World"; // given text

        byte[] encrypted = rc4(plaintext.getBytes(), key.getBytes());
        byte[] decrypted = rc4(encrypted, key.getBytes());

        System.out.println("Original Text : " + plaintext);
        System.out.println("Encrypted Text: " + Base64.getEncoder().encodeToString(encrypted));
        System.out.println("Decrypted Text: " + new String(decrypted));
    }
}