import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ShoppingListApp {

	public static void main(String[] args) {
		// Bonus Lab 20
		Scanner userInput = new Scanner(System.in);

		Map<String, Double> storeItems = new HashMap<>();
		storeItems.put("apple", 0.99);
		storeItems.put("banana", 0.59);
		storeItems.put("cauliflower", 1.59);
		storeItems.put("dragonfruit", 2.19);
		storeItems.put("Elderberry", 1.79);
		storeItems.put("figs", 2.09);
		storeItems.put("grapefruit", 1.99);
		storeItems.put("honeydew", 3.49);

		List<String> itemName = new ArrayList<>();
		List<Double> itemPrice = new ArrayList<>();

		/***********************************************
		 * Program start after variable/Map creation * *
		 ***********************************************/

		runOrderApp(userInput, storeItems, itemName, itemPrice);

	}

	/**
	 * @param userInput
	 * @param storeItems
	 * @param itemName
	 * @param itemPrice  {@link #createHeader()}
	 *                   {@link #orderProduct(Scanner, Map, List, List)}
	 *                   {@link #showOrder(List, List)}
	 */
	private static void runOrderApp(Scanner userInput, Map<String, Double> storeItems, List<String> itemName,
			List<Double> itemPrice) {
		DecimalFormat df = new DecimalFormat("#.00");

		createHeader();
		orderProduct(userInput, storeItems, itemName, itemPrice);
		showOrder(itemName, itemPrice);

		System.out
				.println("Average price per item in order was " + "$" + df.format(getAverageCost(itemName, itemPrice)));
		System.out.println("The most expensive item was at index " + getExpensiveItem(itemPrice) + " in our list.");
		System.out.println("The least expensive item was at index " + getCheapItem(itemPrice) + " in our list.");
	}

	/**
	 * @param itemPrice
	 * @return returns index of cheapest value stored in Price list
	 */
	private static int getCheapItem(List<Double> itemPrice) {
		int minIndex = 0;
		for (double minNumber : itemPrice) {
			double foundMin = minNumber;
			if (foundMin < itemPrice.get(minIndex)) {
				minIndex = itemPrice.indexOf(minNumber);
			}
		}

		return minIndex;
	}

	/**
	 * @param itemPrice
	 * @return returns index of highest value stored in Price list
	 */
	private static int getExpensiveItem(List<Double> itemPrice) {
		int maxIndex = 0; // create index for for-loop imitation
		for (double maxNumber : itemPrice) {
			double foundMax = maxNumber;
			if (foundMax > itemPrice.get(maxIndex)) {
				maxIndex = itemPrice.indexOf(maxNumber);
			}
		}

		return maxIndex;
	}

	/**
	 * @param itemName
	 * @param itemPrice
	 * @return returns average cost from item cost added to list
	 */
	private static double getAverageCost(List<String> itemName, List<Double> itemPrice) {
		Double sum = 0.0;
		for (int i = 0; i < itemName.size(); i++) {
			sum += itemPrice.get(i);
		}

		return sum / itemPrice.size();
	}

	/**
	 * @param itemName
	 * @param itemPrice
	 */
	private static void showOrder(List<String> itemName, List<Double> itemPrice) {
		System.out.println();
		String format = "%-25s%s%n"; // space purchased items names and price
		System.out.println("Here's what you got:");
		for (int i = 0; i < itemName.size(); i++) {
			System.out.printf(format, itemName.get(i), ("$" + itemPrice.get(i))); // return each item in list item and
																					// list price
		}
	}

	/**
	 * @param userInput
	 * @param storeItems
	 * @param itemName
	 * @param itemPrice
	 */
	private static void getValidMenuChoice(Scanner userInput, Map<String, Double> storeItems, List<String> itemName,
			List<Double> itemPrice) {
		String userChoice;
		boolean isValid;
		System.out.println("Would you like to order anything else (y/n)?");
		do {
			userChoice = userInput.next();
			if (userChoice.equalsIgnoreCase("Y")) {
				orderProduct(userInput, storeItems, itemName, itemPrice); // call order method
				isValid = true;
			} else if (userChoice.equalsIgnoreCase("N")) {
				System.out.println("Thanks for your order!");
				isValid = true;
			} else {
				System.out.println("Sorry, invalid input. Try again");
				isValid = false;
			}
		} while (!isValid);
	}

	/**
	 * @param userInput
	 * @param storeItems
	 * @param itemName
	 * @param itemPrice
	 */
	private static void orderProduct(Scanner userInput, Map<String, Double> storeItems, List<String> itemName,
			List<Double> itemPrice) {
		String userChoice;

		System.out.println("What item would you like to order?");

		userChoice = getValidUserChoice(userInput, storeItems); // validated user input
		itemName.add(userChoice); // add item name to list, has been validated
		itemPrice.add(storeItems.get(userChoice));// add item price to price list, validated by Map key
		System.out.println("Adding " + userChoice + " to cart at $" + storeItems.get(userChoice));
		getValidMenuChoice(userInput, storeItems, itemName, itemPrice); // call menu option method

	}

	/**
	 * @param userInput
	 * @param storeItems
	 * @return user input for store order
	 */
	private static String getValidUserChoice(Scanner userInput, Map<String, Double> storeItems) {
		String userChoice;
		boolean isValid;
		do {
			userChoice = userInput.next();
			if (storeItems.containsKey(userChoice)) { // checks if user input matches any Map keys
				isValid = true;
			} else {
				System.out.println("Sorry, we dont have those. Please try again");
				isValid = false;
			}
		} while (!isValid);

		return userChoice;
	}

	/**
	 * @see general header to create menu list
	 */
	private static void createHeader() {
		// Used parallel arrays to create store menu/display
		String[] items = { "apple", "banana", "cauliflower", "dragonfruit", "Elderberry", "figs", "grapefruit",
				"honeydew" };
		String[] price = { "$0.99", "$0.59", "$1.59", "$2.19", "$1.79", "$2.09", "$1.99", "$3.49" };

		String format = "%-25s%s%n"; // spacing used between item name and price

		System.out.println("Welcome to Buenther's Market!\n\n");
		System.out.println("Item" + "\t\t\t" + "Price");
		System.out.println("==============================");

		for (int i = 0; i < items.length; i++) {
			System.out.printf(format, items[i], price[i]);
		}
		System.out.println();
	}

}
