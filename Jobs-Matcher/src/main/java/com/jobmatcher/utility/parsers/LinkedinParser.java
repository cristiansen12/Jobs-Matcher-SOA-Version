package com.jobmatcher.utility.parsers;

import java.io.IOException;

/**
 * Created by gevlad on 19-Jan-17.
 */
public class LinkedinParser {

    public static String[] parseFile(String fileName) {

        String st = null;
        try {
            st = Parser.parse(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] wordsInLikedin = st.split("[^a-zA-Z']+");

        return wordsInLikedin;
    }
}
