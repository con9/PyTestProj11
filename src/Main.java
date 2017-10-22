import java.util.ArrayList;
import java.util.Vector;

public class Main {

	//sample inputs for test. Comment "inputarr = InputGen.parseFile();" for testing this input
	int[][] inputarr = new int[][]{
		{4,4},
		{4,8,7,3}, 
		{2,5,9,3},
		{6,3,2,5},
		{4,4,1,6}, //result should  be 9, 5, 3, 2, 1
};
//	int[][] inputarr = new int[][]{
//		{4,4}, 
//		{4,3,2,1}, 
//		{5,8,9,0}, 
//		{6,7,2,-1},
//		{-5,-4,-3,-2} // result should be 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4, -5
//	};

//	int[][] inputarr = new int[][]{
//		{4,4}, 
//		{4,0,2,1}, 
//		{5,8,7,0}, 
//		{6,7,-2,-1},
//		{-5,-4,-5,-2} // result should be 8, 7, 2, 1, 0, -1, -2, -5
//	};
	
	boolean isDebug = false;
	public static void main(String[] args) {
		
		//reads input from file inputFile1.txt. File is parsed in InputGen.java
		//x[0] holds the size of the array
		new Main().start();
	}
	
	
//enum to store direction North, South, East, West
	enum Dir{
		N(1), S(2), E(3), W(4);	
		private int value;  
		Dir(int value){  
		this.value=value;  
		}  
	}
	
	//variable to store ongoing contender for best path.
	public ArrayList<String> path = new ArrayList<String>();
	//size of the input array
	int xSize, ySize;
	//holds the result: the best path
	Vector<Integer> result = new Vector<Integer>();
	/**
	 * iterates over each element and finds the best path
	 */
	void start() {
		if(isDebug)
			System.out.println("start");
		inputarr = InputGen.parseFile();
		xSize = inputarr[0][0]; 
		ySize = inputarr[0][1];
		//start iterating over each item
		for(int i=1;i<=xSize;i++)
			for(int j=0;j<=ySize-1;j++) {
				if(isDebug)
					System.out.println("start for"+i+","+j);
				System.out.println("...."+i+","+j);
				path = new ArrayList<String>();
				traverse(i,j);
			}//151422
		System.out.println("result is"+result);
		System.out.println("end");
	}
	
	/**
	 * traverses in all possible directions, checks feasibility and finds the best path
	 * @param x current coordinate in x direction
	 * @param y current coordinate in y direction
	 */
	void traverse(int x, int y) {
		if(isDebug)
			System.out.println("traverse for "+x+","+y);
		boolean feasible = false;
		path.add(x+","+y);
		//any order to start with: n, e, s, w 
		feasible = checkFeasibility(x, y, Dir.N);
		if(feasible) {
//			path.add(x+","+y);
			//for North go up 
			traverse(x-1, y);
		}
		feasible = checkFeasibility(x, y, Dir.E);
		if(feasible) {
//			path.add(x+","+y);
			traverse(x, y+1);
		};
		feasible = checkFeasibility(x, y, Dir.S);
		if(feasible) {
//			path.add(x+","+y);
			traverse(x+1, y);
		}
		feasible = checkFeasibility(x, y, Dir.W);
		if(feasible) {
//			path.add(x+","+y);
			traverse(x, y-1);
		}
		if(!feasible) {
			//process the coordinates in path
			checkLengthAndAddIfLongest();
		}
		path.remove(x+","+y);
	}
	
	/**
	 * process the coords in path and assign to result if its the best route
	 */
	void checkLengthAndAddIfLongest() {
		if(isDebug)
			System.out.println("checkLengthAndAddIfLongest");
		Vector<Integer> tempResult = new Vector<Integer>();
		if(isDebug)
			System.out.print("temp vals:");
		for(String val:path) {
			String[] temp = val.split(",");
			int arrVal = inputarr[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])];
			if(isDebug)
				System.out.print(arrVal);
			if(isDebug)
				System.out.print(",");
			tempResult.add(arrVal);
		}
		if(isDebug)
			System.out.println();
		if(result.size()>1) {
			if(tempResult.size()>result.size()) {
				if(isDebug)
					System.out.println("*****new result found"+tempResult);
				result = tempResult;
			}else if(tempResult.size()==result.size()){
				int resultDiff = result.get(0)-result.get(result.size()-1);
				int tempResultDiff = tempResult.get(0)-tempResult.get(tempResult.size()-1);
				if(tempResultDiff>resultDiff) {
					result = tempResult;
					if(isDebug)
						System.out.println("*****new result found from steepness:"+tempResult);
				}
			}
		}else {
			result = tempResult;
			if(isDebug)
				System.out.println("*****set result:"+tempResult);
		}
	}
	
	/**
	 * check if the path in the dir is possible or not. Path is 
	 * possible only if the new coords are within the bounds and 
	 * smaller than the current coord value.
	 * @param x current x coord
	 * @param y current y coord
	 * @param dir dir to move in
	 * @return if the path is possible or not
	 */
	boolean checkFeasibility(int x, int y, Dir dir) {
//		System.out.println("checkFeasibility "+x+","+y+" "+dir.toString());
		int oldX=x, oldY=y;
		switch(dir) {
		case N:
			x-=1;
			break;

		case E:
			y+=1;
			break;

		case S:
			x+=1;
			break;

		case W:
			y-=1;
			break;
			
		}
		
		if(x>0&&y>=0 && x<=xSize&&y<=ySize-1) {
			if(inputarr[oldX][oldY]<=inputarr[x][y]) {
				return false;
			}
				
			if(path.contains(x+","+y)) {
				return false;
			}
			return true;
		}
		return false;
	}
}








