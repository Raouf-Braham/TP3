/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eniso.filehandler;

/**
 *
 * @author HP
 */

public class XORCipher {
    private String key;

    public XORCipher(String key) {
        this.key = key;
    }

    //----------------------------------------------- Méthode pour convertir une chaîne de caractères en binaire -----------------------------------------------
    private String stringToBinary(String s) {
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String binaryChar = Integer.toBinaryString(s.charAt(i));
            // Ajouter des zéros à gauche pour obtenir 8 bits par caractère
            while (binaryChar.length() < 8) {
                binaryChar = "0" + binaryChar;
            }
            binaryString.append(binaryChar);
        }
        return binaryString.toString();
    }

    //----------------------------------------------- Méthode pour convertir une chaîne binaire en texte -----------------------------------------------
    private String binaryToString(String binaryString) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i += 8) {
            String binaryChar = binaryString.substring(i, i + 8);
            int charCode = Integer.parseInt(binaryChar, 2);
            text.append((char) charCode);
        }
        return text.toString();
    }

    //-------------------------------------------------------- Méthode de chiffrement XOR -----------------------------------------------
    public String encrypt(String plaintext) {
        String plaintextBinary = stringToBinary(plaintext);
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < plaintextBinary.length(); i++) {
            char plaintextBit = plaintextBinary.charAt(i);
            char keyBit = key.charAt(i % key.length());
            char encryptedBit = (char) (plaintextBit ^ keyBit);
            encryptedText.append(encryptedBit);
        }
        return encryptedText.toString();
    }

    //-------------------------------------------------------- Méthode de déchiffrement XOR -----------------------------------------------
    
    public String decrypt(String ciphertext) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char ciphertextBit = ciphertext.charAt(i);
            char keyBit = key.charAt(i % key.length());
            char decryptedBit = (char) (ciphertextBit ^ keyBit);
            decryptedText.append(decryptedBit);
        }
        return binaryToString(decryptedText.toString());
    }
    
    //-------------------------------------------------------- Tests dans la Méthode Main -----------------------------------------------

    public static void main(String[] args) {
        // Clé de chiffrement
        String key = "secret";
        // Texte clair à chiffrer
        String plaintext = "Hello, world";

        // Création de l’objet XORCipher
        XORCipher xorCipher = new XORCipher(key);

        // Chiffrement du texte clair
        String encryptedText = xorCipher.encrypt(plaintext);
        System.out.println("Texte chiffré : " + encryptedText);

        // Déchiffrement du texte chiffré
        String decryptedText = xorCipher.decrypt(encryptedText);
        System.out.println("Texte déchiffré : " + decryptedText);
    }
}
