

// Veasna Bun
// 10/14/2021
// CS143

public class IAList {
	private int[] a; // Underlying array
	private int length; // Number of added elements in a
	private int pointer;// YOU WILL NEED TO ADD AT LEAST ONE NEW DATA FIELD HERE.
	
	public IAList() {
		length = 0; // Start with no added elements in a
		a = new int[4]; // A little room to grow
		pointer = 0;// YOU MAY NEED TO INITIALIZE YOUR NEW DATA FIELD(S).
	}

	public int get(int i) { // Retrieve an added element, O(1)
		// THE NEW DATA FIELD(S) MAY CHANGE THE WAY GET WORKS.
		if (i < 0 || i  >= length) {	
			throw new IndexOutOfBoundsException(i+"");
		}
		return a[i + pointer]; // Retrieve the element at position i
	}

	public int size() { // Number of added elements, O(1)
		// THE NEW DATA FIELD(S) MAY CHANGE THE WAY SIZE WORKS.
		return length; // The number of added elements
	}

	public void set(int i, int x) { // Modify an existing element, O(1)
		// THE NEW DATA FIELD(S) MAY CHANGE THE WAY SET WORKS.
		if(i < 0 || i >= length) {
			throw new IndexOutOfBoundsException(i+"");
		}
		a[i + pointer] = x; // Change the existing element at position i to x
	}

	public void add(int x) { // Add an element to the end, O(n) for n
		// THE NEW DATA FIELD(S) MAY CHANGE THE WAY ADD WORKS.
		if (length >= a.length || pointer + length == a.length) {
			// Create new array of double the length
			int[] b = new int[a.length * 2];
			// Copy the elements of a to the corresponding indexes of b
			for (int i = 0; i < a.length; i++) {
				b[i] = a[i];
			}
			a = b;
		}
		a[length + pointer] = x;
		length = length + 1;
	}
	
	public void addBefore(int x) {
		/* FILL THIS IN!! */
// (START OF TEST THREE: ) DOING THE EXTRA CREDIT 100+
		
		if(length < 16) {
			for (int i = length; i > 0; i--) {
				a[i] = a[i-1];
			}
			a[0] = x;
		}
		if(length >= 16 && pointer == 0) {
			int[] b = new int[a.length * 2];
			for (int i = 0; i < a.length; i++) {
				b[a.length + i] = a[i];
				pointer++;
			}
			a = b;
		}
		if(length >= 16 && pointer != 0) {
			a[pointer - 1] = x;
			pointer--;
		}
		
		length++;	
		
// (END OF TEST THREE:) This runs and get me 100/100 also but I'm sure the arrays Full). 		
		
// (START OF TEST TWO:) This run and give you 100/100 but array NOT full. 	
		
//		if(pointer == 0) {
//			int[] b = new int[a.length * 2];
//			for (int i = 0; i < a.length; i++) {
//				b[a.length + i] = a[i];
//				pointer++;
//			}
//			a = b;
//		}
//		pointer--;
//		a[pointer] = x;
//		length++;	
		
// (END OF TEST TWO) This runs and get me 100/100;		
		
// ( START OF TEST ONE) this code works but take way too slow.

//		if(length >= a.length) {
//			int[] b = new int[a.length * 2];
//			for (int i = 0; i < a.length; i++) {
//				b[i] = a[i];
//				
//			}
//			a = b;
//		}
//		for (int i = length; i > 0; i--) {
//	        a[i] = a[i-1];
//	    }
//		a[0] = x;
//		length++;
		
// (END OF TEST ONE) this code run but take way too slow to run.
	}
}
