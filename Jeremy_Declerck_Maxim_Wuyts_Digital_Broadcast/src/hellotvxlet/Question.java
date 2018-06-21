/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

/**
 *
 * @author student
 */
public class Question {
    protected String Ask;
    protected String A;
    protected String B;
    protected String C;
    protected String D;
    protected String Correct;
    
    public Question(String ask){
        Ask = "Question not found";
        Ask = ask;
    }
    
    public String getAsk(){
        return Ask;
    }
    
    public void setA(String a){
        A = a;
    }
    public String getA(){
        return A;
    }
    
    public void setB(String b){
        B = b;
    }
    public String getB(){
        return B;
    }
    
    public void setC(String c){
        C = c;
    }
    public String getC(){
        return C;
    }
    
    public void setD(String d){
        D = d;
    }
    public String getD(){
        return D;
    }
    
    public void setCorrect(String correct){
        Correct = correct;
    }
    public String getCorrect(){
        return Correct;
    }
}
