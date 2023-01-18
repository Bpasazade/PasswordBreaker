import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Hashtable<Integer, String> numbers = new Hashtable<Integer, String>();
    static Trie trie = new Trie();
    static int digitNum = 2;

    public static void fillNumberDict(Hashtable<Integer, String> numbers) {
        numbers.put(0, "zero");
        numbers.put(1, "one");
        numbers.put(2, "two");
        numbers.put(3, "three");
        numbers.put(4, "four");
        numbers.put(5, "five");
        numbers.put(6, "six");
        numbers.put(7, "seven");
        numbers.put(8, "eight");
        numbers.put(9, "nine");
    }
    public static void createTree() {

        for (int i = 0; i < Math.pow(10, digitNum) - 1; i++) {
            String number = Integer.toString(i);
            if (checkIllegal(number)) { // belki patlatabiilir
                insert(trie.root, number);
            }
        }

    }

    public static void insertToTrie(String illegal) {
        insert(trie.root, illegal);
    }

    // Inserts a new illegal password by dividing it into digits then add them as child nodes to root one by one.
    public static void insert(Node root, String password) {

        ArrayList<Integer> digitList = seperateToDigits(password);
        Node temp = root;

        for (Integer integer : digitList) {
            Node childNode = new Node(integer);
            temp.addChildren(childNode);
            temp = childNode;
        }
    }


    // Checks every possible substring from index 0 of a prefix by number of vowels and consonants.
    public static boolean checkVowelConsonant(String password) {
        int vowelNumber = 0, consonantNumber = 0;

        for (char c : password.toCharArray()) {
            //Counter for  vowel and consonant
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowelNumber++;
            } else {
                consonantNumber++;
            }

            if (vowelNumber > consonantNumber) {
                return false;
            }
        }

        return true;
    }

    // Gets the password as an integer list then merges them into a string value to return.
    public static String digitsToString(ArrayList<Integer> digits) {
        String prefix = "";
        for (Integer digit : digits) {
            prefix += numbers.get(digit);
        }
        return prefix;
    }

    // Gets the password and split it into digits as string, then parses them into Integer one by one.
    public static ArrayList<Integer> seperateToDigits(String string) {
        String[] digitsStr;
        digitsStr = string.split("");

        ArrayList<Integer> digitList = new ArrayList<>();

        for (int i = 0; i < digitsStr.length; i++) {
            digitList.add(Integer.parseInt(digitsStr[i]));
        }

        return digitList;
    }

    // Built to use the checkVowelConsonant() function as a shortcut.
    private static boolean checkIllegal(String password) {
        if (!checkVowelConsonant(digitsToString(seperateToDigits(password)))) {
            //System.out.println("Password is illegal!");
            return true;
        }
        return false;
    }

    public static boolean checkOnlyDigits(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) < '0' || password.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    // Checks if a password and its illegal digits are already in trie
    public static boolean checkIsInTrie(String password, ArrayList<String> illegals) {

        for (int i = 0; i < illegals.size(); i++) {
            if (illegals.get(i).equals(password)) {
                return true;
            }
        }

        return false;
    }

    // Check if input password is at mosst 20 character long.
    public static boolean checkLength(String password) {
        return (password.length() <= 20);
    }

    public static void main(String[] args) {

        fillNumberDict(numbers);
        ArrayList<String> illegals = new ArrayList<>();

        createTree();

        String password;

        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter password: ");
        password = scanner.nextLine();


        while (!checkLength(password)) {
            System.out.println("Password must be shorter than 20 digit!");
            System.out.println("Enter password again: ");
            password = scanner.nextLine();
        }

        while (!checkOnlyDigits(password)) {
            System.out.println("Password can contain only numeric (0-9) values!");
            System.out.println("Enter password again: ");
            password = scanner.nextLine();
        }


        while (checkIllegal(password)) {
            illegals = trie.traverseAllTrie(trie.root);
            if (checkIsInTrie(password, illegals)) {
                System.out.println("Password is illegal and in trie.");
            } else {
                System.out.println("Password is illegal but not in trie. Adding to trie...");
                insertToTrie(password);
            }
            System.out.println("Enter password again: ");
            password = scanner.nextLine();
        }


        LocalTime from = LocalTime.now();
        long millis;

        int ctrl = 0;
        int passwordInt = Integer.parseInt(password);
        for (int i = 1; i <= password.length(); i++) {
            for (int j = (int) Math.pow(10, i - 1); j < Math.pow(10, i); j++) {
                if (checkIsInTrie(j + "", illegals)) ;
                else {
                    ctrl++;
                    if (j == passwordInt) {
                        System.out.println("Your password is: " + j);
                        LocalTime to = LocalTime.now();
                        Duration duration = Duration.between(from, to);
                        millis = duration.toMillis();
                        System.out.println("Password is broken in " + ctrl + " iterations and in " + millis + " milliseconds.");
                    }
                }
            }
        }


    }

}


