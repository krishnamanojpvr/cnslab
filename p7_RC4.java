// 7.Write a java program the RC4 logic using cryptography; encrypt the text "Hello World" using RC4. 
// Create your own key using java key tool

import java.util.*;

public class p7_RC4 {

    private byte[] S; // state array
    private int x, y; //

    // Constructor to initialize the key
    public p7_RC4(byte[] key) {
        this.x = 0;
        this.y = 0;
        this.S = new byte[256];
        init(key);
    }

    // Initialize the permutation in the array S
    private void init(byte[] key) {
        int keyLength = key.length;
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
        }
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + key[i % keyLength]) & 0xFF;
            swap(i, j);
        }
    }

    // Swap elements in the array S
    private void swap(int i, int j) {
        byte temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }

    // Generate the key stream and perform encryption/decryption
    public byte[] encrypt(byte[] plaintext) {
        byte[] ciphertext = new byte[plaintext.length];
        for (int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = (byte) (plaintext[i] ^ keyItem());
        }
        return ciphertext;
    }

    // Generate the next byte of the key stream
    private byte keyItem() {
        x = (x + 1) & 0xFF;
        y = (y + S[x]) & 0xFF;
        swap(x, y);
        return S[(S[x] + S[y]) & 0xFF];
    }

    public static void main(String[] args) {
        String keyString;
        String plaintext;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter encryption key:");
            keyString = sc.nextLine();

            System.out.print("Enter Plain text :");
            plaintext = sc.nextLine();
        }

        // Create RC4 instance with the key
        p7_RC4 rc4Encrypt = new p7_RC4(keyString.getBytes());
        byte[] encryptedBytes = rc4Encrypt.encrypt(plaintext.getBytes());

        p7_RC4 rc4Decrypt = new p7_RC4(keyString.getBytes()); // use two different objects for encryption and decryption
        byte[] decryptedBytes = rc4Decrypt.encrypt(encryptedBytes);

        System.out.println("Original : " + plaintext);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encryptedBytes));
        System.out.println("Decrypted: " + new String(decryptedBytes));

    }
}