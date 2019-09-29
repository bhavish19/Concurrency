package skeletonCodeAssgnmt2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.*;

public class WordPanel extends JPanel implements Runnable {
	public static volatile boolean done;
	WordRecord[] words;
	private int noWords;
	public int maxY;
        int num=0, num1=0, num2=0;
        String[] msg;
        boolean t1 = true;
        boolean t2 = false;
        Score score;

		
	public void paintComponent(Graphics g) {
            int width = getWidth();
            int height = getHeight();
            g.clearRect(0,0,width,height);
            g.setColor(Color.red);
            g.fillRect(0,maxY-10,width,height);
            g.setColor(Color.black);
            g.setFont(new Font("Helvetica", Font.PLAIN, 26));
            //draw the words
            //animation must be added 
            for (int i=0;i<noWords;i++){	    	
                //g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
                g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words
                repaint();	
            }
		   
	}
		
	WordPanel(WordRecord[] words,Score s, int maxY) {
            this.words=words;
            this.score = s;
            noWords = words.length;
            done=false;
            this.maxY=maxY;		
	}
      
        public synchronized void incrementNum(){ num++; }
      
        public synchronized boolean getVal(){
      
            if(num != noWords){
                return true;
            }
            else{
                return false;
            }
        }
      
        public synchronized int getMaxY(){
            return maxY;
        }
      
        public synchronized void incrWord(int b){
            if(t1){
                num1++;
         
                if(num1 == WordApp.totalWords){
                    t1=false;
                }
            }
            else{
                words[b].setWord("");
            }
        }
      
		
	public void run() {

            if(t2){
        
                for(int k=0 ; k<noWords; k++){
                    words[k].resetWord();
                }
            }
        
            t2 = true;
        
            Game[] g = new Game[noWords];
        
            num1 = 0;
            t1 = true;
            num2 = 0;
        
            msg = new String[noWords];
        
            Arrays.fill(msg, "");
        
            for (int i = 0; i < noWords; i++) {
                incrWord(i);
                g[i] = new Game(this,i);
                g[i].start();
            }

            repaint();
           
        }
}

