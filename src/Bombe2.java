import java.awt.Color;

public class Bombe2 extends Spielfeld{
	double radius,x,y;


	public Bombe2(){
	x = 0.5;
	y = 0.5;
	radius = 0.05;
	}
	
	public void draw(){
		StdDraw.setPenColor(Color.BLUE);
        StdDraw.filledCircle(x, y, radius);
	}
             
	public static void main(String[]args){
		Bombe2 bomb = new Bombe2();
		bomb.draw();
	}
}
             
     	
/*	public Bombe(explode){	
	double radius,x,y; 
	this.x = 1;
	this.y = 1;
	this.radius = 2;
	public void draw()
             {
             StdDraw.setPenColor(RED);
             StdDraw.filledCircle(x, y, radius);}
      */      
             


