package org.example.pollStuff;

public class Answer {
    private String text = "";
    private int[] points = new int[4];

    public Answer(String text, int[] points) {
        this.text = text;
        this.points = points;
    }

    public String getText() {
        return this.text;
    }

    public int[] getPoints() {
        return this.points;
    }
}
