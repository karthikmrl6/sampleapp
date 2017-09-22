package JMapReducer.JMapReducer;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;



public class SortedList<T> extends LinkedList<T> 
{
	
    public SortedList() 
    { }
    

    public SortedList(Comparator<? super T> comparator) 
    {
        this.comparator = comparator;
    }


    public boolean add(T paramT) 
    {
        int insertionPoint = Collections.binarySearch(this, paramT, comparator);
        super.add((insertionPoint > -1) ? insertionPoint : (-insertionPoint) - 1, paramT);
        return true;
    }

    public boolean addAll(Collection<? extends T> paramCollection) {
        boolean result = false;
        if (paramCollection.size() > 4) {
            result = super.addAll(paramCollection);
            Collections.sort(this, comparator);
        }
        else {
            for (T paramT:paramCollection) {
                result |= add(paramT);
            }
        }
        return result;
    }

    public boolean containsElement(T paramT) 
    {
        return (Collections.binarySearch(this, paramT, comparator) > -1);
    }

    private static final long serialVersionUID = 1L;
    private Comparator<? super T> comparator = null;
}