package quizapp;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class participant {
    private String name;
    private int age;
    private String gender;
    private long phoneNumber;
    private String address;
    private int score = 0;
    private boolean isFiftyFiftyUsed = false;
    private boolean isAudiencePollUsed = false;
 
	public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void displayRules() {
        System.out.println("-Before we start, let’s go through the rules!- ");
        System.out.println("1. Each question has six options: A, B, C, D, E (Lifeline), and F (Quit).");
        System.out.println("2. Correct answers earn 10 points. Incorrect answers end the game.");
        System.out.println("3. Lifelines:");
        System.out.println("   - 50-50: Removes two incorrect options.");
        System.out.println("   - Audience Poll: Shows voting percentages.");
        System.out.println("5. Each lifeline can only be used once.");
        System.out.println("6-Your goal is to answer all the questions correctly and score the highest points.");
        System.out.println("- Good luck and have fun!-");
    }	
    public void useFiftyFifty(String[] options, char correctAnswer) {
        if (isFiftyFiftyUsed) {
            System.out.println("The 50-50 lifeline is no longer available. Try another option to move forward.");
            return;
        }

        Random random = new Random();
        int correctIndex = correctAnswer - 'A';
        boolean[] displayed = new boolean[options.length];
        displayed[correctIndex] = true; 
        int removed = 0;

        while (removed < 1) { 
            int randomIndex = random.nextInt(options.length);
            if (randomIndex != correctIndex && !displayed[randomIndex]) {
                displayed[randomIndex] = true;
                removed++;
            }
        }

        System.out.println("The 50-50 lifeline is done. Consider your remaining options carefully.:");
        for (int i = 0; i < options.length; i++) {
            if (displayed[i]) {
                System.out.println("Option " + (char) ('A' + i) + ": " + options[i]);
            }
        }

        isFiftyFiftyUsed = true;
    }
    public void useAudiencePoll(String[] options, char correctAnswer) {
        if (isAudiencePollUsed) {
            System.out.println("The Audience Poll lifeline has already been used. Consider another lifeline");
            return;
        }

        Random random = new Random();
        int correctIndex = correctAnswer - 'A';
        int[] poll = new int[options.length];
        int remaining = 100;

        for (int i = 0; i < poll.length; i++) {
            if (i == correctIndex) {
                poll[i] = 50 + random.nextInt(21);
            } else {
                poll[i] = random.nextInt(remaining / 2);
            }
            remaining -= poll[i];
        }

        poll[correctIndex] += remaining;

        System.out.println("Audience Poll Results:");
        for (int i = 0; i < options.length; i++) {
            System.out.println("Option " + (char) ('A' + i) + ": " + poll[i] + "%");
        }

        isAudiencePollUsed = true;
    }
    public void useLifeline(String[] options, char correctAnswer, Scanner scanner) {
    	try {
        System.out.println("\nSelect a lifeline:");
        System.out.println("1. 50-50");
        System.out.println("2. Audience Poll");
        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            useFiftyFifty(options, correctAnswer);
        } else if (choice.equals("2")) {
            useAudiencePoll(options, correctAnswer);
        } else {
            System.out.println("Invalid choice. No lifeline used.");
        }
    }
    	catch (Exception e) {
            System.out.println("An error occurred while using a lifeline: " + e.getMessage());
        }
    }
    public void askQuestion(String question, String[] options, char correctAnswer, Scanner scanner) {
    	try {
        System.out.println("\n" + question);
        for (int i = 0; i < options.length; i++) {
            System.out.println("Option " + (char) ('A' + i) + ": " + options[i]);
        }
        System.out.println("Option E: Lifeline");
        System.out.println("Option F: Quit");

        System.out.println("\nEnter your answer (A, B, C, D, E, or F):");
        String answer = scanner.nextLine().trim();

        if (answer.equalsIgnoreCase("F")) {
            System.out.println("You chose to quit the game.");
            System.out.println("Final score: " + score);
            System.exit(0);
        } else if (answer.equalsIgnoreCase("E")) {
            useLifeline(options, correctAnswer, scanner);
            askQuestion(question, options, correctAnswer, scanner);
        } else if (answer.equalsIgnoreCase(String.valueOf(correctAnswer))) {
            System.out.println("Good work! 🌟 That’s absolutely correct!");
            score += 10;
            System.out.println("Your current score is: " + score);
        } else {
            System.out.println("Sorry, that’s not right. The correct answer was:  Option " + correctAnswer);
            System.out.println("Game Over. Final score: " + score);
            System.exit(0);
        }
    }
    	catch (Exception e) {
            System.out.println("An error occurred while processing your answer: " + e.getMessage());
        }
    }
    public void startQuiz() {
    	try {
        Scanner scanner = new Scanner(System.in);

        askQuestion("What is the capital of France?",
                new String[]{"Paris", "London", "Berlin", "Madrid"}, 'A', scanner);
        askQuestion("How many days are there in a leap year?",
                new String[]{"365", "366", "364", "367"}, 'B', scanner);
        askQuestion("What is 5 + 3?",
                new String[]{"6", "7", "8", "9"}, 'C', scanner);
        askQuestion("Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 'B', scanner);
        askQuestion("What color are bananas?",
                new String[]{"Red", "Blue", "Yellow", "Green"}, 'C', scanner);
        askQuestion("Which animal is known as the King of the Jungle?",
                new String[]{"Tiger", "Elephant", "Lion", "Bear"}, 'C', scanner);

        System.out.println("Congratulations! You have SUCCESSFULLY completed the quiz.");
        System.out.println("Your final score is: " + score);
    }
    	catch (Exception e) {
            System.out.println("An error occurred during the game: " + e.getMessage());
        }
}
}

  