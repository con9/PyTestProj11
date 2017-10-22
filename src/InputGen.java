import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputGen {

	static int[][] parseFile() {
		BufferedReader br = null;
		int[][] arr = null;
		try {
			br = new BufferedReader(new FileReader("./src/inputFile1.txt"));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    	int x = 0;
		    while (line != null) {
		    		if(arr == null) {
		    			String[] val = line.split(" ");
		    			arr = new int[Integer.parseInt(val[0])+1][Integer.parseInt(val[1])];
		    		}

	    			parseX(arr, x, line);
		        line = br.readLine();
	    			x++;
		    }
		}
		catch(Exception e){
			
		}finally {
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return arr;
	}
	static void parseX(int[][] arr, int x, String line) {
		String[] val = line.split(" ");
		for(int i=0; i<val.length; i++) {
			arr[x][i] = Integer.parseInt(val[i]);
		}
		
	}
	
}
