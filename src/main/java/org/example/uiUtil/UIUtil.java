package org.example.uiUtil;

import java.util.Scanner;

import org.example.affiliations.Affiliation;
import org.example.pollStuff.Answer;
import org.example.pollStuff.Question;

public class UIUtil {

    private Scanner scanner = new Scanner(System.in);

    public void showPromptAndAnswersFor(Question question) {
        String prompt = question.getPrompt();
        Answer[] answers = question.getAnswers();
        Printer.print(prompt);
        for (int i = 0; i < answers.length; i++) {
            String solution = i + 1 + ": " + answers[i].getText();
            Printer.print(solution);
        }
    }

//    public void listAnswersFor(Question question) {
//        // something to do with printer
//        Answer[] answers = question.getAnswers();
//        int ansNum = 0;
//        for (Answer answer: answers) {
//            Printer.print((char) ansNum + ": " + answer.getText());
//        }
//    }

    public char getResponse() {
        Printer.promptResponse();
        // Read the user's input
        String userInput = scanner.nextLine();
        // Extract the first character as a char
        char choice = userInput.charAt(0);
        return choice;
    }

    public void showPrediction(Affiliation aff) {
        String party = aff.getAffiliation();
        Printer.print("The best guess is that you are a " + party + " voter.");
    }


    public void exitProgram() {
        scanner.close();
        Printer.print("Thanks for running the program! Goodbye.");

    }
}
