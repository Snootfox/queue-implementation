package queue;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
	
	static Scanner input = new Scanner(System.in);

	// Queue implementation
	static Queue<String> queue = new LinkedList<>();
	
	public static void main(String[] args) {
		int choice;
		
		do {
			System.out.println("\n===== PERMUTATION AND COMBINATION =====");
            System.out.println("[1] Add Elements to Queue");
            System.out.println("[2] Display Queue");
            System.out.println("[3] Generate Permutations");
            System.out.println("[4] Generate Combinations");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();
            
            switch (choice) {
            case 1:
            	addElements();
            	break;
            case 2:
            	displayQueue();
            	break;
            case 3:
            	generatePermutations();
            	break;
            case 4:
            	generateCombinations();
            	break;
            case 5:
            	System.out.println("Program Terminated");
            	break;
            default:
            	System.out.println("Invalid Choice!");
            }
		} while (choice != 5);
	}
	// TODO 1: Accept elements and add them to the Queue
    static void addElements() {
        System.out.print("Enter number of elements to add: ");
        int count = input.nextInt();
        input.nextLine(); // Clear scanner buffer

        for (int i = 0; i < count; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            String element = input.nextLine().trim();
            queue.add(element);
        }
        System.out.println("Elements successfully added.");
    }
    // TODO 2: Display all elements in the Queue
    static void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.println("Queue: " + queue);
    }
    // TODO 3: Generate all possible permutations
    static void generatePermutations() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty! Please add elements first.");
            return;
        }

        System.out.print("Enter number of elements to arrange (r): ");
        int r = input.nextInt();

        if (r <= 0 || r > queue.size()) {
            System.out.println("Invalid arrangement size! (Must be between 1 and " + queue.size() + ")");
            return;
        }

        // Convert current main queue to array for indexed access
        String[] elements = queue.toArray(new String[0]);
        Queue<String> permQueue = new LinkedList<>();

        // Initialize queue with single-element prefixes
        for (String el : elements) {
            permQueue.add(el);
        }

        int totalCount = 0;
        System.out.print("\nPermutations:\n");

        while (!permQueue.isEmpty()) {
            String current = permQueue.poll();

            // Split current string into elements using comma separator
            String[] currentArr = current.split(",");

            if (currentArr.length == r) {
                System.out.print(String.join("", currentArr) + " ");
                totalCount++;
            } else {
                // Append elements that haven't been used yet in the current path
                for (String el : elements) {
                    boolean exists = false;
                    for (String used : currentArr) {
                        if (used.equals(el)) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        permQueue.add(current + "," + el);
                    }
                }
            }
        }

        System.out.println("\nTotal Permutations: " + totalCount);
    }
    // TODO 4: Generate all possible combinations
    static void generateCombinations() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty! Please add elements first.");
            return;
        }

        System.out.print("Enter number of elements to select (r): ");
        int r = input.nextInt();

        if (r <= 0 || r > queue.size()) {
            System.out.println("Invalid selection size! (Must be between 1 and " + queue.size() + ")");
            return;
        }

        String[] elements = queue.toArray(new String[0]);
        Queue<String> combQueue = new LinkedList<>();

        // Queue elements along with their starting index to preserve forward-only selection
        for (int i = 0; i < elements.length; i++) {
            combQueue.add(elements[i] + ":" + i);
        }

        int totalCount = 0;
        System.out.println("\nCombinations:");

        while (!combQueue.isEmpty()) {
            String item = combQueue.poll();
            String[] parts = item.split(":");
            String value = parts[0];
            int lastIndex = Integer.parseInt(parts[1]);

            String[] valArr = value.split(",");

            if (valArr.length == r) {
                System.out.println(String.join("", valArr));
                totalCount++;
            } else {
                // Only select elements that appear strictly after the last selected element index
                for (int nextIdx = lastIndex + 1; nextIdx < elements.length; nextIdx++) {
                    combQueue.add(value + "," + elements[nextIdx] + ":" + nextIdx);
                }
            }
        }

        System.out.println("\nTotal Combinations: " + totalCount);
    }
}
