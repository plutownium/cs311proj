package org.example.pollStuff;

public class Question {
    private String prompt;
    private Answer[] answers;

    public Question(String prompt, Answer[] answers) {
        this.prompt = prompt;
        this.answers = answers;
    }

    public String getPrompt( ) {
        return this.prompt;
    }

    public Answer[] getAnswers() {
        return this.answers;
    }
}
