import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Trie {

    Node root;

    public Trie() {
        root = new Node(-1);
        root.setParent(null);
    }


    ArrayList<String> traverseAllTrie(Node root) {
        Stack<Node> numbers = new Stack<>();
        String str = "";
        ArrayList<String> arrayList = new ArrayList<>();

        numbers.push(root);

        while (!numbers.isEmpty()) {
            Node temp = numbers.pop();

            if (temp != null) {
                if (temp.value != -1)
                    str += temp.value;

                for (int i = temp.childrenList.size() - 1; i >= 0; i--) {
                    numbers.add(temp.childrenList.get(i));
                }

                if (temp.isEnd) {
                    arrayList.add(str);
                    str = "";
                }
            }
        }
        return arrayList;


    }

}
