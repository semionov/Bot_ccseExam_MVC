package model;

import view.Messages;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class File {

    //list of String[] arrays that are lines of elements for each question of quiz
    private final List<String[]> listOfQuizLines = new ArrayList<String[]>();

    public File(String filepath) throws IOException {
        fillUpMapFromFile(filepath);
    }

    public File() {
    }

    public int getArrayLength() {
        return listOfQuizLines.size();
    }

    public void fillUpMapFromFile(String filepath) throws IOException {
        int index = 0;
        String line = "";
        String[] elements = new String[5];
        int linesCounter = 0;

        //create reader for file
        BufferedReader reader = new BufferedReader
            (new InputStreamReader
                (new FileInputStream(filepath),"Unicode"));

        // Each line of txt.file contains six elements(Strings) divided by "|" symbol,
        //
        // first element is integer (2 or 3) that indicates wether the question is
        //                                        2: closed (false or true answers only)
        //                                        3: open (multianswer)
        // second element is the question
        // just in case of open question (see first element) next three elements are answers
        // last element is letter that indicates the correct answer

        // The next method is aimed to
        // 1. read file by lines
        // 2. divide each line with "|" symbol
        // 3. put divided parts into array of Strings
        // 4. add array to ArrayList of String[]

        line = reader.readLine();
        while (line != null) {
            elements = line.split(Pattern.quote("|"));
            listOfQuizLines.add(index, elements);
            index++;
            line = reader.readLine();
            linesCounter++;
        }

        //print information about fullUp process to console and send me
        BotContainer.getBot().sendMsg(linesCounter + " lines uploaded to array\n" +
            "Array size: " + listOfQuizLines.size(), "290631155");
        System.out.println(linesCounter + " lines uploaded to array\n" +
            "Array size: " + listOfQuizLines.size());
        reader.close();
    }

    // Next three methods retrieve answers, question and correct option correspondingly
    // from the String[] elements that in it's turn is retrieved from ArrayList of quiz lines.
    // It is necessary to provide correct functioning of bot based on telegram library

    public String[] answers(int index) {

        String[] line = new String[6];
        line = listOfQuizLines.get(index);

        String[] answers;

        if (line[0].equals("3")) {
            answers = new String[3];
            answers[0] = line[2];
            answers[1] = line[3];
            answers[2] = line[4];
        } else {
            answers = new String[2];
            answers[0] = Messages.falseTrue[1];
            answers[1] = Messages.falseTrue[0];
        }
        return answers;
    }

    public String question(int index) {
        return listOfQuizLines.get(index)[1];
    }

    public int correctOption(int index) {
        String correctAnswer = listOfQuizLines.get(index)[listOfQuizLines.get(index).length - 1];
        int answer = 0;

        if (correctAnswer.equals("a")) {
            answer = 0;
        } else if (correctAnswer.equals("b")) {
            answer = 1;
        } else if (correctAnswer.equals("c")) {
            answer = 2;
        }
        return answer;
    }
}