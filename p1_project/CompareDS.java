/*
 * Aiden Tepper
 * ajtepper@wisc.edu
 * Lecture 001
 */

public class CompareDS {
	
	public static void main(String[] args) {
		System.out.println("Comparing the work time for DS_My and DS_Mark");
		System.out.println("An increasing number of items N will be inserted, then every other item will be deleted\n");
		for(int n = 10; n <= 100000; n = n*10) {
			System.out.println("DS_My is doing work for " + n + " values");
			System.out.println("It took " + testDS_My(n) + " ns");
			System.out.println("DS_Mark is doing work for " + n + " values");
			System.out.println("It took " + testDS_Mark(n) + " ns\n");
		}
	}
	
	public static long testDS_My(int n) {
		DS_My my = new DS_My();
		long myStartTime = System.nanoTime();
		for(int i = 0; i < n; i++) {
			my.insert("" + i, "value: " + i);
		}
		for(int i = 0; i < n; i+=2) {
			my.remove("" + i);
		}
		long myEndTime = System.nanoTime();
		return myEndTime - myStartTime;		
	}
	
	public static long testDS_Mark(int n) {
		DS_Mark mark = new DS_Mark();
		long markStartTime = System.nanoTime();
		for(int i = 0; i < n; i++) {
			mark.insert("" + i, "value: " + i);
		}
		for(int i = 0; i < n; i+=2) {
			mark.remove("" + i);
		}
		long markEndTime = System.nanoTime();
		return markEndTime - markStartTime;
	}

}
