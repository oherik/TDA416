//Grupp 7 - Erik Öhrn & Paula Eriksson Imable
import java.util.List;

import testSortCol.CollectionWithGet;
import testSortCol.TestMapWithCounter;


public class SLCTest<E extends Comparable<? super E>> extends SortedLinkedCollection<E>{
	
	public SLCTest(){
		super();
	}
	public static void main(String[] args){
		SLCTest<Integer> SLC   = new SLCTest<Integer>();
		SLC.addAndPrint(1);
		SLC.addAndPrint(2);
		SLC.addAndPrint(8);
		SLC.addAndPrint(-2);
		SLC.addAndPrint(2);
		SLC.addAndPrint(100);
		SLC.addAndPrint(5);
	}
	private void addAndPrint(E e){
		add(e);
		System.out.println("Added " + e + " to the list");
		printList(head);
	}
	
	private void printList(Entry t){
		if(t==null){
			System.out.println("\n");
			return;
			}
		else if(t!=head)
			System.out.print(" ---> ");
		System.out.print(t.element);
		printList(t.next);
	}
}
