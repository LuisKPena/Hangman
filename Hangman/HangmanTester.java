/**
*Tester class for interactive game of Hangman.
*
*@author Luis Pena
*@version (02/01/2022)
*
*/

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;


public class HangmanTester
{
   public static void main(String [] args)
   {
   
   //Instantiating Scanner input object
   
   Scanner userInput = new Scanner(System.in);
   
      
   //Instantiating Random object
   
   Random randomizer = new Random();
   
   
   //Instantiating array list objects
   
   ArrayList<Character> oldLetters = new ArrayList<Character>();
   ArrayList<Integer> indexList = new ArrayList<Integer>();
    
   
   
   //Declaring global variables
   
   String [] wordList = new String[10];
   String challengeWord = "";
   char userLetter = '\0';
   int mainMenuChoice = 0;
   int wordCounter = 0;
   int answerIndex;
   int gameRestart = 1;
     
   
   //Hangman object instantiation
   
   Hangman newGame = new Hangman(challengeWord, userLetter);

   
   //Challenge word pool
   
   wordList[0] = "Dragon"; 
   wordList[1] = "Wizard"; 
   wordList[2] = "Elves";
   wordList[3] = "Hobbit";
   wordList[4] = "Genie";
   wordList[5] = "Centaur"; 
   wordList[6] = "Warlock";
   wordList[7] = "Wyvern";
   wordList[8] = "Mermaid"; 
   wordList[9] = "Vampire";
  
  
   //While loop for menu and game if player wants to replay
         
   while(gameRestart == 1 && mainMenuChoice != 3)
   { 
   
      //Declaring local variables
      
      int userLives = 8;
      int randomIndex = 0;
      String censorUpdater = "";
      int gameRulesChoice = 0;
      int [] usedWords = new int[10];
      
      
      //Randomizer and setter methods for challenge word
      
      for(int i = 0; i < 10; i++)
      { 
         if(usedWords[i] == randomIndex)
         {   
            randomIndex = randomizer.nextInt(wordList.length); 
         }
         
         else{
            usedWords[i] = randomIndex;
            break;
         }
      }              
         
      challengeWord = wordList[randomIndex];
      newGame.setWord(challengeWord);  
       
            
      //Word counter for used words list
      
      wordCounter++;
      
      if(wordCounter > 9)
      {
         System.out.println("\nOut of usable words! Thank you for playing!");
         System.exit(1);
      }

      
      //Challenge word censor array
   
      char [] censoredWord = new char[challengeWord.length()];
   
      for(int i = 0; i < censoredWord.length; i++)
      {
         censoredWord[i] = '*';
      }
   
     
      //Main menu U.I.
   
      System.out.println("\n-= Let's Play Hangman =-");
      System.out.println("\n\nHello player! Welcome to Hangman!");
   
      System.out.println("\n\nPlease make a selection: " + "\n\n1.) Rules"
                        + "\n2.) New Game" + "\n3.) Quit");
   
      System.out.print("\nPlease make a numerical selection: ");
      mainMenuChoice = userInput.nextInt();
   
      
      //Game rules output and menu
   
      if(mainMenuChoice == 1)
      {
         System.out.println("\nHow to Play:" 
      
                           + "\n\nThe Executioner will choose a word at " 
                           + "\nrandom from a pool of preselected words. " 
                           + "\nEach word will be censored and displayed " 
                           + "\nwith \'*\'. You will be allowed 8 tries "
                           + "\nto guess the word, one letter at a time. " 
                           + "\nIf you guess the word correctly within " 
                           + "\nthe alloted chances, you win the game of "
                           + "\nHangman and live to see another day. "
                           + "\n\nOtherwise you will meet an unfortunate "
                           + "\nend...");
                         
         System.out.print("\nPlease enter 1 to start a new game or 2 to quit: ");
      
         gameRulesChoice = userInput.nextInt();
      
      }
   
   
      //Main game screen output
   
      if(mainMenuChoice == 2 || gameRulesChoice == 1)
      {
      
         //Life counter and challenge word output
      
         System.out.println("\nCurrent lives: " + userLives);
         System.out.print("\nYour challenge word: ");
      
         censorUpdater = String.valueOf(censoredWord);
         System.out.println(censorUpdater);
      
      
         //While loop for main game
      
         while(userLives > 0 && censorUpdater.contains("*"))
         {
         
            //User letter guess input and validation
         
            do
            {
            System.out.print("\nPlease input a letter: ");
            userLetter = userInput.next().toLowerCase().charAt(0);
            
               if(oldLetters.contains(userLetter))
               {
                  System.out.println("\nThis letter has already be used. " 
                                    +"Please try a different letter.");
               }
            }
         
            while(oldLetters.contains(userLetter));
               
      
            //User letter setter for Hangman class object
       
            newGame.setUserLetter(userLetter);
         
         
            //Adding user letter to list of used letters
         
            oldLetters.add(userLetter);
         
      
            //User guess correctness checker 
      
            newGame.checkAnswer();
    
      
            //User guess correctness output and life counter/censored word updaters  
         
            if(newGame.getAnswer() && censorUpdater.contains("*"))
            {            
         
               //Word decensor method for correct guesses

               indexList = newGame.getIndexes();

               for(int i = 0; i < indexList.size(); i++)
               {
                  answerIndex = indexList.get(i);
               
                  censoredWord[answerIndex] = userLetter;
            
                  if(censoredWord[0] != '*')
                  {
                     censoredWord[0] = Character.toUpperCase(censoredWord[0]);
                  }
               }
            
            
               //Life counter and updated censored word output
            
               System.out.println("\nCorrect! Good job! :)");
                  
               System.out.println("\nCurrent lives: " + userLives);
               System.out.print("\nYour challenge word: ");
         
               censorUpdater = String.valueOf(censoredWord);
               System.out.println(censorUpdater);
            
            
               //Victory message if censored word decensored completely
            
               if(!censorUpdater.contains("*"))
               {
                  System.out.println("\n\nCONGRATULATIONS! You win! :)");
               }
            }
      
         
            else
            {
               userLives--;
            
            
               //Updated life counter and censored word output
            
               System.out.println("\nSorry, that is incorrect! :(");
         
               System.out.println("\nCurrent lives: " + userLives);
               System.out.print("\nYour challenge word: ");
         
               censorUpdater = String.valueOf(censoredWord);
               System.out.println(censorUpdater);
            
            
               //Game over message if lives < 0
            
               if(userLives == 0)
               {
                  System.out.println("\n\nOh no! Game Over! :(");
               
                  System.out.println("\nYour word was: " + challengeWord);
               }     
            }
         }
      
            //Resetting of used letters list after game ends
         
            oldLetters.clear();
            censorUpdater ="";
      
      
            //Play again or quit options output
         
            System.out.println("\n\nWould you like to play again?");
            System.out.print("\nEnter 1 to start a new game or 2 to quit: ");         
      }

   
      //Menu quit method
   
      else if(mainMenuChoice == 3 || gameRulesChoice == 2)
      {
         System.out.println("\nThank you for playing! Goodbye!");
         System.exit(1);
      }
      
      
      //Restart game input for user
      
      gameRestart = userInput.nextInt();
      
      
      //Restart menu quit method
      if(gameRestart == 2)
      {
         System.out.println("\nThank you for playing! Goodbye!");
         System.exit(1);  
      } 
   }   
   }
}