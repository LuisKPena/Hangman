/**
*Constructor class for interactive game of Hangman.
*
*@author Luis Pena
*@version (01/27/2022)
*
*/

import java.util.ArrayList;

public class Hangman
{

   //ArrayList object instantiation

   private ArrayList<Integer> letterIndexes = new ArrayList<Integer>();


   //Intialization of variables
   
   private String currentWord;
   private char currentLetter;
   private boolean answer;   

  
   //Constructor for one instance of hangman game object
   
   public Hangman(String currWord, char currLetter)
   {
      currentWord = currWord;
      currentLetter = currLetter;
   }
   
   
   //Setters for hangman game object variables
   
   public void setWord(String word)
   {
      currentWord = word;
   }
   
   public void setUserLetter(char currUserLetter)
   {
      currentLetter = currUserLetter;
   }
   
   
   //Getters for hangman game object variables   
   
   public String getWord()
   {
      return currentWord;
   }
   
   public char getUserLetter()
   {
      return currentLetter;
   }
   
   
   //Method for checking user answer 
   
   public void checkAnswer()
   {
      
      //Answer boolean and letterIndexes array list resetter
      
      answer = false;
      letterIndexes.clear();
      
      
      //Check user answer and append index to array list if correct
      
      for(int i = 0; i < currentWord.length(); i++)
      {
         if(currentWord.toLowerCase().charAt(i) == currentLetter)
         {
            answer = true; 
            letterIndexes.add(i);
         }
       }          
   }
   
   
   //Answer boolean variable getter
   
   public boolean getAnswer()
   { 
      return answer;
   }
   
   
   //Index getter for correct guess location in challenge word
   
   public ArrayList<Integer> getIndexes()
   {
      return letterIndexes;
   }  
}