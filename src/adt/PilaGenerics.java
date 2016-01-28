/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import adt.*;


/**
 *
 * @author cenacchi
 */
public class PilaGenerics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
          Pila<String> p=new Pila<String>();
        p.push("primo");
        p.push("secondo");
        p.push("terzo");
        while (!p.isEmpty()){
            System.out.println(p.top());
            p.pop();
        }
        Pila<Integer> p1=new Pila<Integer>();
         p1.push(10);
        p1.push(29);
        p1.push(30);
        while (!p1.isEmpty()){
            System.out.println(p1.top());
            p1.pop();
        }
        
    }
}
