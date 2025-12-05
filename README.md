  ### Project setup with structure and AES utilities
- Created main menu system with options 1-3 and loop
- Added error handling for invalid menu choices
- Created AESUtil class with encryption/decryption methods
- Added random AES key generation (128-bit)
- Added hex key validation method

  
 ### Implemented file encryption feature
- Added encryptFile method with file reading/writing
- Generated random AES keys for each encryption
- Displayed hex key to user for saving
- Created ciphertext.txt output file
- Added bytesToHex helper method

### Added file decryption feature
- Implemented decryptFile method with key validation
- Added hexToBytes helper method for key conversion
- Added detailed error messages for decryption failures
- Created plaintext.txt output file
- Added key format validation 32 hex characters    

### Usage:
1. Run Main.java
2. Choose option 1 to encrypt a file
3. Save the displayed 32 character hex key
4. Choose option 2 to decrypt a file
5. Enter the key when prompted
6. Find results in ciphertext.txt (encrypted) or plaintext.txt (decrypted)