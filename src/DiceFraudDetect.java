import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DiceFraudDetect {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner keyboard= new Scanner(System.in);
		System.out.print("Enter filename (include file type): ");
		String num;
		String file= keyboard.next();
		int count=0;
		int r1=0;
		int r2=0;
		int r3=0;
		int r4=0;
		int r5=0;
		int r6=0;
		double p1;
		double p2;
		double p3;
		double p4;
		double p5;
		double p6;
		double chi;
		
		Scanner fileScanner= new Scanner(new File(file));
		while(fileScanner.hasNext()) {
			num=fileScanner.nextLine();
			++count;
			if(num.equals("1")) {
				++r1;
			}else if(num.equals("2")) {
				++r2;
			}else if(num.equals("3")) {
				++r3;
			}else if(num.equals("4")) {
				++r4;
			}else if(num.equals("5")) {
				++r5;
			}else if(num.equals("6")) {
				++r6;
			}
			
		}
		
		double denom=count/6;
		
		chi=chisqr(r1, r2, r3, r4, r5, r6, denom);
		
		p1=((double)r1/(double)count)*100;
		p2=((double)r2/(double)count)*100;
		p3=((double)r3/(double)count)*100;
		p4=((double)r4/(double)count)*100;
		p5=((double)r5/(double)count)*100;
		p6=((double)r6/(double)count)*100;
		
		System.out.printf("File: %s\nTotal= %d\nIndividual number counts: [ %d, %d, %d, %d, %d, %d]\nPercentages [%.2f, %.2f, %.2f, %.2f, %.2f, %.2f]", file, count, r1, r2, r3, r4, r5, r6, p1, p2, p3, p4, p5, p6);
		System.out.print("\n\n\nGraph\n");
		graphMaker((int)p1, 1);
		graphMaker((int)p2, 2);
		graphMaker((int)p3, 3);
		graphMaker((int)p4, 4);
		graphMaker((int)p5, 5);
		graphMaker((int)p6, 6);
		System.out.printf("\n\nChi-squared= %.3f\n", chi);
		
		if(chi<11.07) {
			System.out.print("Dice is fair");
		}else {
			System.out.print("Dice is fraudulent");
		}
		
		
		fileScanner.close();
		keyboard.close();

	}
	
	public static void graphMaker (int amt, int roll) {
		System.out.print(roll+": ");
		for(int i=0; i<amt; ++i) {
			System.out.print("[]");
		}
		System.out.print("  "+amt+"\n");
	}
	
	public static double chisqr (int r1, int r2, int r3, int r4, int r5, int r6, double E) {
		double chi=(((Math.pow(((double)r1-E),2))/E)+((Math.pow(((double)r2-E),2))/E)+((Math.pow(((double)r3-E),2))/E)+((Math.pow(((double)r4-E),2))/E)+((Math.pow(((double)r5-E),2))/E)+((Math.pow(((double)r6-E),2))/E));
		return chi;
	}

}

