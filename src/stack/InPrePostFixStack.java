package stack;

import java.util.Stack;

public class InPrePostFixStack {
   public static void main(String[] args) {
      infixOperation("2*5+3/7");
   }
// todo doing some conversion mistake, wii do it later
   private static void infixOperation(String str) {
      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < str.length(); i++) {
         char ch = str.charAt(i);
         if (Character.isDigit(ch) || ch == '+' || ch == '-') {
            stack.push(ch);
            continue;
         }
         stack.push(operation(stack.pop(), ch, str.charAt(++i)));
      }
      while (stack.size() > 1) {
         stack.push(operation(stack.pop(), stack.pop(), stack.pop()));
      }
      System.out.println(stack.peek());

   }

   private static char operation(char op1, char operator, char op2) {
      int num1 = Character.getNumericValue(op1);
      int num2 = Character.getNumericValue(op2);
      if (operator == '/') return (char) ((num1 / num2) + '0');
      if (operator == '*') return (char) ((num1 / num2) + '0');
      if (operator == '+') return (char) ((num1 / num2) + '0');
      if (operator == '-') return (char) ((num1 / num2) + '0');
      return '_';
   }
}
