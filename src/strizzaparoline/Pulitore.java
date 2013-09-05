/*
 * Pulitore.java
 *
 * Created on 1 marzo 2007, 0.59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package strizzaparoline;

import java.nio.charset.CharacterCodingException;

/**
 *
 * @author ferrara
 */
public class Pulitore {
    
    private boolean inizia=false;
    private boolean finisce=false;
    private boolean contiene=false;
    private boolean max=false;
    private boolean min=false;
    
    private String iniziaString = null;
    private String finisceString = null;
    private String contieneString = null;
    private int maxCaratteri = 50;
    private int minCaratteri = 1;
    
    StringConverter converter = null;
    WordReader wr = null;
    
    /** Creates a new instance of Pulitore */
    public Pulitore() {
        converter = new StringConverter("UTF-8");
        wr = new WordReader();
    }
    
    ///////////////////////////////////////////////
    public void enableInizia(String str) {
        if (str.length() > 0) {
            try {
                iniziaString = converter.encode(str);
            } catch (CharacterCodingException e) {
                iniziaString = new String(str);
            }
            inizia=true;
        } else {
            inizia=false;
        }
        System.err.println("Inizia >> " + str);
    }
    
    public void enableFinisce(String str) {
        if (str.length() > 0) {
            try {
                finisceString = converter.encode(str);
            } catch (CharacterCodingException e) {
                finisceString = new String(str);
            }
            finisce=true;
        } else {
            finisce=false;
        }
        System.err.println("Finisce >> " + str);
    }
    
    public void enableContiene(String str) {
        if (str.length() > 0) {
            try {
                contieneString = converter.encode(str);
            } catch (CharacterCodingException e) {
                contieneString = new String(str);
            }
            contiene=true;
        } else {
            contiene=false;
        }
        System.err.println("Contiene >> " + str);
    }
    
    public void enableMax(String str) {
        if (str.length() > 0) {
            try {
                maxCaratteri = Integer.parseInt(str);
                max=true;
            } catch (NumberFormatException e) {
                maxCaratteri = 50;
                max=false;
            }
        } else {
            max=false;
        }
        System.err.println("Max >> " + str);
    }
    
    public void enableMin(String str) {
        if (str.length() > 0) {
            try {
                minCaratteri = Integer.parseInt(str);
                min=true;
            } catch (NumberFormatException e) {
                minCaratteri = 50;
                min=false;
            }
        } else {
            min=false;
        }
        System.err.println("Min >> " + str);
    }
    
    ///////////////////////////////////////////////
    
    private boolean checkInizia(String word) {
        if (iniziaString.length() > word.length())
            return false;
        
        return word.startsWith(iniziaString);
    }
    
    private boolean checkFinisce(String word) {
        if (finisceString.length() > word.length())
            return false;
        
        return word.endsWith(finisceString);
    }
    
    private boolean checkContiene(String word) {
        if (contieneString.length() > word.length())
            return false;
        
        return word.contains(contieneString);
    }
    
    private boolean checkMax(String word) {
        if (word.length() > maxCaratteri)
            return false;
        else
            return true;
    }
    
    private boolean checkMin(String word) {
        if (word.length() < minCaratteri)
            return false;
        else
            return true;
    }
    
    public void resettati() {
        wr.wordRoollback();
    }
    
    public String applicaFiltri() {
        
        int i = 0;
        boolean add;
        
        String word = wr.nextWord();
        
        while (word != null) {
            
            add = true;
            
            if (add && max) {
                add = checkMax(word);
            }
            
            if (add && min) {
                add = checkMin(word);
            }
            
            if (add && inizia) {
                add = checkInizia(word);
            }
            
            if (add && finisce) {
                add = checkFinisce(word);
            }
            
            if (add && contiene) {
                add = checkContiene(word);
            }
            
            if (add)
                return word;
            else
                word = wr.nextWord();
        }
        
        return null;
        
    }
    
    
}
