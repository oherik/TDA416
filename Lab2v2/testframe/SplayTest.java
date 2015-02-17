
public class SplayTest {

	public static void main(String[] args){
		SplayTree tree = new SplayTree<Integer>();
		
		 //Lägg in massa tal
		for(int i = -300; i<1000; i++)
			tree.add(new Integer(i));
		for(int i = 0; i<400; i++)
			tree.add(new Integer(i));
		for(int i = 60; i<280; i++)
			tree.add(new Integer(i));
		
		System.out.println("Rot: " + tree.getRootElement());
		
		System.out.println("Hämtar 2, fårn" + tree.get(2));
		
		System.out.println("Ny rot: " + tree.getRootElement());
		
		
		System.out.println("Hämtar 9, får " + tree.get(9));
		System.out.println("Ny rot: " + tree.getRootElement());
		System.out.println("Hämtar -1000, får " + tree.get(-1000));
		System.out.println("Ny rot: " + tree.getRootElement());
		System.out.println("Hämtar 2, får " + tree.get(2));
		System.out.println("Ny rot: " + tree.getRootElement());

		
	}

	
}
