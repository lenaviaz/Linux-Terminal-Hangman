import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class words {
    
    public String myWord (){

        int upper = 104334;

        int r = (int) (Math.random() * (upper - 0)) + 0;

        try (FileInputStream fs = new FileInputStream("american-english")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            
            
            for(int i = 0; i < r; ++i)
              br.readLine();
            String lineIWant = br.readLine();
            
            
            br.close();
            return lineIWant;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return theLetters;
    }

String word = myWord();

    //String word = "dkdalsdnawdnkjwnakjsdnkS";
    int wrongCounter = 1;
    String theLetters = "";
    String [] wordArray = new String[word.length()];

    List <String> guessedLetters = new ArrayList<String>();

    String[] parts = 
    {" ", " O", " ", "--", "|", "--", " /", " ", "\\"};

    public void setLetters(){
        for(int i = 0; i < word.length(); i++){
            theLetters += "_ ";
            wordArray[i] = "_ ";
        }
    };


    public void letters(){
  
        for(String a : wordArray){
            System.out.print(a);
        }
        System.out.println();

    }

    public void printGuessedLetters(){
        for(String a : guessedLetters){
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void printMan(){
        int count = 0;
/*         for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(body[i][j]);
                count++;

                if(count % 3 == 0){
                    System.out.println();
                }
            }
        } */

        for(int i = 0; i < parts.length; i++){

            System.out.print(parts[i]);
            count++;

            if(count % 3 == 0){
                System.out.println();
            }


        }
    }

    public void performCheck(String a){
        for(int i = 0; i < guessedLetters.size(); i++){
            if(guessedLetters.get(i).equals(a)){
                return;
            }
        }
        guessedLetters.add(a);
    }

    public void play() throws IOException { 
    
   // String aaah = myWord();
   // System.out.print(aaah);

    

    int rightCounter = 0;
    setLetters();

    Scanner in = new Scanner(System.in);

    do {
    System.out.print(word);
    //System.out.print(wrongCounter);
    boolean bool = false;
    
    System.out.println("\n\n\nWelcome to HangMan!!!!");
    printMan();
    System.out.println("");
    letters();
    System.out.println("Guessed Letters: ");
    printGuessedLetters();
    char guess = in.nextLine().charAt(0);

    if(guessedLetters.contains(String.valueOf(guess))){
        System.out.println("bleh");
        bool = true;

    } else {
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == guess){
                // they got it right
    
             wordArray[i] = String.valueOf(word.charAt(i));
             rightCounter++;
             //guessedLetters.add(String.valueOf(guess));----------
             performCheck(String.valueOf(guess));
             bool = true;
    
             if(rightCounter == wordArray.length){
                System.out.print("\033[H\033[2J");
                System.out.println("\n\n\nyou win!");
                printMan();
                System.out.println("");
                letters();
                 return;
             }
            } 
        }
    }

    if(bool == false){
    performCheck(String.valueOf(guess));
    if(parts[parts.length - wrongCounter] == " "){
       // System.out.println("im deleting two!!!");
        parts[parts.length - wrongCounter] = " ";
        parts[parts.length - wrongCounter - 1] = " ";
        wrongCounter+=2;
    } else {
        parts[parts.length - wrongCounter] = " ";
        wrongCounter++;
        }
    }

    printMan();
    System.out.print("\033[H\033[2J");
    } while(wrongCounter < 9);

    System.out.println("you lost! The word was : " + word);

    in.close();
    }

}
