
public class Array<E> {
	
	private E[] data;		//创建只承载int数据的数组
	private int size;		//size是在data数组中有多少个有效元素
	
	//有参构造函数，传入数组的容量capacity构造Array
	public Array(int capacity) {
		data = (E[])new Object[capacity];
		size = 0;
	}
	
	//无参构造，当用户不知道传入多大capacity的时候，此时默认为10
	public Array() {
		this(10);
	}
	
	//获取数组中的元素个数
	public int getSize() {
		return size;
	}
	
	//获取数组的容量
	public int getCapacity() {
		return data.length;
	}
	
	//返回数组是否为空
	public boolean isEmpty() {
		return size == 0;
	}
	
	//向数组中所有元素之后添加一个新元素
	public void addLast(E e) {
		add(size, e);
	}
	
	//向数组中所有元素前插入一个新元素
	public void addFirst(E e) {
		add(0, e);
	}
	
	//在第index个位置插入一个新元素e
	public void add(int index, E e) {
		
		if(index < 0 || index > size) {	//再判断index是否有效
			throw new IllegalArgumentException("AddLast failed. Require index >= 0 and index <= size.");
		}
		
		if(size == data.length) {		//如果满了，就进行扩容操作
			resize(2 * data.length);	//每次扩容一倍
		}
		
		
		for(int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];		//将index之后的元素都往后移一位
		}
		data[index] = e;
		size++;							//执行插入程序之后一定要size++
	}
	
	//获取index索引位置的元素
	public E get(int index) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("Get failed. Index is illegal"); 
		}
		return data[index];
	}
	
	//修改index索引位置的元素为e
	public void set(int index, E e) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("Set failed. Index is illegal"); 
		}
		data[index] = e;
	}
	
	//查找数组中是否有元素e
	public boolean contains(E e) {
		for(int i = 0; i < size; i++) {
			if(data[i].equals(e)) {	//这里要改为equals
				return true;
			}
		}
		return false;
	}
	
	//查找数组中元素e所在的索引，如果不存在元素e，则返回-1
	public int find(E e) {
		for(int i = 0; i < size; i++) {
			if(data[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}
	
	//从数组中删除index位置的元素，返回删除的元素
	public E remove(int index) {
		if(index < 0 || index >= size) {
			throw new IllegalArgumentException("Remove failed. Index is illegal"); 
		}
		
		E ret = data[index];
		for(int i = index + 1; i < size; i++) {
			data[i - 1] = data[i];
		}
		size--;				//删除之后一定记得size--
		data[size] = null;	//因为这里的size的索引其实还指向一个引用值，所以可以手动将它释放掉
		
		//此处的小bug在于，data.length可能等于1，这样的话data.length / 2等于0，缩容不可能缩成0
		if(size == data.length / 4 && data.length / 2 != 0) {
			resize(data.length / 2);	//如果数组中有效元素的个数是数组长度的一半以下，那就进行缩容操作
		}
		return ret;
	}
	
	//从数组中删除第一个元素，返回删除的元素
	public E removeFirst() {
		return remove(0);
	}
	
	//从数组中删除最后一个元素，返回删除的元素
	public E removeLast() {
		return remove(size - 1);
	}
	
	//从数组中删除元素e，此时默认用户知道要删除的元素是什么，所以不需要设置返回值
	public void removeElement(E e) {
		int index = find(e);
		if(index != -1) {
			remove(index);
		}
	}
	
	public void swap(int i, int j) {
		if(i < 0 || i >= size || j < 0 || j >= size) {
			throw new IllegalArgumentException("Index is illegal.");
		}
		
		E t = data[i];
		data[i] = data[j];
		data[j] = t;
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
		res.append("[");
		for(int i = 0; i < size; i++) {
			res.append(data[i]);
			if(i != size - 1) {
				res.append(", ");
			}
		}
		res.append("]");
		return res.toString();
	}
	
	private void resize(int newCapacity) {
		E[] newData = (E[])new Object[newCapacity];
		for(int i = 0; i < size; i++) {
			newData[i] = data[i];	//把原来data里面的元素照搬到newData里面
		}
		data = newData;				//把newData指向data
	}
}
