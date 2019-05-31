
public interface UF {
	
	//不是先对并查集添加元素，只实现对当前的元素进行合并和查询操作
	int getSize();						//返回并查集一共考虑多少个元素
	void unionElements(int p, int q);	//先实现“并”操作
	boolean isConnected(int p, int q);	//再实现“查”操作
}
