import java.util.*;
public class FracCalc{
   public static Scanner userInput = new Scanner(System.in); 
   public static void main(String args[]){
      //hasQuit is a boolean that returns true when "quit" is input
      boolean hasQuit = false;
      //the loop stops when hasQuit returns true, so that it exits the loop and the program
      while(!hasQuit){
         System.out.print("Enter: ");
         String input = userInput.nextLine();
         Scanner parser = new Scanner(input);
         while(!input.matches(".+")){
            System.out.print("At least enter something.\nEnter: ");
            input = userInput.nextLine();
         }
         System.out.println(processCommand(input));
         System.out.println();
         if(input.toLowerCase().startsWith("quit")){
            hasQuit = true;
         }
      }
   }
   public static String processCommand(String input){
      //turning to lowercase normalizes input to allow for any combination of capitals and lowercase
      if(UnitTestRunner.processCommand(input)){
         return "";
      } else if(input.toLowerCase().startsWith("help")){
         return processHelp(input);
      } else if(input.matches("\\d+/?\\d*[+-*]\\d+/?\\d*")){
         return(processExpression(input));
      } else if(input.toLowerCase().matches("let'?s play.*")){
         return "Game over.";
      } else if(input.toLowerCase().startsWith("command")){
         return "What command?";
      } else if(input.toLowerCase().startsWith("something")){
         return "Cheeky.";
      } else if(!input.toLowerCase().startsWith("quit")){
         return input;
      } else {
         return "Quit";
      }
   }
   public static String processHelp(String input){
      Scanner parser = new Scanner(input);
      if(input.length() >= 5){
         if(input.substring(5).contains("help")){
            return "Returns description of a command\nHelp <command>";
         } else if(input.substring(5).startsWith("quit")){
            return "Quits the program";
         } else if(input.substring(5).startsWith("test")){
            return "Runs the unit tester\nTest <testnumber>";
         } else if(input.substring(5).startsWith("me")){
            return "I'm sorry, I don't know if I can help you, man";
         } else if(input.substring(5).matches("command|<command>")){
            return "You think you're cheeky, don't you";
         } else {
            return input.substring(5) + " is not a valid command";
         }
      } else {
         return "Your commands are\n1: Help\n2: Quit\n3: Test 1\nEnter Help <command> for details";
      }
   }
   public static String processExpression(String input){
      input = input.replaceAll("\\s","");
      //find the index of the first +-*/ character
      int index = 0;
      char obj;
      if(input.contains("+")){
         obj = '+';
      } else if(input.contains("-")){
         index = input.indexOf("-");
      } else if(input.contains("*")){
         index = input.indexOf("*");
      } else {
         index = input.indexOf("/");
      }
      double firstPart = Double.parseDouble(input.substring(0, index));
      double secondPart = Double.parseDouble(input.substring(index + 1));
      String returnValue = "";
      return returnValue;
   }
}