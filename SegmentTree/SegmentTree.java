/**
如果去间有n个元素，数组表示需要有多少个节点？
1. 如果n满足n = 2 ^ k，那就是一棵满二叉树，
此时叶子节点的数量就是n，从第0层到倒数第二层的节点数量等于n - 1，此时需要2n的空间

                           A[0...7]
                        /            \
                     A[0...3]        A[4...7]
                     /     \          /     \
                A[0...1]  A[2...3] A[4...5]  A[6...7]
                /     \    /   \     /   \    /    \
               A[0]  A[1] A[2] A[3] A[4] A[5] A[6] A[7]


2. 如果n = 2 ^ k + 1，那就不是一棵满二叉树而是一棵平衡二叉树，
此时最坏情况是比原来2 ^ k还要多一层，本来就需要2n，现在多了一层，而这一层的节点总数等于2n - 1个（worst case），
所以此时需要4n的空间

                           A[0...9]
                        /            \
                     A[0...4]        A[5...7]
                     /     \          /     \
                A[0...1]  A[2...4] A[5...6]  A[7...9]
                /  \      /   \     /   \     /    \
             A[0] A[1] A[2] A[3,4] A[5] A[6] A[7] A[8,9]
                             /   \                 /   \
                           A[3]  A[4]            A[8]  A[9]
 * 
 * */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){

        this.merger = merger;

        data = (E[])new Object[arr.length];
        for(int i = 0 ; i < arr.length ; i ++)
            data[i] = arr[i];

        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r){

        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index + 2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
