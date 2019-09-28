/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skeletonCodeAssgnmt2;

/**
 *
 * @author bhavm
 */
public class Game implements Runnable{
    WordPanel wp;
    volatile int x;
    Thread th;
    WordRecord wr;
    WordApp wa;
    
    public Game (WordPanel w, int a){
        this.wp = w;
        this.x = a;
    }
    
    /*public void change(){
        int y = wp.words[x].getY();
        wp.words[x].setY(y +wp.words[x].getSpeed());
        
    }*/
    
    public void start(){
        if (th == null){
            th = new Thread (this);
            th.start();
        }
    }
    
    public void stop(){
        if (th!=null){
            th=null;
        }
    }
    
    public void run(){
        while(true){
            if (!wp.words[x].dropped()){
                wp.words[x].drop(wp.words[x].getSpeed());
                /*change();*/
                //wp.repaint();
                 if (wp.maxY-wp.words[x].getY()<=10){
                    wp.words[x].setWord("");
                    wa.score.missedWord();     
                }
               
                wp.repaint();
                
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.getMessage();
                }
            }
            if (wp.words[x].matchWord(wa.text)){
                
                wa.score.caughtWord(wp.words[x].getWord().length());
                wa.caught.setText("Caught: " + wa.score.getCaught() + "    ");
                wa.scr.setText("Score:" + wa.score.getScore()+ "    ");
                }
                
            wa.missed.setText("Missed:" + wa.score.getMissed()+ "    ");
            
        }
    }
}
