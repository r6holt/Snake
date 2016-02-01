import java.util.*;

public class Snake {
	int length;
	ArrayList<Integer> row = new ArrayList<Integer>();
	ArrayList<Integer> col = new ArrayList<Integer>();
	boolean food = false;
	
	public Snake() {
		length=1;
	}
	
	public void food() {
		length++;
		food=true;
		
	}
	
	public ArrayList<Integer> getR() {
		return row;
	}
	
	public ArrayList<Integer> getC() {
		return col;
	}
	
	public void add(int r, int c) {
		row.add(0,r);
		col.add(0,c);
		if(food==false) {
			row.remove(row.size()-1);
			col.remove(col.size()-1);
		}
		food=false;
	}
	
	public int getLength() {
		return length;
	}
}
