package com.app.eshiksa.utility;

public class NumberToWordsWithPaisa {

	private static final String[] belowTen = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
			"Nine" };
	private static final String[] belowTwenty = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
			"Sixteen", "Seventeen", "Eighteen", "Nineteen" };
	private static final String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
			"Ninety" };
	private static final String[] thousands = { "", "Thousand", "Million", "Billion" };

	public static void main(String[] args) {
		double number = 100.01; // input number
		String result = convert(number);
		System.out.println(result); // Output the result
	}

	public static String convert(double num) {
		if (num == 0) {
			return "Zero";
		}

		// Split into integer and decimal parts
		long integerPart = (long) num;
		long decimalPart = (long) (((num - integerPart) * 100) + 0.01); // Get decimal part up to 2 digits

		String integerInWords = convertInteger(integerPart);
		String decimalInWords = convertInteger(decimalPart);

		// Return formatted string
		if (decimalPart == 0)
			return integerInWords + " Rupees";
		else
			return integerInWords + " Rupees and " + decimalInWords + " Paise";

	}

	public static String convertHundreds(long num) {
		StringBuilder result = new StringBuilder();
		if (num >= 100) {
			result.append(belowTen[(int) (num / 100)]).append(" Hundred ");
			num %= 100;
		}
		if (num >= 20) {
			result.append(tens[(int) (num / 10)]).append(" ");
			num %= 10;
		}
		if (num >= 10) {
			result.append(belowTwenty[(int) (num - 10)]).append(" ");
		} else if (num > 0) {
			result.append(belowTen[(int) num]).append(" ");
		}
		return result.toString();
	}

	public static String convertInteger(long num) {
		if (num == 0) {
			return "";
		}

		StringBuilder words = new StringBuilder();
		int i = 0;

		while (num > 0) {
			if (num % 1000 != 0) {
				words.insert(0, convertHundreds(num % 1000) + thousands[i] + " ");
			}
			num /= 1000;
			i++;
		}

		return words.toString().trim();
	}

}