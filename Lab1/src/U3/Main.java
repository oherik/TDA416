package U3;

import java.awt.Color;

// EH 2015 01 22
public class Main {
	
	public static void main(String[] args) {

		DimpLinkedList outline;
		int size = 0; //n numsber of points in shape
		int j = -1; // count arguments
		int w = 15; // width
		int h = 15; // heigth
		int k = 8; // take away to k points
		// tag hand om flaggorna								 
		while  ( j+1 < args.length && args[j+1].charAt(0) == '-' )  {
			
			j = j+1;
			//System.out.println("j= " + j + " " + args[j] );
			switch ( args[j].charAt(1) ) {
				case 'k': {
					try {
						k = Integer.parseInt( args[j].substring(2) );
					}
					catch ( NumberFormatException e ) {
						System.out.println("main: k must have numerical argument");
						System.exit(0);
					}
				}
				break;
				case 'w' : {
					try {
						w = Integer.parseInt( args[j].substring(2) );
					}
					catch ( NumberFormatException e ) {
						System.out.println("main: w must have numerical argument");
						System.exit(0);
					}
				}
				break;
				case 'h' : {
					try {
						h = Integer.parseInt( args[j].substring(2) );
					}
					catch ( NumberFormatException e ) {
						System.out.println("main: h must have numerical argument");
						System.exit(0);
					}
				}
				break;
				default : 
					System.err.println("main: unknown flag: " + args[j]);
					System.exit(0);   // avsluta
			} // end switch
		} // end loop;
		// kolla argument
		
		if (k<2 || w<10 || h<10) {
			System.err.println("main: either of k<2 || w<10 || h<10" );
			System.exit(0);   // avsluta
		}
		
		outline = new DimpLinkedList(w, h);
		
		// read constant points to linked list
		//size = outline.readFakeShape();
		// read from user/file
		
		size = outline.readShape();
		
		//System.out.println("********** original list:");
		//System.out.println(outline.toString());
		System.out.println("********** draws original Shape in black");
		// draw original
		outline.drawShape(Color.BLACK, true);
		// calculate initial "important measure"
		//outline.calcInitialImportance();
		
		//System.out.println("********* after init");
		//System.out.println(outline.toString());
		System.out.println("*********** after remove:");
		outline.importanceRemoveList(k);
		System.out.println(outline);

		try{ // take a breake - oförklarliga timing problems med uppritningen
			outline.wait(1000);
		} catch (Exception e ) {
		}
		System.out.println("********** draws generated Shape in red");
		
		outline.drawShape(Color.RED, false);
		System.out.println();
	}
}
