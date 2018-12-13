import java.util.Scanner;
public class FracCalc{
   //scanner is public to the entire class
   //reduce lines in processExpressions
   public static Scanner console; 
   public static void main(String args[]){
      console = new Scanner(System.in);
      //the loop stops when hasQuit returns true, so that it exits the loop and the program
      while(true){
         //next two lines allow user to input a line
         System.out.print("Enter: ");
         String input = console.nextLine();
         //normalizes input to allow for any combination of capitals and lowercase
         input = input.toLowerCase();
         //check if you should quit before processing the commands
         if(input.startsWith("quit")){
            break;
         }
         //print the return value of processCommand, which processes the command given
         System.out.println(processCommand(input));
      }
   }
   
   //processes the command given in a parameter
   public static String processCommand(String input){
      //checks if the unit test runner returns true, and lets unit test runner do all the work
      if(!input.equals("") && UnitTestRunner.processCommand(input)){
         return "";
      //if help is in the command, return help
      } else if(input.startsWith("help")){
         return processHelp();
      //if the input matches the correct type of expression
      } //else if(input.matches(".+ [-+*\\/] .+")){
      return processExpressions(input);

   }
   
   public static String processHelp(){
      String returnString = "";
      returnString += "Your commands are\n1: Help\n2: Quit\n3: Test 1\n";
      returnString += "\nTo write an expression, write any combination of fractions and whole numbers,\nseperated by an expressional operator, with spaces between all tokens.\n";
      returnString += "A fraction is in the form of a_b/c or b/c.";
      return returnString;
   }
   
   public static String processExpressions(String input){
      //initializes all of these integers
      int whole1, whole2, num1, num2, den1, den2;
      String firstEntireNum, op, secondEntireNum;
      Scanner parser = new Scanner(input);
      //if the input is invalid, this, instead of crashing the program, tells the user
      try{
         //next 3 lines of code parses the input into 3 seperate tokens
         firstEntireNum = parser.next();
         op = parser.next();
         secondEntireNum = parser.next();
         //parses the first/second numbers into seperate numbers
         whole2 = processWholeNum(secondEntireNum);
         whole1 = processWholeNum(firstEntireNum);
         //parses the first/second numerator
         num1 = processNumerator(firstEntireNum);
         num2 = processNumerator(secondEntireNum);
         //parses the first/second denominator
         den1 = processDenominator(firstEntireNum);
         den2 = processDenominator(secondEntireNum);
      } catch (Exception e){
         return "Not a valid expression";
      }
      //normalize the fraction
      if(den1 < 0){
         den1 = -den1;
         num1 = -num1;
      }
      if(den2 < 0){
         den2 = -den2;
         num2 = -num2;
      }
      num1 = mixedToImproper(whole1, num1, den1);
      whole1 = 0;
      num2 = mixedToImproper(whole2, num2, den2);
      whole2 = 0;
      
      //makes both fractions have the same denominator
      int fDen = den1 * den2;
      num1 *= den2;
      num2 *= den1;
      
      if(op.equals("+")){
         num1 += num2;
      } else if(op.equals("-")){
         num1 -= num2;
      } else if(op.equals("*")){
         num1 *= num2;
         fDen *= fDen;
      } else if(op.equals("/")){
         num1 *= fDen;
         fDen *= num2;
      }
      if(fDen < 0 && num1 < 0){
         fDen = -fDen;
         num1 = -num1;
      }
      for(int test = 2; test <= fDen; test++){
         while(fDen % test == 0 && num1 % test == 0){
            fDen /= test;
            num1 /= test;
         }
      }
      if(num1 != 0){
         whole1 = 0;
         while(Math.abs(num1) >= fDen){
            if(num1 < 0){
               num1 += fDen;
               whole1--;
            }
            if(num1 > 0){
               num1 -= fDen;
               whole1++;
            }
         }
      }
      if(whole1 < 0 && num1 < 0){
         num1 = -num1;
      }
      String returnString = "";
      if(whole1 != 0){
         returnString += whole1;
      }
      if(whole1 != 0 && num1 != 0){
         returnString += "_";
      }
      if(num1 != 0){
         returnString += num1 + "/" + fDen;
      }
      if(whole1 == 0 && num1 == 0){
         returnString = "0";
      }
      //if the input still has more, call processExpressions again
      if(parser.hasNext()){
         return processExpressions(returnString + parser.nextLine());
      }
      //final return, replace all _ with spaces to work with the unit test
      else{
         returnString = returnString.replaceAll("_"," ");
         return returnString;
      }
   }
   //turns mixed fractions into improper, returns the new numerator
   public static int mixedToImproper(int whole, int num, int den){
      if(whole < 0){
         num = -num + whole * den;
      } else {
         num += whole * den;
      }
      return num;
   }
   public static int processWholeNum(String number){
      //if the number has a _, then the whole number is between the start and _.
      if(number.contains("_")){
         return Integer.parseInt(number.substring(0, number.indexOf("_")));
      //if the number doesn't have a / either, then the number itself is the whole number.
      } else if(!number.contains("/")){
         return Integer.parseInt(number);
      //the only other possible answer is no _, but one /. That means there is no whole number at all.
      } else {
         return 0;
      }
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