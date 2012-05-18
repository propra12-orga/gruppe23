
public class Hulk extends Map{

	public Hulk(int x, int y){
		super(false, 0, 0);		
	}
	
	public void testPos(int PosX, int PosY){
		int TestWert = map[PosX][PosY];
		
		if(TestWert == 2){
			super(true, PosX, PosY);			
		}
		else{}
	}
	
}
