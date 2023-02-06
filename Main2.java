import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your expression : ");
        String str = sc.nextLine();
        Stack<Character> stack = new Stack<>();
        System.out.println("Given expression : " + str);
        System.out.println("Evaluating expression from infix to postfix...");
        String evlStr = "";

        for (int i = 0; i < str.length(); i++) {

            char element = str.charAt(i);

            // if wrong expression is entered
            if (evlStr.equalsIgnoreCase("") && (element == '+' || element == '-' || element == '/' || element == '*' || element == '^')) {
                System.out.println("Wrong expression input (" + str + ")");
                evlStr += -1;
                break;
            }

            // if element is bracket
            if (element == '(') {
                stack.push(element);
                continue;
            }
            if (element == ')') {
                while(!stack.isEmpty()) {
                    if (stack.peek() == '(') {
                        stack.pop();
                        break;
                    }
                    evlStr += stack.pop();
                }
                continue;
            }

            // if element is operator
            if (element == '+' || element == '-' || element == '/' || element == '*' || element == '^') {
                if (stack.isEmpty()) {
                    stack.push(element);
                } else {
                    char anotherElement = stack.peek();
                    switch(element) {
                        case '+' : case '-' :
                            while(anotherElement == '+' || anotherElement == '-' || anotherElement == '*' || anotherElement == '/' || anotherElement == '^') {
                                evlStr += stack.pop();
                                if (stack.isEmpty()) {
                                    break;
                                }
                                anotherElement = stack.peek();
                            }
                            stack.push(element);
                            break;
                        case '/' : case '*' :
                            while (anotherElement == '*' || anotherElement == '/' || anotherElement == '^') {
                                evlStr += stack.pop();
                                if (stack.isEmpty()) {
                                    break;
                                }
                                anotherElement = stack.peek();
                            }
                            stack.push(element);
                            break;
                        case '^' :
                            stack.push(element);
                            break;
                    }
                }
            } else {
                evlStr += element; // element is other than operators and bracket.
            }

            if (i == str.length()-1) {
                while(!stack.isEmpty()) {
                    if (stack.peek() == '(' || stack.peek() == ')') {
                        stack.pop();
                        continue;
                    }
                    evlStr += stack.pop();
                }
                break;
            }

        }

        System.out.println("Evaluated expression : " + evlStr);

    }

}
