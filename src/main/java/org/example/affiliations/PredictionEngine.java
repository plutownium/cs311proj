package org.example.affiliations;

import org.example.pollStuff.Question;
import org.example.pollStuff.Answer;

import java.util.Arrays;

public class PredictionEngine {
    int liberal = 0;
    int conservative = 0;
    int authoritarian = 0;
    int libertarian = 0;


    public void tallyTotals(String answerSeries, Question[] questions) {
        String[] choices = answerSeries.split(",");
        for (int i = 0; i < questions.length; i++) {
            int choice = Integer.parseInt(choices[i]);
            Answer[] potentialAnswers = questions[i].getAnswers();
            Answer chosenAnswer = potentialAnswers[choice - 1];
            integratePointsFromAnswer(chosenAnswer);
        }
    }

    // tally up results per question using this method
    public void integratePointsFromAnswer(Answer answer) {
        int[] points = answer.getPoints();
        liberal += points[0];
        conservative += points[1];
        authoritarian += points[2];
        libertarian += points[3];
    }

    public Affiliation predictAffiliation() {
        return getLeadingParty();
    }

    public Affiliation getLeadingParty() {
        int[] affiliationTotals = new int[]{liberal, conservative, authoritarian, libertarian};;
        Arrays.sort(affiliationTotals);
        int max = affiliationTotals[affiliationTotals.length - 1];
        if (max == liberal) {
            return new Affiliation("liberal");
        } else if (max == conservative) {
            return new Affiliation("conservative");
        } else if (max == authoritarian) {
            return new Affiliation("authoritarian");
        } else if (max == libertarian) {
            return new Affiliation("libertarian");
        } else {
            return new Affiliation("error"); // fixme: should never happen
        }
    }
}
