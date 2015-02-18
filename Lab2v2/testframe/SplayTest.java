//Grupp 7 - Erik Öhrn & Paula Eriksson Imable
public class SplayTest<E extends Comparable<? super E>> extends SplayTree<E> {

	public SplayTest() {
		super();
	}

	public static void main(String[] args) {
		SplayTest<Integer> ST = new SplayTest<Integer>();
		ST.add(0);
		ST.add(2);
		ST.add(-3);
		ST.add(3);
		ST.add(-2);
		ST.add(-4);
		ST.add(1);
		ST.add(-1);
		System.out.println("Original tree layout\n");
		ST.printTree(ST.root, 1);
		System.out.println("\nZig:");
		ST.getAndPrint(ST.root.left.element);	
		System.out.println("\nZag:");
		ST.getAndPrint(ST.root.right.element);
		System.out.println("\nZigZag:");
		
		ST.getAndPrint(ST.root.left.right.element);
		System.out.println("\nZigZig:");
		ST.getAndPrint(ST.root.left.left.element);
		System.out.println("\nZagZag:");
		ST.getAndPrint(ST.root.right.right.element);
		System.out.println("\nZagZig:");
		ST.getAndPrint(ST.root.right.left.element);

		System.out.println("\nGetting a number not in tree, small");
		ST.getAndPrint(-6);
		System.out.println("\nGetting a number not in tree, large");
		ST.getAndPrint(10);

	}

	private void getAndPrint(E e) {
		System.out.println("Trying to find " + e + " in the tree.");
		E result = get(e);
		if (result == null)
			System.out.println("Couldn't find " + e + ". Splayed "
					+ root.element + " (last checked entry) to the top.");
		else
			System.out.println(e + " was found! Splayed it to the top.");
		System.out.println("New tree layout: \n");
		printTree(root, 1);
	}

	private void printTree(Entry t, int depth) {
		if (t == null)
			return;
		else if (t == root) {
			printTree(t.right, depth + 1);
			System.out.print("Root ->\t" + t.element + "\n");
			printTree(t.left, depth + 1);
		} 
		else if (depth == 1) {
			printTree(t.right, depth + 1);
			System.out.print("Start ->\t" + t.element + "\n");
			printTree(t.left, depth + 1);
		}else {
			printTree(t.right, depth + 1);
			for (int i = 0; i < depth - 1; i++)
				System.out.print("\t|");
			System.out.print("-----> ");
			System.out.print(t.element + "\n");
			printTree(t.left, depth + 1);
		}

	}

}
