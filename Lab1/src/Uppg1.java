//Grupp 7: Erik �hrn, Paula Eriksson Imable
import java.util.Scanner;
public class Uppg1 {
	private static double testNumber;
	private static double sensitivity;
	private static double mean;
	private static double squareOfMean;

	public static double binarySqrt(double sqr, double eps) //Publik d� den ska kunna anropas utanf�r klassen
			throws IllegalArgumentException {
		if (sqr < 1 || eps < 0) {
			throw new IllegalArgumentException();
		} else { // else beh�vs egentligen inte h�r men �kar l�sligheten
			return help(sqr, eps, 1, sqr);
		}
	}// binarySqrt

	private static double help(double sqr, double eps, double low, double high) { //Enbart hj�lpklass, d�rf�r privat
		mean = (low + high) / 2;
		squareOfMean = Math.pow(mean, 2);
		if (Math.abs(sqr - squareOfMean) <= eps) {
			return mean;
		} else if (sqr < squareOfMean) {
			return help(sqr, eps, low, mean);
		} else { // else beh�vs egentligen inte h�r men �kar l�sligheten
			return help(sqr, eps, mean, high);
		}
	}// help

	public static void main(String[] args) { //Testprogram
		Scanner sc = new Scanner(System.in);
		System.out.println("V�nligen skriv in ett tal att ber�kna kvadratroten av:");
		while(!sc.hasNextDouble()){ 
			System.out.println("Ej ett giltigt tal. Var god f�rs�k igen.");
			sc.next();
		}
		testNumber = sc.nextDouble();	//V�lj valfri siffra h�r att ber�kna kvadratroten ur
		sc.close();
		sensitivity = Math.pow(10, -6); //eps s�tts till 10^-6
		try {
			System.out.println("V�r utr�knade rot: "
					+ binarySqrt(testNumber, sensitivity));
		} catch (IllegalArgumentException e) {
			System.out
					.println("Talet som du s�ker efter roten till m�ste vara st�rre �n 1 och epsilon m�ste vara positivt. Avslutar.");
			System.exit(0);
		}
		System.out.println("Rot enligt Math.sqrt(" + testNumber + "): "
				+ Math.sqrt(testNumber));
	}// main
}//Uppg1
