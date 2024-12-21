package quizapp;

import java.util.Scanner;

public class quiz {
	public static void main(String[] args) {
		participant p=new participant();
		System.out.println("welcome to the quiz");
		System.out.println("Before we begin, let’s take a moment to get to know you better!");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your name: ");
	    p.setName(sc.next());
		System.out.println("Enter your Age: ");
		p.setAge(sc.nextInt());
		System.out.println("Enter your gender(male/female): ");
		p.setGender(sc.next());
		System.out.println("Enter your address: ");
		p.setAddress(sc.next());
		System.out.println("Enter your phone number: ");
		p.setPhoneNumber(sc.nextLong());
	    System.out.println("Welcome, " + p.getName() + "!");
		System.out.println("Are you ready to play the quiz (yes/no)? ");
		String str=sc.next();
		if (str.equals("No")){
			System.exit(0);
		}	
		p.displayRules();
		p.startQuiz();
		}
}
