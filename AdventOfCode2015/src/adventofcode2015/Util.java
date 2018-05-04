/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Util {

    /**
     * Retrieves the text inside the file of the path given
     *
     * @param path: it has to be "dayX/name.txt" where X is the number of the day and name 
     * is the name of the file
     * @return
     */
    public static String ReadFileOneLine(String path) {
        final String DEFAULT_PATH = "src/adventofcode2015/";
        String fileContent = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(DEFAULT_PATH + path));
            String line = bf.readLine();
            while (line != null) {
                fileContent += line;
                line = bf.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileContent;
    }
}
