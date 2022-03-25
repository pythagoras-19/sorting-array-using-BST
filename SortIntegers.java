public class SortIntegers {
    
    public int[] sortIntergerArray(int[] array, int size){
        System.out.println("sortIntegerArray()");
        System.out.println("Unsorted, as given: ");
        //show what our array looks like originally
        for(int i =0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println("");//for organization in terminal
        int[] sorted; // our sorted array
        sorted = new int[size];

        //our new tree which will help our array get sorted 
        BST t = new BST();

        // insert each element of our array into our our tree t
        for(int i=0; i < size; i++){
            //insert them into the bst you implemented
            //my insert method utilizes a stack data structure
            t.root = t.insert(t.root, array[i]);
        }

        //return the array of integers sorted
        sorted = t.inorderTraversal(t, t.root,size);

        //return the sorted array
        return sorted;
    }
}
