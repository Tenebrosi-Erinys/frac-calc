import java.util.Scanner;
public class FracCalc{
   static char op;
   public static Scanner userInput = new Scanner(System.in); 
   public static void main(String args[]){
      //the loop stops when hasQuit returns true, so that it exits the loop and the program
      while(true){
         System.out.print("Enter: ");
         String input = userInput.nextLine();
         Scanner parser = new Scanner(input);
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
      return "Your commands are\n1: Help\n2: Quit\n3: Test 1";
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
      int whole1 = 0;
      int whole2 = 0;
      int num1 = 0;
      int num2 = 0;
      int den1 = 0;
      int den2 = 0;
      input = input.replaceAll(" ", "");
      //find first whole number
      
      String firstEntireNum = findFirstNum(input);
      System.out.println(firstEntireNum);
      System.out.println(op);
      
      String secondEntireNum = findFirstNum(input);
      //whole2 = processWholeNum(secondEntireNum);
      //whole1 = processWholeNum(firstEntireNum);
      //tnum1 = processNumerator(firstEntireNum);
      //num2 = processNumerator(secondEntireNum);
      //den1 = processDenominator(firstEntireNum);
      //den2 = processDenominator(secondEntireNum);
      return "";
     // return "Op:" + op + " Whole:" + whole2 + " Num:" + num2 + " Den:" + den2;
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
         return 0;
      }
   }
   public static String findFirstNum(String s){
      boolean hasDivided = false;
      String num = "";
      //matches (-num), num, /num, _num
      for(int i = 0; s.substring(i, i + 1).matches("[-\\d\\/_]"); i++){
         if(s.charAt(i) == '/'){
            if(hasDivided == true){
               return num;
            }
            hasDivided = true;
         }
         num = num + s.charAt(i);
         op = s.charAt(i + 1);
      }
      return num;
   }
}
