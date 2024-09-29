import java.util.List;
import java.util.ArrayList;

public class ListBinaryTree <E>
{
    protected List<E> list;

    public ListBinaryTree() 
    {
        list = new ArrayList<E>();
    }

    //add data to this Complete Binary Tree
    public void add(E data)
    {
        list.add(data);
    }

    //Add all the elements from the provided list to this Complete Binary Tree
    public void addAll(List<E> data)
    {
        for(E x: data)
            add(x);
    }

    //Return the size of this tree
    public int size()
    {
        return list.size();
    }

    public String preorder()
    {
        String x = preorderHelper(0, "");
        return "[" + x.substring(2, x.length()) + "]";
    }

    private String preorderHelper(int index, String ret)
    {
        if(getValueAt(index) == null || ((Integer)(getValueAt(index)) == -1))
            return ret;
        if(index == -1)
            return ret;
        ret += ", " + getValueAt(index);
        ret = preorderHelper(getLeftIndex(index), ret);
        ret = preorderHelper(getRightIndex(index), ret);
        return ret;
    }

    public String inorder()
    {
        String x = inorderHelper(0, "");
        return "[" + x.substring(2, x.length()) + "]";
    }

    private String inorderHelper(int index, String ret)
    {
        if(getValueAt(index) == null || ((Integer)(getValueAt(index)) == -1))
            return ret;
        if(index == -1)
            return ret;
        ret = inorderHelper(getLeftIndex(index), ret);
        ret += ", " + getValueAt(index);
        ret = inorderHelper(getRightIndex(index), ret);
        return ret;
    }

    public String postorder()
    {   
        String x = postorderHelper(0, "");
        return "[" + x.substring(2, x.length()) + "]";
    }

    private String postorderHelper(int index, String ret)
    {
        if(getValueAt(index) == null || ((Integer)(getValueAt(index)) == -1))
            return ret;
        if(index == -1)
            return ret;
        ret = postorderHelper(getLeftIndex(index), ret);
        ret = postorderHelper(getRightIndex(index), ret);
        ret += ", " + getValueAt(index);
        return ret;
    }

    //return the value stored at the specified index
    //or null if the index is out of bounds
    public E getValueAt(int index)
    {
        if(index < list.size() && index >= 0)
            return list.get(index);
        return null;
    }

    //return the index of the parent of the node located at the specified index
    //if index is out of bounds, return -1
    //if the node at the specified index does not have a parent, return -1
    public int getParentIndex(int index)
    {
        int parent = (int)Math.floor((index-1)/2);
        if(index == 0)
            return -1;
        if((getValueAt(parent) != null))
            return parent;       
        return -1;
    }

    //return the index of the left child of the node at the specified index
    //if there is no left child, return -1
    public int getLeftIndex(int parentIndex)
    {
        if(getValueAt(parentIndex * 2 + 1) != null &&(parentIndex < list.size() && parentIndex >= 0))
            return parentIndex * 2 + 1;
        return -1;
    }

    //return the index of the right child of the node at the specified index
    //if there is no right child, return -1
    public int getRightIndex(int parentIndex)
    {
        if(getValueAt(parentIndex * 2 + 2) != null &&(parentIndex < list.size() && parentIndex >= 0))
            return parentIndex * 2 + 2;
        return -1;
    }

    //a useful utility function to swap two nodes in the tree
    //swap the values located at indexA and indexB
    public void swap(int indexA, int indexB)
    {
        E temp = getValueAt(indexA);
        list.set(indexA, getValueAt(indexB));
        list.set(indexB, temp);
    }
    
    protected E removeLast()
    {
        return list.remove(size()-1);
    }

}