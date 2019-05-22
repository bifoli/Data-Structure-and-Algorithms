/*
 * front == tail 队列为空
 * (tail + 1) % capacity == front 队列满
 * capacity中，浪费一个空间
 * ------------------------------------------------ 
 *  data
 *               front  
 *             	   | 
 *	index:  0   1  2 3 4 5 6 7
 *	        i null c d e f g h     capacity
 *	            |
 *	           tail
 * ------------------------------------------------         
 */
public class LoopQueue<E> implements Queue<E> {
	
	private E[] data;
	private int front, tail;
	private int size;
	
	public LoopQueue(int capacity) {
		data = (E[])new Object[capacity + 1];	//因为会有意识浪费一个空间，所以为了满足用户的期望capacity，这里需要加一
		front = 0;
		tail = 0;
		size = 0;
	}
	
	public LoopQueue() {
		this(10);	//无参构造默认初始capacity为10
	}
	
	public int getCapacity() {
		return data.length - 1;		//因为前面已经加1，所以这里要减1
	}
	
	@Override
	public boolean isEmpty() {
		return front == tail;
	}
	
	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public void enqueue(E e) {
		if((tail + 1) % data.length == front) {
			resize(getCapacity() * 2);		//这里需要使用getCapacity而不是data.length,因为后者比前者多一位，而前者是真实的用户想要的值
		}
		
		data[tail] = e;
		tail = (tail + 1) % data.length;	//原本是要tail++，但因为是在循环队列中，所以要取余
		size++;								//入队之后size++
	}
	
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
		}
		
		E ret = data[front];
		data[front] = null;		//手动回收
		front = (front + 1) % data.length;
		size--;					//出队之后size--
		if(size == getCapacity() / 4 && getCapacity() / 2 != 0) {
			resize(getCapacity() / 2);
		}
		return ret;
	}
	
	@Override
	public E getFront() {
		if(isEmpty()) {
			throw new IllegalArgumentException("Queue is empty.");
		}
		return data[front];
	}
	
	private void resize(int newCapacity) {
		E[] newData = (E[])new Object[newCapacity + 1];	//此处需要加1是同样要人为浪费一个空间
		for(int i = 0; i < size; i++) {
			//把原来data里面的值从front到tail依次赋给newData从0开始
			newData[i] = data[(i + front) % data.length];
		}
		
		data = newData;
		front = 0;
		tail = size;
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(String.format("Queue: size = %d, capacity = %d\n", size, getCapacity()));
		res.append("front [");
		//循环队列中遍历，需要考虑front不在0的位置
		for(int i = front; i != tail; i = (i + 1) % data.length) {
			res.append(data[i]);
			//判断当前元素不是最后一个元素的方法
			if((i + 1) % data.length != tail) {
				res.append(", ");
			}
		}
		res.append("] tail");
		return res.toString();
	}
}
