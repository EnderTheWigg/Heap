public class Heap<E extends Comparable> extends ListBinaryTree<E>
{
    //Add data to this heap
    //Add data exactly like a ListBinaryTree, then propigate that value up the tree 
    //(using the addHelper method)
    @Override
    public void add(E data)
    {
        super.add(data);
        addHelper(list.size()-1);
    }
    
    //Recursive helper method for add
    //Recusively swap the value at index and its parent while val is less than its parent
    private void addHelper(int index)
    {
        if(((getValueAt(index)  != null && getValueAt(getParentIndex(index)) != null) && (Integer)getValueAt(index) < (Integer)getValueAt(getParentIndex(index)))){
            swap(index, getParentIndex(index));
            addHelper(index);
        }
    }
    
    //returns true if the value at index is less than both of its children
    public boolean meetsHeapProperty(int index)
    {
        if(getValueAt(getLeftIndex(index)) == null && getValueAt(getRightIndex(index)) == null)
            return true;
        if((Integer)getValueAt(index) > (Integer)getValueAt(getLeftIndex(index)) || (Integer)getValueAt(index) > (Integer)getValueAt(getRightIndex(index)))return false;
        return true;
    }
    
    //Helper method
    //Returns the index of the child of the specified node with the smallest value
    private int getSmallestChildIndex(int index)
    {
        if(getValueAt(getLeftIndex(index)) == null && getValueAt(getRightIndex(index)) == null)
            return -1;
        else if(getValueAt(getLeftIndex(index)) == null && getValueAt(getRightIndex(index)) != null)
            return getRightIndex(index);
        else if(getValueAt(getLeftIndex(index)) != null && getValueAt(getRightIndex(index)) == null)
            return getLeftIndex(index);
        return (Integer)getValueAt(getLeftIndex(index)) < (Integer)getValueAt(getRightIndex(index))? getLeftIndex(index): getRightIndex(index);
    }
    
    
    //remove and return the value at the root of this heap
    //then reconstitute its heapness using the remove algorithm
    public E removeRoot()
    {
        if(super.size() == 0)
            return null;
        E x = getValueAt(0);
        swap(0, size()-1);
        removeLast();
        int y = 0;
        while(!meetsHeapProperty(y)){
            int z = y;
            y = getSmallestChildIndex(y);
            swap(z, getSmallestChildIndex(z));
        }
        return getValueAt(0);
    }
    
    public void heapify()
    {
        for(int i = list.size()-1; i >= 0; i--)
            sink(i);
    }
 
    //recursive helper method for heapify. 
    //This method "sinks" the value at index until it meets the heap property
    private void sink(int index)
    {
        if(!meetsHeapProperty(index)){
            int x = getSmallestChildIndex(index);
            swap(index, getSmallestChildIndex(index));
            sink(x);
        }
            
    }
    
    //do not edit this method!
    public void shuffle()
    {
        for(int i=0; i<size(); i++)
            swap(i, (int)(Math.random() * size()));
    }
}