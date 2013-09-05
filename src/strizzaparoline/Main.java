/*
 * Main.java
 *
 * Created on 28 febbraio 2007, 1.31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package strizzaparoline;

/**
 *
 * @author ferrara
 */
public class Main {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        System.out.println("Welcome to the StrizzaStringa!!!");
        

        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StrizzaParole().setVisible(true);
            }
         
        });
         
        
        
    }
    
}
