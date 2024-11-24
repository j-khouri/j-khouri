package org.cs362.escaperoom;

@SuppressWarnings("unused")
public class Puzzle {
  
   private int puzzleNum;
   private String question;


   public String getQuestion() {
       return question;
   }


   public void setQuestion(String question) {
       this.question = question;
   }

   public Puzzle() {
       question = "";
   }

   public Puzzle(int puzzleNum, String question) {
       this.puzzleNum = puzzleNum;
       this.question = question;
   }  
}




