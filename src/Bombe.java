import java.awt.Color;

public class Bombe extends Feld{



	public Bombe(){
	double radius,x,y;
	x = 0.5;
	y = 0.5;
	radius = 0.05;
             
	public void draw(){
             StdDraw.setPenColor(Color.BLUE);
             StdDraw.filledCircle(x, y, radius);
	}
             
     	
/*	public Bombe(explode){	
	double radius,x,y; 
	this.x = 1;
	this.y = 1;
	this.radius = 2;
	public void draw()
             {
             StdDraw.setPenColor(RED);
             StdDraw.filledCircle(x, y, radius);
      */      
             }

}
