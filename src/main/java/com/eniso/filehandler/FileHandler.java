/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eniso.filehandler;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author HP
 */

//-------------------------------------------------------------- Classe FileHandler --------------------------------------------------------------

public class FileHandler 
{
    
    public String filePath;
    public Integer fileSizeInKb, numberOfLines;
    public ArrayList <String> fileContent;
    
    //-------------------------------------------------------- Constructeur et Vérification du Chemin -----------------------------------------------
    
    public FileHandler(String filePath) throws FileNotFoundException
    {
        
        Path path = Paths.get(filePath);
        
        if(Files.exists(path))
        {
            if(Files.isRegularFile(path))
            {
                this.filePath = filePath;
                this.fileSizeInKb = 0;
                this.numberOfLines = 0;
                this.fileContent = new ArrayList();
                readFile();
            }

        }
        
        else
        {
            System.out.println("Erreur, Le chemin : "+filePath+"n'existe pas !");
        }
    }
    
    //-------------------------------------------------------- Lecture du Fichier et Traitement des Chaînes -----------------------------------------------
    
    public void readFile() throws FileNotFoundException
    {
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) 
        {
            this.fileContent.add(scanner.nextLine());
            this.numberOfLines++;       
        }
        
        this.fileSizeInKb = (int) file.length() / 1024;
    }
    
    public ArrayList<String> findLinesWithPattern(String pattern) 
    {
        ArrayList<String> linesWithPattern = new ArrayList<>();

        for (String line : this.fileContent)
        {
            String[] words = line.split(" ");
        
            for (String word : words)
            {
                if (word.contains(pattern))
                {
                    linesWithPattern.add(line);
                    break;
                }
            }
        }
        
        return linesWithPattern;
    }

    
    public boolean compareStrings1(String str1, String str2)
    {
        if(str1.equals(str2))
        {
            return true;
        }
        
        return false;
    }
    
    public boolean compareStrings2(String str1, String str2)
    {
        if(str1.equalsIgnoreCase(str2))
        {
            return true;
        }
        
        return false;
    }
    
    public boolean compareStrings3(String str1, String str2)
    {
        return str1 == str2;
    }
    
    //-------------------------------------------------------- Manipulation des Chaînes de Caractères -----------------------------------------------
    
    public String reverseString(int lineIndex) throws IOException
    {
        String line = Files.readAllLines(Paths.get(this.filePath)).get(lineIndex);
        
            String reversedLine = "";
            
            for(int i=line.length()-1;i>=0;i--)
            {  
                reversedLine+=line.charAt(i);
            }
            
            return reversedLine;
            
    }
    
    public StringBuilder removeDuplicates(int lineIndex) throws IOException
    {
        
        String line = Files.readAllLines(Paths.get(this.filePath)).get(lineIndex);
        
        char[] words = line.toCharArray();
        
        int index = 0;
 
        for (int i = 0; i < words.length; i++)
        {
 
            int j;
            for (j = 0; j < i; j++) 
            {
                if (words[i] == words[j])
                {
                    break;
                }
            }
 
            if (j == i) 
            {
                words[index++] = words[i];
            }
        }
        
        StringBuilder newLine = new StringBuilder(); 
        
        newLine.append(words); 
        
        return newLine;
    }
    
    //-------------------------------------------------------- Tests dans la Méthode Main -----------------------------------------------
    
    public static void main(String[] args) throws IOException 
    {
        FileHandler fileHandler = new FileHandler("E:\\Desktop\\IA1 Studies\\POO IA1.2\\TP3\\file.log");
        int lineIndexToReverse = 1;
        fileHandler.reverseString(lineIndexToReverse);
        int lineIndexToRemoveDuplicates = 2;
        fileHandler.removeDuplicates(lineIndexToRemoveDuplicates);

        String pattern = "epoch";
        ArrayList<String> linesWithPattern = fileHandler.findLinesWithPattern(pattern);
        String str1 = "hello";
        String str2 = "Hello";
        System.out.println("Comparaison avec equals : " + fileHandler.compareStrings1(str1, str2));

        String str5 = "hello";
        String str6 = "HELLO";
        System.out.println("Comparaison avec equalsIgnoreCase : " + fileHandler.compareStrings2(str5,str6));

        String str7 = "hello";
        String str8 = "hello";
        System.out.println("Comparaison avec == : " + fileHandler.compareStrings3(str7, str8));

        System.out.println("Compsaraison avec == : "+ fileHandler.compareStrings3(new String("hello"),"hello"));
        System.out.println("Comparaison avec == : "+ fileHandler.compareStrings3(new String("hello"),new String("hello")));

        if (!linesWithPattern.isEmpty()) 
        {
            System.out.println("Ligne contenant le motif '" + pattern + "': " + linesWithPattern.get(0));
        }

        else 
        {
            System.out.println("Le motif '" + pattern + "' n'a été trouvé dans aucune ligne.");
        }
    }
}

