import java.util.TreeMap;

/**
 *                       root
 *               c/       d|        \p
 *               c         d         p
 *              a|      o/   \e      |a
 *               a      o     e      a
 *              t|     g|     |e     |n
 *               t      g     e      n
 *                            |r     |d
 *                            r      d
 *                                   |a
 *                                   a
 * 
 * 每个节点又若干指向下个节点的指针
 * 考虑不同的语言，不同的情景，有多少个节点
 * 
 * class Node {
 *     //此处设置isWord是因为，在panda中，pan也是个单词，如果想要既存储pan又存储panda，
 *     //那就需要在n上存储一个布尔值，就是当前节点是否代表一个单词的结尾
 *     boolean isWord;
 *     Map<char, Node> next;
 * }
 * */

public class Trie {
	
	private class Node {
		
		public boolean isWord;
		public TreeMap<Character, Node> next;	//此处仅仅考虑英文字符串，就不使用泛型了
		
		//有参构造
		public Node(boolean isWord) {
			this.isWord = isWord;
			next = new TreeMap<>();
		}
		
		//无参构造
		public Node() {
			this(false);
		}
	}
	
	private Node root;
	private int size;
	
	public Trie() {
		root = new Node();
		size = 0;
	}
	
	//获得Trie中存储的单词数量
	public int getSize() {
		return size;
	}
	
	//向Trie中添加一个新的单词word
	//添加字符串，然后把字符串拆成一个个字符，然后把一个个字符做成一个个节点添加进树结构中
	public void add(String word) {
		
		Node cur = root;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(cur.next.get(c) == null) {	//如果cur.next节点中不存在要插入的节点，那就插进去。多叉树，有多少插多少
				cur.next.put(c, new Node());
			}
			cur = cur.next.get(c);			//如果已经包含了待插入的节点，那就移向下一个节点
		}
		//上述循环结束之后，cur所在的节点代表是一个单词的尾节点，此时要改变isWord的值
		//但是这里要注意，如果之前已经插入了这个单词了，那这时候要先判断isWord的值
		//如果它的false代表没有插入过，如果已经是true，就代表之前已经插入过了
		//实现不重复添加单词
		if(!cur.isWord) {
			cur.isWord = true;
			size++;
		}
	}
	
	//查询单词word是否在Trie中
	public boolean contains(String word) {
		
		Node cur = root;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(cur.next.get(c) == null) {
				return false;			//只要查不到连续的，就返回false
			}
			cur = cur.next.get(c);
		}
		//这时候要注意，比如说树中只存储了panda，但是想要查找pan
		//这时候因为树中没有存储pan，但是char c来到了n这个字母，如果是单纯的renturn true的话，那此时就返回true了
		//但是树中根本没有存储pan这个单词
		//所以正确的返回值应该是cur.isWord，此时n所在的cur.isWord是false，所以返回false
		return cur.isWord;
	}
}
