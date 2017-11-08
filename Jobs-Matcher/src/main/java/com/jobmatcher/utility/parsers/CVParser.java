package com.jobmatcher.utility.parsers;

import java.io.IOException;

/**
 * Created by gevlad on 16-Jan-17.
 */
public class CVParser {

    public static String[] parseFile(String fileName) {

        String st = null;
        try {
            st = Parser.parse(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] wordsInCV = st.split("[^a-zA-Z']+");

        return wordsInCV;
    }
}
