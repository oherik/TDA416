//Grupp 7 - Erik Öhrn & Paula Eriksson Imable
public class SplayTest<E extends Comparable<? super E>> extends SplayTree<E> {

	public SplayTest() {
		super();
	}

	public static void main(String[] args) {
		SplayTest<Integer> ST = new SplayTest<Integer>();
		ST.add(1);
		ST.add(3);
		ST.add(4);
		ST.add(2);
		ST.add(0);
		ST.add(-2);
		ST.add(-5);
		ST.add(2);
		ST.add(6);
		System.out.println("Original tree layout\n");
		ST.printTree(ST.root, 1);
		ST.getAndPrint(6);
		ST.getAndPrint(0);
		ST.getAndPrint(-6);
		ST.getAndPrint(2);
		ST.getAndPrint(10);
		ST.getAndPrint(1);

	}

	private void getAndPrint(E e) {
		System.out.println("\nTrying to find " + e + " in the tree.");
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
		} else {
			printTree(t.right, depth + 1);
			for (int i = 0; i < depth - 1; i++)
				System.out.print("\t|");
			System.out.print("-----> ");
			System.out.print(t.element + "\n");
			printTree(t.left, depth + 1);
		}

	}

}
