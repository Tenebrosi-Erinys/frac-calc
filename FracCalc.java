import java.util.Scanner;
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
         //normalizes input to allow for any combination of capitals and lowercase
         input = input.toLowerCase();
         System.out.println(processCommand(input));
         if(input.startsWith("quit")){
            hasQuit = true;
         }
      }
   }
   public static String processCommand(String input){
      if(UnitTestRunner.processCommand(input)){
         return "";
      }
      else if(input.startsWith("help")){
         return processHelp(input);
      }
      else if(!input.startsWith("quit")){
         return input;
      }
      else{
         return "Quit";
      }
   }
   public static String processHelp(String input){
      return "Your commands are\n1: Help\n2: Quit\n3: Test 1";
   }
}
