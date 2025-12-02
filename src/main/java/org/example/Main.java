package org.example;

import java.util.Scanner;

public class Main {
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
                    System.out.println("Encrypt file selected.");
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
}