package org.example;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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
                    decryptFile(scanner);
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

            // Check if ciphertext.txt already exists
            File outputFile = new File("ciphertext.txt");
            if (outputFile.exists()) {
                System.out.print("ciphertext.txt already exists. Overwrite? (y/n): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("y") && !response.equals("yes")) {
                    System.out.println("Encryption cancelled.");
                    return;
                }
            }

            System.out.println("Encrypting file: " + filename);

            // Read file
            FileInputStream fis = new FileInputStream(file);
            byte[] fileData = fis.readAllBytes();
            fis.close();

            // Generate AES key
            SecretKey secretKey = AESUtil.generateAESKey();
            byte[] keyBytes = secretKey.getEncoded();
            lastGeneratedKey = bytesToHex(keyBytes);

            // Encrypt data
            byte[] encryptedData = AESUtil.encryptData(fileData, secretKey);


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


    private static void decryptFile(Scanner scanner) {
        try {
            System.out.println("\n=== FILE DECRYPTION ===");
            System.out.print("Enter encrypted filename: ");
            String filename = scanner.nextLine().trim();

            // Check if file exists
            File file = new File(filename);
            if (!file.exists() || !file.canRead()) {
                System.out.println("Error: Cannot read file '" + filename + "'");
                return;
            }

            System.out.print("Enter 32-character hex key: ");
            String keyHex = scanner.nextLine().trim();

            // Validate key
            if (keyHex.length() != 32 || !keyHex.matches("[0-9A-Fa-f]+")) {
                System.out.println("Error: Key must be 32 hex characters (0-9, A-F)");
                return;
            }

            // Check if plaintext.txt already exists
            File outputFile = new File("plaintext.txt");
            if (outputFile.exists()) {
                System.out.print("plaintext.txt already exists. Overwrite? (y/n): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("y") && !response.equals("yes")) {
                    System.out.println("Decryption cancelled.");
                    return;
                }
            }

            System.out.println("Decrypting file: " + filename);

            // Read encrypted file
            FileInputStream fis = new FileInputStream(file);
            byte[] encryptedData = fis.readAllBytes();
            fis.close();

            // Convert hex key to bytes
            byte[] keyBytes = hexToBytes(keyHex);
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

            // Decrypt data
            byte[] decryptedData = AESUtil.decryptData(encryptedData, secretKey);

            // Write decrypted file
            FileOutputStream fos = new FileOutputStream("plaintext.txt");
            fos.write(decryptedData);
            fos.close();

            // Display results
            System.out.println("\n" + "=".repeat(40));
            System.out.println("DECRYPTION SUCCESSFUL!");
            System.out.println("=".repeat(40));
            System.out.println("Encrypted file: " + filename);
            System.out.println("Decrypted file: plaintext.txt");
            System.out.println("=".repeat(40));

        } catch (Exception e) {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("DECRYPTION FAILED!");
            System.out.println("=".repeat(40));
            System.out.println("Error: " + e.getMessage());
            System.out.println("\nPossible causes:");
            System.out.println("1. Wrong encryption key");
            System.out.println("2. File was not encrypted with AES");
            System.out.println("3. File is corrupted");
            System.out.println("=".repeat(40));
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

    // Convert hex string to bytes
    private static byte[] hexToBytes(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            int index = i * 2;
            bytes[i] = (byte) Integer.parseInt(hex.substring(index, index + 2), 16);
        }
        return bytes;
    }


}