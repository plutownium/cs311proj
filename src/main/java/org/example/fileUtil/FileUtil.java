package org.example.fileUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.example.pollStuff.Question;
import org.example.pollStuff.Answer;

public class FileUtil {
    // todo: change back to Question[]
    public static Question[] readQuestions() {
        JSONParser parser = new JSONParser();
        String fileName = "questions.json";
        try {
            String fileNameDir = changeToQuestionsDir();
            listFiles();
            System.out.println("Parsing " + fileName);
            Object obj = parser.parse(new FileReader(fileNameDir + "/" + fileName));
            JSONArray array = new JSONArray();
            // Cast the parsed object to a JSONObject
            JSONObject jsonObject = (JSONObject) obj;
            // Get the "questions" array from the JSON object
            JSONArray questionsArray = (JSONArray) jsonObject.get("questions");
            Question[] questionsFromJSON = new Question[questionsArray.size()];
            // Iterate through each question in the array
            int questionIndex = 0;
            for (Object questionObj : questionsArray) {
                JSONObject questionJson = (JSONObject) questionObj;
                // Extract the prompt and answers from the question JSON
                String prompt = (String) questionJson.get("prompt");
                JSONArray answersArray = (JSONArray) questionJson.get("answers");
                Answer[] answerArray = new Answer[answersArray.size()];
                // Iterate through each answer in the answers array
                int answerIndex = 0;
                for (Object answerObj : answersArray) {
                    JSONObject answerJson = (JSONObject) answerObj;
                    // Extract the text and points from the answer JSON
                    String answerText = (String) answerJson.get("text");
                    JSONArray pointsArray = (JSONArray) answerJson.get("points");
                    int[] pointsAsIntArr = new int[pointsArray.size()];
                    for (int i = 0; i < pointsArray.size(); i++) {
                        pointsAsIntArr[i] = ((Long) pointsArray.get(i)).intValue();
                    }
                    Answer answer = new Answer(answerText, pointsAsIntArr);
                    answerArray[answerIndex] = answer;
//                    System.out.println("Answer: " + answerText);
//                    System.out.println("Points: " + pointsArray);
                    answerIndex++;
                }

                questionsFromJSON[questionIndex] = new Question(prompt, answerArray);
                questionIndex++;
            }
            return questionsFromJSON;
        } catch (FileNotFoundException e) {
            listFiles();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Question[0];
    }

    public static String queueLogging(String currentSeries, char choice) {
        if (currentSeries.isEmpty()) {
            return String.valueOf(choice);
        }
        // update series of answers.
        // i.e. if currentSeries is 1,3,2,1 and choice is "3", output will be 1,3,2,1,3
        return currentSeries + "," + choice;
    }

    public static void logAnswersToCsv(String answerSeries) {
        // Specify the file name
        String fileName = "answers.csv";
        try {
            // Create a BufferedWriter to write to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            // Write the text to the file
            writer.write(answerSeries);
            writer.newLine(); // Add a new line for better formatting (optional)

            // Close the writer to save changes and release resources
            writer.close();

            System.out.println("Text has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String changeToQuestionsDir() {
        // Note for reviewers: This will undoubtedly break on your system
        // unless you're also running the project in a file path "/home/rlm/Code/learn/java/cs311proj".
        // You can fix it by changing projectPath to reflect the location of the project.
        String projectPath = "/home/rlm/Code/learn/java/cs311-ai-v2";
        String directoryPath = projectPath + "/cs311proj/src/main/java/org/example/data";

        System.setProperty("user.dir", questionsDir);
        return questionsDir;
    }

    public static void listFiles() {
        // Note for reviewers: This will undoubtedly break on your system
        // unless you're also running the project in a file path "/home/rlm/Code/learn/java/cs311proj".
        // You can fix it by changing projectPath to reflect the location of the project.
        String projectPath = "/home/rlm/Code/learn/java/cs311-ai-v2";
        String directoryPath = projectPath + "/cs311proj/src/main/java/org/example/data";

        // Create a File object for the specified directory
        File directory = new File(directoryPath);

        // Get the list of files and directories in the specified directory
        File[] files = directory.listFiles();

        // Display the names of files and directories
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.err.println("Failed to list directory contents.");
        }
    }
}
