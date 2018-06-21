package hellotvxlet;

import javax.tv.xlet.*;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.MediaTracker;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import java.awt.Image;
import java.util.Random.*;
import org.dvb.event.*;
import org.havi.ui.event.*;
import org.havi.ui.*;
import java.awt.*;
import javax.swing.*;

public class HelloTVXlet implements Xlet, HActionListener, ActionListener,UserEventListener {
    
    static HScene scene = null;
    private HText start;
    private HText end;
    private HText pointsResult;
    private HText again;
    private boolean hasStarted = false;
    private boolean hasEnded = false;
    private HTextButton a;
    private HTextButton b;
    private HTextButton c;
    private HTextButton d;
    private boolean AisSelected = true;
    private boolean BisSelected;
    private boolean CisSelected;
    private boolean DisSelected;
    private HText question;
    private HText pointsText;
    private HText validpopup;
    int activeQuestion = 1;
    int points = 0;
    Question[] questions = new Question[20];
    private boolean questionIsDone;
    //BufferedImage background;
    private Image img;
    //Toolkit toolkit;
    //MediaTracker tracker;

    public HelloTVXlet() {
        
    }
    
    public static HScene getScene(){
        return scene;
    }
    
    public void initXlet(XletContext context) {
        questions[1] = new Question("Wat is de hoofdstad van Peru?");
        questions[1].setA("Lima");
        questions[1].setB("Bogota");
        questions[1].setC("Quito");   
        questions[1].setD("Abuja");
        questions[1].setCorrect("a");
        
        questions[2] = new Question("Welke planeet is het dichtste bij de zon?");
        questions[2].setA("Mars");
        questions[2].setB("Aarde");
        questions[2].setC("Mercurius");   
        questions[2].setD("Venus");
        questions[2].setCorrect("c");
        
        questions[3] = new Question("Bij welke sport kan je de Davis Cup winnen?");
        questions[3].setA("Baseball");
        questions[3].setB("Golf");
        questions[3].setC("Tennis");   
        questions[3].setD("Voetbal");
        questions[3].setCorrect("c");
        
        questions[4] = new Question("Welke bekende Engelse band uit de jaren zestig \n en bestaat uit John, Paul, George en Ringo?");
         questions[4].setA("The Beatles");
         questions[4].setB("ACDC");
         questions[4].setC("De Kreuners");
         questions[4].setD("Jackson 5 ");
         questions[4].setCorrect("a");
        
         questions[5] = new Question("Van welke wielrenner is volgende \n bijnaam: de kannibaal?");
         questions[5].setA("Peter Sagan");
         questions[5].setB("Greg Van Avermaet");
         questions[5].setC("Johan Musseeuw");
         questions[5].setD("Eddy Merckx ");
         questions[5].setCorrect("d");
         
         questions[6] = new Question("Hoeveel tanden heeft een volwassen mens normaal?");
         questions[6].setA("30");
         questions[6].setB("28");
         questions[6].setC("35");
         questions[6].setD("32");
         questions[6].setCorrect("d");

         questions[7] = new Question("Wat is de grootste stad van Europa?");
         questions[7].setA("Istanbul");
         questions[7].setB("London");
         questions[7].setC("Moskou");
         questions[7].setD("Parijs");
         questions[7].setCorrect("a");

         questions[8] = new Question("Wie was de uitvinder van de gloeilamp?");
         questions[8].setA("Newton");
         questions[8].setB("Dalton");
         questions[8].setC("Edison");
         questions[8].setD("Einstein");
         questions[8].setCorrect("c");
         
         questions[9] = new Question("Hoeveel landen telt Europese Unie?");
         questions[9].setA("31");
         questions[9].setB("28");
         questions[9].setC("27");
         questions[9].setD("24");
         questions[9].setCorrect("c");


         questions[10] = new Question ("Waar is Peter Paul Rubens geboren?");
         questions[10].setA("Belgie");
         questions[10].setB("Duitsland");
         questions[10].setC("Nederland");
         questions[10].setD("Frankrijk");
         questions[10].setCorrect("b");

        questions[11] = new Question ("Hoe heet de enige Amerikaanse president \n die vier keer gekozen is?");
         questions[11].setA("Franklin Roosevelt");
         questions[11].setB("George W Bush");
         questions[11].setC("Bill Clinton");
         questions[11].setD("Barack Obama");
         questions[11].setCorrect("a");


        questions[12] = new Question ("Wat is de hoogste berg ter wereld?");
         questions[12].setA("Mont Blanc");
         questions[12].setB("Kilimanjaro");
         questions[12].setC("Everest");
         questions[12].setD("K2");
         questions[12].setCorrect("c");
         
         questions[13] = new Question("Wie won het WK voetbal in 2014?"); 
         questions[13].setA("Brazilië");
         questions[13].setB("Duitsland");
         questions[13].setC("België");
         questions[13].setD("Nederland");
         questions[13].setCorrect("b");

        questions[14] = new Question("Hoe heet de muzikale film met in de \n hoofdrol John Travolta en Olivia Newton John? ");
         questions[14].setA("Mama Mia");
         questions[14].setB("Romeo & Julia"); 
        questions[14].setC("Grease"); 
        questions[14].setD("Titanic"); 
        questions[14].setCorrect("c");
        
        questions[15] = new Question("Welke kleur is cobalt? ");
         questions[15].setA("Blauw");
         questions[15].setB("Groen"); 
        questions[15].setC("Rood"); 
        questions[15].setD("Geel"); 
        questions[15].setCorrect("a");
         
         
         
        scene = HSceneFactory.getInstance().getDefaultHScene();
        startScreen(); 
    }
    
    public void startScreen(){
        scene.removeAll();
        AisSelected = true;
        activeQuestion = 1;
        points = 0;
        hasStarted = false;
        hasEnded = false;
        start = new HText("Druk enter om de Quiz te starten");
        start.setLocation(100,10);
        start.setSize(500,100);
        start.setBordersEnabled(false);
        scene.add(start);
        
        scene.repaint();
        scene.validate();
        scene.setVisible(true);
    }
    
    public void askQuestion(){
        scene.removeAll();
        AisSelected = true;
        BisSelected = false;
        CisSelected = false;
        DisSelected = false;
        scene = HSceneFactory.getInstance().getDefaultHScene();
        question = new HText(questions[activeQuestion].getAsk());
        question.setBackground(Color.BLACK);
        question.setBackgroundMode(HVisible.BACKGROUND_FILL);
        question.setBordersEnabled(false);
        question.setLocation(100,100);
        question.setSize(500,80);
        
        
        scene.add(question);
        
        pointsText = new HText("points: " + points);
        pointsText.setLocation(600,10);
        pointsText.setSize(100,50);
        pointsText.setBordersEnabled(false);
        scene.add(pointsText);
        
        a = new HTextButton(questions[activeQuestion].getA());
        a.setLocation(125,250);
        a.setSize(200,100);
        a.setBackground(Color.GREEN);
        a.setBackgroundMode(HVisible.BACKGROUND_FILL);
        a.setActionCommand("a");
        a.addHActionListener(this);
        scene.add(a);
        
        
        b = new HTextButton(questions[activeQuestion].getB());
        b.setLocation(375,250);
        b.setSize(200,100);
        b.setBackground(Color.RED);
        b.setBackgroundMode(HVisible.BACKGROUND_FILL);
        b.setActionCommand("b");
        b.addHActionListener(this);
        scene.add(b);

        c = new HTextButton(questions[activeQuestion].getC());
        c.setLocation(125,400);
        c.setSize(200,100);
        c.setBackground(Color.RED);
        c.setBackgroundMode(HVisible.BACKGROUND_FILL);
        c.setActionCommand("a");
        c.addHActionListener(this);
        scene.add(c);
        
        d = new HTextButton(questions[activeQuestion].getD());
        d.setLocation(375,400);
        d.setSize(200,100);
        d.setBackground(Color.RED);
        d.setBackgroundMode(HVisible.BACKGROUND_FILL);
        d.setActionCommand("a");
        d.addHActionListener(this);
        scene.add(d);
        
        scene.repaint();
        scene.validate();
        scene.setVisible(true);
    }
   
    
    public void actionPerformed(ActionEvent e){
        String action = e.getActionCommand();
        if(action.equals("a")){
            a.setBackground(Color.RED);
            a.repaint();
            scene.add(a);
        scene.validate();
        scene.repaint();
        }
    }

    public void startXlet()  {
        EventManager manager = EventManager.getInstance();
        UserEventRepository repos = new UserEventRepository("Keys");
        repos.addAllArrowKeys();
        repos.addKey(HRcEvent.VK_ENTER);
        manager.addUserEventListener(this,repos);
        
    }
    
      public void userEventReceived(UserEvent e){
        if(e.getType() == KeyEvent.KEY_PRESSED){
            switch(e.getCode()){
                case HRcEvent.VK_LEFT:
                    if(BisSelected){
                        a.setBackground(Color.GREEN); a.repaint();
                        b.setBackground(Color.RED); b.repaint();
                        BisSelected = false;
                        AisSelected = true;
                        CisSelected = false;
                        DisSelected = false;
                    }
                    if(DisSelected){
                        d.setBackground(Color.RED); d.repaint();
                        c.setBackground(Color.GREEN); c.repaint();
                        CisSelected = true;
                        DisSelected = false;
                        AisSelected = false;
                        BisSelected = false;
                    }
                    break;
                case HRcEvent.VK_RIGHT:
                    if(AisSelected){
                        a.setBackground(Color.RED); a.repaint();
                        b.setBackground(Color.GREEN); b.repaint();
                        BisSelected = true;
                        AisSelected = false;
                        CisSelected = false;
                        DisSelected = false;
                    }
                    if(CisSelected){
                        c.setBackground(Color.RED); c.repaint();
                        d.setBackground(Color.GREEN); d.repaint();
                        DisSelected = true;
                        CisSelected = false;
                        AisSelected = false;
                        BisSelected = false;
                    }
                    break;
                case HRcEvent.VK_UP:
                    if(CisSelected){
                        a.setBackground(Color.GREEN); a.repaint();
                        c.setBackground(Color.RED); c.repaint();
                        AisSelected = true;
                        CisSelected = false;
                        BisSelected = false;
                        DisSelected = false;
                    }
                    if(DisSelected){
                        d.setBackground(Color.RED); d.repaint();
                        b.setBackground(Color.GREEN); b.repaint();
                        BisSelected = true;
                        DisSelected = false;
                        CisSelected = false;
                        AisSelected = false;
                    }
                    break;
                case HRcEvent.VK_DOWN:
                    if(AisSelected){
                        a.setBackground(Color.RED); a.repaint();
                        c.setBackground(Color.GREEN); c.repaint();
                        CisSelected = true;
                        AisSelected = false;
                        DisSelected = false;
                        BisSelected = false;
                    }
                    if(BisSelected){
                        b.setBackground(Color.RED); b.repaint();
                        d.setBackground(Color.GREEN); d.repaint();
                        DisSelected = true;
                        BisSelected = false;
                        AisSelected = false;
                        CisSelected = false;
                    }
                    break;
                case HRcEvent.VK_ENTER:
                    if(hasEnded){
                        startScreen();
                    }
                    if(hasStarted){
                        answer();
                    } 
                    if(!hasStarted){
                        askQuestion();
                        hasStarted = true;
                    }
                    
                    break;
            }
        }
    }
      
    public void update(){
        if(a.hasFocus()){
            System.out.println("a is focus");
        }
    }
    
    public void answer(){
        String userAnswer = new String("");
        String correctAnswer;
        correctAnswer = questions[activeQuestion].Correct;
        String A = questions[activeQuestion].getA();
        String B = questions[activeQuestion].getB();
        String C = questions[activeQuestion].getC();
        String D = questions[activeQuestion].getD();
        scene.removeAll();
        
        if(AisSelected){
            userAnswer = "a";
        }
        if(BisSelected){
            userAnswer = "b";
        }
        if(CisSelected){
            userAnswer = "c";
        }
        if(DisSelected){
            userAnswer = "d";
        }
        if(userAnswer.equals(correctAnswer)){
            
            points = points + 10;
            activeQuestion++;
            validpopup = new HText("Dat klopt helemaal!");
            validpopup.setLocation(200,300);
            validpopup.setBordersEnabled(false);
            validpopup.setSize(250,150);
            validpopup.setBackground(Color.GREEN);
            scene.add(validpopup);
            System.out.println(activeQuestion);
            scene.repaint();
            scene.validate();
            scene.setVisible(true);
            try{
            Thread.sleep(1000);
            } catch(InterruptedException ex){endGame();};
            scene.validate();
            scene.setVisible(true);
            if(activeQuestion < 16){
                AisSelected = true;
                askQuestion();
            } else{endGame(); hasEnded = true;};
             
            
        } else{
            if(correctAnswer.equals("a")){
            validpopup = new HText("Fout! Het juiste antwoord was " + A);
            
            } 
            if(correctAnswer.equals("b")){
                validpopup = new HText("Fout! Het juiste antwoord was " + B);
            }
            if(correctAnswer.equals("c")){
                validpopup = new HText("Fout! Het juiste antwoord was " + C);
            }
            if(correctAnswer.equals("d")){
                validpopup = new HText("Fout! Het juiste antwoord was " + D);
            }
            validpopup.setLocation(100,300);
            validpopup.setBordersEnabled(false);
            validpopup.setSize(500,150);
            validpopup.setBackground(Color.RED);
            scene.popToFront(validpopup);
            scene.add(validpopup);
            points = points + 0;
            activeQuestion++;
            
            System.out.println(activeQuestion);
            scene.repaint();
            scene.validate();
            scene.setVisible(true);
            try{
            Thread.sleep(1000);
            } catch(InterruptedException ex){endGame();};
            scene.validate();
            scene.setVisible(true);
            if(activeQuestion < 16){
                AisSelected = true;
                askQuestion();
            } else{endGame(); hasEnded = true;};
                
        }
        
    }
    
    public void endGame(){
        System.out.println("endGame()");
        scene.removeAll();
        scene.popToFront(end);
        scene.remove(validpopup);
        end = new HText("De quiz is over. Je behaalde " + points);
        end.setLocation(100,10);
        end.setSize(500,100);
        end.setBordersEnabled(false);
        scene.add(end);
        
        if(points > 0 && points < 50){
            pointsResult = new HText("Waardeloos!");
        } 
        if(points >= 50 && points < 100){
            pointsResult = new HText("Dat kan toch wel beter!");
        }
        if(points >= 100 && points < 150){
            pointsResult = new HText("Proper resultaat!");
        }
        if(points == 150){
            pointsResult = new HText("Alles correct? Fantastisch!");
        }
        
        pointsResult.setLocation(100,30);
        pointsResult.setSize(500,100);
        pointsResult.setBordersEnabled(false);
        scene.add(pointsResult);
        
        again = new HText("Druk op enter om opnieuw te proberen");
        again.setLocation(100,100);
        again.setSize(500,100);
        again.setBordersEnabled(false);
        scene.add(again);
        
        scene.repaint();
        scene.validate();
        scene.setVisible(true);
        
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
}
