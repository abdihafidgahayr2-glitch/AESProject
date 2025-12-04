package org.example;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.util.Scanner;

public class Main {


    private static String lastGeneratedKey = "";

     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("AES FILE ENCRYPTION SYSTEM");

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Encrypt a File");
            System.out.println("2. Decrypt a File");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    encryptFile(scanner);
                    break;
                case "2":
                    System.out.println("Decrypt file selected.");
                    break;
                case "3":
                    System.out.println("\nExiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }

    private static void encryptFile(Scanner scanner) {
        try {

            System.out.println("\n=== FILE ENCRYPTION ===");
            System.out.print("Enter filename to encrypt: ");
            String filename = scanner.nextLine().trim();

            // Check if file exists
            File file = new File(filename);
            if (!file.exists() || !file.canRead()) {
                System.out.println("Error: Cannot read file '" + filename + "'");
                System.out.println("Make sure the file exists in: " + System.getProperty("user.dir"));
                return;
            }

            System.out.println("Encrypting file: " + filename);

            // Read file
            FileInputStream fis = new FileInputStream(file);
            byte[] fileData = fis.readAllBytes();
            fis.close();

            // Generate AES key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128, new SecureRandom());
            SecretKey secretKey = keyGen.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            lastGeneratedKey = bytesToHex(keyBytes);

            // Encrypt data
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(fileData);

            // Write encrypted file
            FileOutputStream fos = new FileOutputStream("ciphertext.txt");
            fos.write(encryptedData);
            fos.close();

            // Display results
            System.out.println("\n" + "=".repeat(40));
            System.out.println("ENCRYPTION SUCCESSFUL!");
            System.out.println("=".repeat(40));
            System.out.println("Original file: " + filename);
            System.out.println("Encrypted file: ciphertext.txt");
            System.out.println("\nENCRYPTION KEY:");
            System.out.println(lastGeneratedKey);
            System.out.println("\nIMPORTANT: Save this key for decryption!");
            System.out.println("=".repeat(40));

        }
        catch (Exception e)
        {
            System.out.println("Encryption failed: " + e.getMessage());
        }
    }
    //Convert bytes to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02X", b));
        }
        return hex.toString();
    }


}