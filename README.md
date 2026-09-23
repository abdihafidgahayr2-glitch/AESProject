# AES File Encryption Tool

A Java console application for encrypting and decrypting files using AES, built to practice applied cryptography — key generation, file I/O, and secure data handling.

## 👤 Author

**Abdihafid Gahayr** — [LinkedIn](https://www.linkedin.com/in/abdihafid-gahayr/) | [GitHub](https://github.com/abdihafidgahayr2-glitch)

## 📋 Project Overview

The program lets a user encrypt any file with a randomly generated AES key, then decrypt it again by supplying that key. It was built in stages: first the project structure and menu system, then file encryption, then file decryption with full key validation and error handling.

## ✨ Features

- **Menu-driven interface** — simple options 1–3 with input validation for invalid choices
- **File encryption** — reads a file, encrypts it, and writes the result to `ciphertext.txt`
- **Random AES key generation** — generates a new 128-bit AES key for every encryption
- **Hex key display** — shows the encryption key as a 32-character hex string for the user to save
- **File decryption** — decrypts `ciphertext.txt` back into `plaintext.txt` using a supplied key
- **Key validation** — checks that a supplied key is exactly 32 hex characters and correctly formatted before attempting decryption
- **Error handling** — detailed error messages for invalid keys or failed decryption

## 🏗️ Tech Stack

- **Language:** Java
- **Core class:** `AESUtil` — handles encryption, decryption, key generation, and hex/byte conversion

## 📁 Project Structure

```
AESProject/
├── Main.java       # Menu system and entry point
└── AESUtil.java     # AES encryption/decryption, key generation, hex/byte helpers
```

## 🚀 Getting Started

### Prerequisites
- Java JDK

### Run it
```bash
javac Main.java AESUtil.java
java Main
```

## 🎮 Usage

1. Run `Main.java`
2. Choose option 1 to encrypt a file
3. Save the displayed 32-character hex key somewhere safe — it's needed for decryption
4. Choose option 2 to decrypt a file
5. Enter the key when prompted
6. Find the results in `ciphertext.txt` (encrypted) or `plaintext.txt` (decrypted)

## 🔒 How It Works

- **Encryption:** `encryptFile` reads the target file, generates a fresh random AES key, encrypts the contents, and writes the ciphertext to `ciphertext.txt` — the key is displayed as hex so it can be saved and reused later
- **Decryption:** `decryptFile` validates the supplied hex key (must be exactly 32 hex characters), converts it back to bytes, and decrypts `ciphertext.txt` into `plaintext.txt`, with clear error messages if the key is invalid or malformed
