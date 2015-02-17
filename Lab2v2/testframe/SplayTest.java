
public class SplayTest {

	public static void main(String[] args){
		SplayTree tree = new SplayTree<Integer>();
		
		 //L�gg in massa tal
		for(int i = -300; i<1000; i++)
			tree.add(new Integer(i));
		for(int i = 0; i<400; i++)
			tree.add(new Integer(i));
		for(int i = 60; i<280; i++)
			tree.add(new Integer(i));
		
		System.out.println("Rot: " + tree.getRootElement());
		
		System.out.println("H�mtar 2, f�rn" + tree.get(2));
		
		System.out.println("Ny rot: " + tree.getRootElement());
		
		
		System.out.println("H�mtar 9, f�r " + tree.get(9));
		System.out.println("Ny rot: " + tree.getRootElement());
		System.out.println("H�mtar -1000, f�r " + tree.get(-1000));
		System.out.println("Ny rot: " + tree.getRootElement());
		System.out.println("H�mtar 2, f�r " + tree.get(2));
		System.out.println("Ny rot: " + tree.getRootElement());

		
	}

	
}
