
public class SplayTest {

	public static void main(String[] args){
		SplayTree tree = new SplayTree<Integer>();
		
		for(int i = 0; i<10; i++)
			tree.add(new Integer(i));
		tree.add(new Integer(5));
		tree.add(new Integer(5));
		tree.add(new Integer(5));
		tree.add(new Integer(5));
		tree.add(new Integer(5));
		
		

		System.out.println(tree.get(2));
		System.out.println(tree.get(9));
		System.out.println(tree.get(-1));
		System.out.println(tree.get(2));
		String y = "hej";
		test(y);
		System.out.println(y);
		
	}
	public static void test(String str){
		str = str+"lol";
	}
	
}
