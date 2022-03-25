/*
You will be writing a sorting algorithm.

1. Insert() all integer keys to tree to then be sorted into an empty BST.
2. Traverse() the BST to retrieve the keys in sorted order (inorder traversal). You
   must use a stack to do this. You can use your stack implementation from the previous 
   assignment but you may need to update the type stored. You may use Java's Stack.


*/
import java.util.*; // used for the stacks API only. This was explicitly stated in instructions.
public class BST{

    public Node root;
    
    //constructor to construct an empty tree
    public BST(){
        root = null;
    }
    //Node class
    public class Node{
        public int data;
        public Node left,right;
        //constructor
        public Node(int key){
            data = key;
            left = null;
            right = null;
        }
    }

    public int getRootData(){
        //int data = root.data;
        return root.data;
    }
    public Node getLeftSubtree(Node root){
        //return root.left;
        System.out.println("getting left subtree:");
        System.out.println("root.left: " + root.left.data);
        if(root.left == null || isEmpty(root) == true){
            System.out.println("No left subtree");
            return root;
        }
        Stack<Node> stack = new Stack<Node>();
        Node node = new Node(root.left.data);
        node = root.left;
        //while there is still a node and the stack is not empty
        //push on the stack and pop as needed
        System.out.print("The keys in the left subtree are: ");
        int i = 0; //for our array index
        while(node != null || !stack.isEmpty()){
            //go all the way left
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            //pop least elem from stack
            node = stack.pop();
            System.out.print(node.data + " ");
            //array[i] = node.data;
            //i++;
            //look for right subtree and push on stack
            node = node.right;
        }
        System.out.println("");
        System.out.println("node returned from getLeftSubtree is: " + root.left.data);
        return root.left;
    }
    public Node getRightSubtree(Node root){
        //return root.right;
        System.out.println("getting right subtree:");
        System.out.println("root.right: " + root.right.data);
        if(root.right == null || isEmpty(root) == true){
            System.out.println("No right subtree");
            return root;
        }
        Stack<Node> stack = new Stack<Node>();
        Node node = new Node(root.right.data);
        node = root.right;
        //while there is still a node and the stack is not empty
        //push on the stack and pop as needed
        System.out.print("The keys in the right subtree are: ");
        int i = 0; //for our array index
        while(node != null || !stack.isEmpty()){
            //go all the way left
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            //pop least elem from stack
            node = stack.pop();
            System.out.print(node.data + " ");
            //array[i] = node.data;
            //i++;
            //look for right subtree and push on stack
            node = node.right;
        }
        System.out.println("");
        System.out.println("node returned from getRightSubtree() is: " + root.right.data);
        return root.right;
    }
    public boolean isEmpty(Node root){
        if(root == null) return true; 
        return false;
    }
    //to test if the tree contains a given value
    public boolean contains(BST T, Node root, int val){
        System.out.println("in contains()");
        boolean bool = false;
        if(root == null) return false; // empty tree!
        if (val == root.data) return true; // found immediately at root, return true


        Stack<Node> stack = new Stack<Node>();
        Node node = new Node(val);
        stack.push(node);


        Node temp = root;
        while(temp != null){
            System.out.println("temp.data is " + temp.data);
            if (temp.data == stack.peek().data){
                stack.pop();
                return true;
            }
            if (val < temp.data){
                temp = temp.left;
            }
            else if (val > temp.data){
                temp = temp.right;
            }
        }
        return false;
        /* 
        //recursive good
        //searching
        if(val < root.data){
            //bool = T.contains(T, T.getLeftSubtree(), val);
            bool = T.contains(T, root.left, val);
        }
        if(val > root.data){
            //bool = T.contains(T, T.getRightSubtree(), val);
            bool = T.contains(T, root.right, val);
        }
        */
        //return bool;
    }
    
    //insert
    //must support duplicate values
    //all duplicates will become their twin's right child ONLY
    public Node insert(Node root, int val){
        System.out.println("Inserting : " + val);
        Node insert = new Node(val);
        if(root == null){
           // root = new Node(val);
            //return root;
            return insert;
        }
        Node temp = root;
        Stack<Node> stack = new Stack<Node>();
        
        //stack implementation 
        Node k = null;
        stack.push(insert);
  
        //find insertion position
        while(temp != null) {
            k = temp; // hold the last node we reach that is not null
            if(val < temp.data) {
                temp = temp.left;
                System.out.println("temp is now temp.left");
            }
            else if (val >= temp.data) {
                temp = temp.right;
                System.out.println("temp is now temp.right");
            }
            else {
                ; // do nothing
            }
        }
        // now insert into either left or right node
        if (val < k.data){
            //System.out.println("temp is : " + temp.data);
            k.left = stack.pop();
        }
        else {
            // we will take duplicates on RHS of tree
            k.right = stack.pop();
        }

        /*
        //good recursive
        if(val < root.data){
            root.left = insert(root.left, val);
        }
        */
        //duplicates will be inserted on the RHS of its twin.
        /*
        //good recursive
        else if(val >= root.data){ 
            root.right = insert(root.right, val);
        }
        */
        return root;
    }

    // inorder traversal to retrieve elements in sorted order
    public int[] inorderTraversal(BST T, Node root, int size){
        System.out.println("In inorderTraversal()");
        int[] array = new int[size];
        //in the assignment instructions it says we CAN USE the Java API libraries for Stack
        Stack<Node> stack = new Stack<Node>();

        //deal with the root
        if(root == null) return array; // its an empty tree so lets return
        //else stack.push(root); // else lets push the root data on the stack

        //now we will loop through tree first pushing all the left subtree elems
        //on stack
        Node node = new Node(root.data);
        node = root;
        //while there is still a node and the stack is not empty
        //push on the stack and pop as needed
        int i = 0; //for our array index
        while(node != null || !stack.isEmpty()){
            //go all the way left
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            //pop least elem from stack
            node = stack.pop();
            System.out.print(node.data + " ");
            array[i] = node.data;
            i++;
            //look for right subtree and push on stack
            node = node.right;
        }
        System.out.println("");//just for organization in the terminal
        return array;
    }
    public static void main(String[] args){
        System.out.println("Test");
        //create a new tree
        BST tree = new BST();

        tree.root = tree.insert(tree.root, 15);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        //System.out.println("tree root is :" + tree.root.data);
        
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 12);
        tree.root = tree.insert(tree.root, 3); // 18
        tree.root = tree.insert(tree.root, 25);
        tree.root = tree.insert(tree.root, 20);
    
        // testing insert
        System.out.println("tree root is "  + tree.root.data);
        tree.inorderTraversal(tree, tree.root, 8);

        //testing get left subtree
        System.out.println("Testing getLeftSubtree()");
        System.out.println("root is: " + tree.root.data);
        Node left = tree.getLeftSubtree(tree.root);
        System.out.println("Left is: " + left.data);

        //testing get right subtree
        System.out.println("Testing getRightSubtree()");
        System.out.println("root is: " + tree.root.data);
        Node right = tree.getRightSubtree(tree.root);
        System.out.println("Right is: " + right.data);

        //testing contains
        boolean bool = tree.contains(tree, tree.root, 1232);
        System.out.println("1232 is in tree: " + bool);
        bool = tree.contains(tree, tree.root, 1);
        System.out.println("1 is in tree: " + bool);
        bool = tree.contains(tree, tree.root, 15);
        System.out.println("15 is in tree: " + bool);

        //testing sortIntegerArray in SortIntegers java file
        int array[] = { 50,15,10,20,5,12,3,25,20,1};
        System.out.println("Calling sortIntegerArray()");
        SortIntegers sortObj = new SortIntegers();
        array = sortObj.sortIntergerArray(array, array.length);

        System.out.println("printed again for good measure:");
        for(int i=0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
       
    }
}
      /*
        while(node.left != null){
            node = node.left;
            //stack.push(node.data);
            while(node.right != null){
                node = node.right;
                stack.push(node.data);
            }
        }
        */
        /*
        //then pop those values and crawl UP checking for right children
        while(!stack.isEmpty()){
            //while stack is not not empty
            stack.pop();

        }
         // inorder traversal to retrieve elements in sorted order
    public void inorderTraversal(BST T, Node root){
        System.out.println("In inorderTraversal()");
        //in the assignment instructions it says we CAN USE the Java API libraries for Stack
        Stack<Node> stack = new Stack<Node>();

        //deal with the root
        if(root == null) return; // its an empty tree so lets return
        //else stack.push(root); // else lets push the root data on the stack

        //now we will loop through tree first pushing all the left subtree elems
        //on stack
        Node node = new Node(root.data);
        node = root;
        //while there is still a node and the stack is not empty
        //push on the stack and pop as needed
        while(node != null || !stack.isEmpty()){
            //go all the way left
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            //pop least elem from stack
            node = stack.pop();
            System.out.print(node.data + " ");
            //look for right subtree and push on stack
            node = node.right;
        }
    }


     //bool = tree.contains(tree, tree.root, 20);
        //System.out.println("20 is in tree: " + bool);
        /*
        
        */
        /*
        //testing get right subtree
        System.out.println("Testing getrightsubtree");
        int right = tree.getRightSubtree().data;
        System.out.println("Right is: " + right);


        inIt = tree.contains(tree, tree.root, 47);
        System.out.println("in tree: " + inIt);
        
        //System.out.println("yo");
        System.out.println("tree root is "  + tree.root.data);
        //tree.inorderTraversal(tree, tree.root);
        System.out.println("Doing array stuff");
        int array[] = { 50,15,10,20,5,12,3,25,20,1};
        System.out.println("Calling sortIntegerArray()");
        SortIntegers sortObj = new SortIntegers();
        array = sortObj.sortIntergerArray(array, array.length);
        //array = tree.inorderTraversal(tree, tree.root, 7);
        System.out.println("here");

        for(int i=0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        */