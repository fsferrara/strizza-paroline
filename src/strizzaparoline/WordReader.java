/*
 * WordReader.java
 *
 * Created on 28 febbraio 2007, 22.24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package strizzaparoline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ferrara
 */
public class WordReader {
    
    /** Creates a new instance of WordReader */
    
    
    private int next;
    private ArrayList list = null;
    
    public WordReader() {
        
        next=0;
        list = new ArrayList();
        if (inizializzaFile() == 0)
            System.err.println("Il file word.txt non contiene parole?!?");
        
    }
    
    public void wordRoollback() {
        next=0;
    }
    
    
    public String nextWord() {
        String buffer = null;
        
        
        if (next < list.size()) {
            buffer = (String) list.get(next);
        }
        
        next++;
        
        return buffer;
    }
    
    private int inizializzaFile() {
        
        int count = 0;
        
        BufferedReader in = null;
        
        StringConverter converter = new StringConverter("UTF-8");
        
        try {
            
            /* /Users/ferrara/Desktop/ */
            in = new BufferedReader(new FileReader("words.txt"));
            String str;
            
            while ((str = in.readLine()) != null) {
                
                try {
                    str = converter.encode(str);
                } catch (CharacterCodingException e) {
                    System.out.println("ERRORE nel convertire le stringhe!");
                    str = null;
                }
                
                list.add(str);
                count++;
            }
            
            in.close();
            
            
        } catch (IOException e) {
            System.out.println("Errore nel trovare il file words.txt!");
        }
        
        return count;
    }
}
