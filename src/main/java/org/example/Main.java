package org.example;

import org.example.affiliations.Affiliation;
import org.example.uiUtil.UIUtil;
import org.example.fileUtil.FileUtil;
import org.example.pollStuff.Question;
import org.example.pollStuff.Answer;
import org.example.affiliations.PredictionEngine;

public class Main {
    public static void main(String[] args) {
        UIUtil uiUtil = new UIUtil();
        Question[] questions = FileUtil.readQuestions();
        String answerSeries = "";
        for (Question question: questions) {
            uiUtil.showPromptAndAnswersFor(question);
            // record answers to the questions
            char choice = uiUtil.getResponse();
            // store answers to the questions
            answerSeries = FileUtil.queueLogging(answerSeries, choice);
            System.out.println("answerSeries: " + answerSeries);
        }
        // "You are most likely to be a ... "
        PredictionEngine predictionEngine = new PredictionEngine();
        predictionEngine.tallyTotals(answerSeries, questions);
        Affiliation affiliation = predictionEngine.predictAffiliation();
        // Progress to showing the answer, logging and wrapping up.
        answerSeries = FileUtil.queueLogging(answerSeries, (char) affiliation.getCode());
        FileUtil.logAnswersToCsv(answerSeries);
        uiUtil.showPrediction(affiliation);
        uiUtil.exitProgram();
    }
}
