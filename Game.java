/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skeletonCodeAssgnmt2;

import javax.swing.JOptionPane;

public class Game implements Runnable{

   WordPanel wp;
   int i;
   Thread th;
   WordApp wa;
   
   public Game(WordPanel w, int i){
      this.wp = w;
      this.i = i;
   }
   

    public void start() {
        if (th == null) {
            th = new Thread(this);
            th.start();
        }
    }

    public void stop() {
        if (th != null) {
            th = null;
        }
    }
    

   public void run(){
      
      while (wp.t1) {
      
            if (wp.words[i].matchWord(wp.msg[i]) || wp.words[i].getY() >= wp.words[i].maxY) {
            
                if (wp.words[i].matchWord(wp.msg[i])) {
                
                    int y= wp.words[i].getWord().length();
                    wp.score.caughtWord(y);
                    
                } 
                else {
                    
                    wp.score.missedWord();
                }
                
                wa.caught.setText("Caught: " + wp.score.getCaught() + " ");
                wa.missed.setText("Missed:" + wp.score.getMissed() + " ");
                wa.scr.setText("Score:" + wp.score.getScore() + " ");
                                
                wp.words[i].resetWord();
                wp.incrWord(i);
                wp.msg[i] = "";
                
            } 
            else {
            
                wp.msg[i] = "";
            }

            if (!wp.words[i].dropped()) {

                wp.words[i].setY(wp.words[i].getY() + wp.words[i].getSpeed());
                wp.repaint();
                
                try {
                
                    Thread.sleep(100);
                    
                } catch (InterruptedException e) {
                    e.getMessage();
                }

            }
        }
        
       while (wp.words[i].getY() <= wp.words[i].maxY) {
        
            if (wp.words[i].matchWord(wp.msg[i])) {
            
                int y = wp.words[i].getWord().length();
                wp.score.caughtWord(y);
                
                wa.caught.setText("Caught: " + wp.score.getCaught() + " ");
                wa.missed.setText("Missed:" + wp.score.getMissed() + " ");
                wa.scr.setText("Score:" + wp.score.getScore() + " ");
             
                wp.words[i].setWord(" ");
                wp.incrementNum();
                break;

            }
            wp.words[i].setY(wp.words[i].getY() + wp.words[i].getSpeed());
            wp.repaint();
            
            try {
                Thread.sleep(100);
                
            } catch (InterruptedException e) {
                 e.getMessage();
             
             }

        }       

       if((!wp.words[i].getWord().equals("")) && (wp.words[i].getY() >= wp.words[i].maxY)) {
            
            wp.score.missedWord();
            
            wa.caught.setText("Caught: " + wp.score.getCaught() + " ");
            wa.missed.setText("Missed:" + wp.score.getMissed() + " ");
            wa.scr.setText("Score:" + wp.score.getScore() + " ");
            wp.words[i].setWord(" ");
            wp.incrementNum();
            
        }

        if(WordApp.score.getTotal()== wp.num1){
            wp.num1++;
            JOptionPane.showMessageDialog (null, "\t\tGame Over... \nTotal score is: " + wa.score.getScore()+"\n Number of words missed: " + wa.score.getMissed()+"\n Number of words caught: " + wa.score.getCaught(), "Results!",JOptionPane.DEFAULT_OPTION);
            
        }

      
   }

}

