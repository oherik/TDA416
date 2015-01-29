public class Uppg1 {
	private static double testNumber;
	private static double sensitivity;
	private static double mean;
	private static double squareOfMean;

	public static double binarySqrt(double sqr, double eps)
			throws IllegalArgumentException {
		if (sqr < 1 || eps < 0) {
			throw new IllegalArgumentException();
		} else {									//else behövs egentligen inte här men ökar läsligheten
			return help(sqr, eps, 1, sqr);
		}
	}// binarySqrt

	private static double help(double sqr, double eps, double low, double high) {
		mean = (low + high) / 2;
		squareOfMean = Math.pow(mean, 2);
		if (Math.abs(sqr - squareOfMean) <= eps) {
			return mean;
		} else if (sqr < squareOfMean) {			//det hade räckt med "if", men "else if" ökar läsligheten
			return help(sqr, eps, low, mean);
		} else {									//else behövs egentligen inte här men ökar läsligheten
			return help(sqr, eps, mean, high);
		}
	}// help

	public static void main(String[] args) {
		testNumber = 345678987;
		sensitivity = Math.pow(10, -6);
		try {
			System.out.println("Vår uträknade rot: "
					+ binarySqrt(testNumber, sensitivity));
		} catch (IllegalArgumentException e) {
			System.out
					.println("Talet som du söker efter roten till måste vara större än 1 och epsilon måste vara positivt.");
			System.exit(0);
		}
		System.out.println("Rot enligt Math.sqrt(" + testNumber + "): "
				+ Math.sqrt(testNumber));
	}// main
}
