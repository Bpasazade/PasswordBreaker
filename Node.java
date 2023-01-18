import java.util.ArrayList;

public class Node {
    int value;
    ArrayList<Node> childrenList;
    Node parent;
    boolean isEnd;

    public Node(int value) {
        this.value = value;
        this.childrenList = new ArrayList<Node>();
        this.isEnd = true;
    }

    public int isInList(Node node, int num) {
        if (node.childrenList != null) {
            for (int i = 0; i < node.childrenList.size(); i++) {
                if (num == node.childrenList.get(i).value) {
                    return i;
                }
            }
        }
        return -1;
    }


    public void addChildren(Node childNode) {
        // If not in list
        // If (isInList(this, childNode.getValue()) == -1) {
        childrenList.add(childNode);
        childNode.parent = this;
        //}

        this.isEnd = false;

    }

    public void setParent(Node parent) {
        this.parent = parent;
    }


}
