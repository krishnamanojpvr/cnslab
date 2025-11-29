// 2.Write a java program that contains a string (char pointer) with a value 'Hello World'. The 
// program should AND or and XOR each character in this string with 127 and displays the result. 
// Program: 

public class p2_BitwiseOperations {

    public static void main(String[] args) {
        String text = "Hello World";
        System.out.println("Original String: " + text);

        for (char c : text.toCharArray()) {
            char andResult = (char) (c & 127);
            System.out.print(andResult);
        }
        System.out.println();

        for (char c : text.toCharArray()) {
            char xorResult = (char) (c ^ 127);
            System.out.print(xorResult);
        }
        System.out.println();
    }
}
