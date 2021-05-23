package algorithms;

import helpers.FuncInterface;
import java.util.List;

public class HeapSort<T> {
    
    public List<T> heapSort(List<T> input, FuncInterface<T> ICompare)
    {
        int size = input.size();

        // build heapSort (rearrange input)
        for (int i = size / 2 - 1; i >= 0; i--)
            heapify(input, size, i, ICompare);

        // one by one extract an element from heapSort
        for (int i = size - 1; i >= 0; i--)
        {
            // move current root to end
            T temp = input.get(0);
            input.set(0, input.get(i));
            input.set(i, temp);

            // call max heapify on the reduced heapSort
            heapify(input, i, 0, ICompare);
        }

        return input;
    }

    // to heapify a subtree rooted with node i which is an index in inpuList<T>
    void heapify(List<T> input, int size, int i, FuncInterface<T> ICompare)
    {
        int max   = i; // initialize max as root
        int left  = 2 * i + 1;
        int right = 2 * i + 2;

        // if left child is larger than root
        if (left < size && ICompare.compare(input.get(left), input.get(max)))
            max = left;

        // if right child is larger than max
        if (right < size && ICompare.compare(input.get(right), input.get(max)))
            max = right;

        // if max is not root
        if (max != i)
        {
            // swap
            T temp = input.get(i);
            input.set(i, input.get(max));
            input.set(max, temp);

            // recursively heapify the affected sub-tree
            heapify(input, size, max, ICompare);
        }
    }
}
