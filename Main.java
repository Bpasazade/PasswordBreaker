import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Hashtable<Integer, String> numbers = new Hashtable<Integer, String>();
    static Trie trie = new Trie();

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
        ArrayList<Integer> numberList = new ArrayList<>();

        try {
            File file = new File("numbers.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String number = reader.nextLine();
                if(!checkVowelConsonant(digitsToString(seperateToDigits(number)))) {
                    insert(trie.root, number);
                    //System.out.println(number + " is added.");
                    //illegal
                } else {
                    //System.out.println(number + " is illegal değil");
                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

    }
    public static boolean checkOnlyDigits(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) < '0' || password.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

}
