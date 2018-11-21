import java.util.Scanner;
public class FracCalc{
   
   public static Scanner userInput = new Scanner(System.in); 
   public static void main(String args[]){
      //the loop stops when hasQuit returns true, so that it exits the loop and the program
      while(true){
         System.out.print("Enter: ");
         String input = userInput.nextLine();
         //normalizes input to allow for any combination of capitals and lowercase
         input = input.toLowerCase();
         if(input.startsWith("quit")){
            break;
         }
         System.out.println(processCommand(input));
      }
   }
   public static String processCommand(String input){
      if(UnitTestRunner.processCommand(input)){
         return "";
      }
      else if(input.startsWith("help")){
         return processHelp();
      }
      else if(input.matches(".+[-+*\\/].+")){
         return processExpressions(input);
      }
      else {
         return input;
      }
   }
   public static String processHelp(){
      return "Your commands are\n1: Help\n2: Quit\n3: Test 1\n4: Any expression.\nTo write an expression, write fractions seperated by spaces.\nTo write mixed numbers, write the whole number, followed by an underscore, followed by the fraction.";
   }
   public static int processWholeNum(String number){
      if(number.contains("_")){
         return Integer.parseInt(number.substring(0, number.indexOf("_")));
      } else if(!number.contains("/")){
         return Integer.parseInt(number);
      } else {
         return 0;
      }
   }
   public static String processExpressions(String input){
      //initializes all of these integers
      int whole1, whole2, num1, num2, den1, den2;
      Scanner parser = new Scanner(input);
      //find first whole number
      String firstEntireNum = parser.next();
      String op = parser.next();
      String secondEntireNum = parser.next();
      whole2 = processWholeNum(secondEntireNum);
      whole1 = processWholeNum(firstEntireNum);
      num1 = processNumerator(firstEntireNum);
      num2 = processNumerator(secondEntireNum);
      den1 = processDenominator(firstEntireNum);
      den2 = processDenominator(secondEntireNum);
      //normalize the fraction
      if(den1 < 0){
         den1 = -den1;
         num1 = -num1;
      }
      if(den2 < 0){
         den2 = -den2;
         num2 = -num2;
      }
      parser.close();
      return "Op:" + op + " Whole:" + whole2 + " Num:" + num2 + " Den:" + den2;
   }
   public static int processNumerator(String number){
      if(number.contains("_")){
         return Integer.parseInt(number.substring(number.indexOf("_") + 1, number.indexOf("/")));
      } else if(number.contains("/")){
         return Integer.parseInt(number.substring(0, number.indexOf("/")));
      } else {
         return 0;
      }
   }
   public static int processDenominator(String number){
      if(number.contains("/")){
         return Integer.parseInt(number.substring(number.indexOf("/") + 1));
      } else {
         return 1;
      }
   }
}
