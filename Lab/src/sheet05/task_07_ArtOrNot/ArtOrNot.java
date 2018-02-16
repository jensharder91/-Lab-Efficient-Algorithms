package sheet05.task_07_ArtOrNot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArtOrNot {
	
	static int [][] Matrix= new int[16][16];
	static boolean possible;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// metadata
		char []line= new char[18];
		int temp;
		int [][] Null_Matrix =new int[16][16];
		int NumberChanges=0;
		int min=Integer.MAX_VALUE;
		possible=false;
		
		for (int i=0; i<16; i++){
			temp=br.read(line, 0, 18);	
			for (int j=0; j<16; j++){	
				Null_Matrix[i][j]=0;
				if(line[j] == '#')
					Matrix[i][j]=0;
				else if(line[j]=='O')
					Matrix[i][j]=1;
				else //should never happen
					Matrix[i][j]=9;
			}
		}
		
		//guess first line
		for (int guess=0; guess<65535; guess++ ){
			String binary = Integer.toBinaryString(guess);
			NumberChanges=CountChanges(binary);
			if (possible && min>NumberChanges)
				min=NumberChanges;
		}
		
		if (min<Integer.MAX_VALUE)
			System.out.println(min);
		else
			System.out.println("-1");
	}
	
	
	
	public static int CountChanges(String start){
		int[] prevLine=new int[16];
		int[] thisLine= new int[16];
		int[] nextLine= new int[16];
		int count=0;
		
		//set first line and count
		for(int i=0; i< start.length(); i++){
			if(start.charAt(i)==49)
			thisLine[(15-start.length())+i+1]=1;
		}
		
		for (int i=0; i<16; i++){
			//set bits on/off on right and left
			for(int j=0; j<16; j++){
			//	System.out.print(thisLine[j]);
				if(thisLine[j]==1)
					// Number of changes
					count++;
			}
		//	System.out.println();
			
			for(int j=0; j<16; j++){
				if(thisLine[j]==1){
					nextLine[j]=XOR(nextLine[j], thisLine[j]);
					if (j>0)
						nextLine[j-1]=XOR(nextLine[j-1], thisLine[j]);
					if(j<15)
						nextLine[j+1]=XOR(nextLine[j+1], thisLine[j]);
				}
			}
			for(int j=0; j<16; j++){
				// set nextLine according to pervLine
				nextLine[j]=XOR(nextLine[j],prevLine[j]);
				// compare to real picture
				nextLine[j]=XOR(Matrix[i][j],nextLine[j]);
				// now nextLine has all bits to change for the next row
			}			
			
			// set the Lines right
			for(int j=0; j<16; j++){
				prevLine[j]=thisLine[j];
			}
			for(int j=0; j<16; j++){
				thisLine[j]=nextLine[j];
				nextLine[j]=0;
			}
			
		}
		// now this line contains all bits to change in the 17^th row which does not exist
		possible=true;
		for (int i=0; i<16; i++){
			if (thisLine[i]==1)
				possible=false;
		}
		return count;
	}
	

	public static int XOR (int x, int y){
		if (x==1){
			if (y==1)
				return 0;
			else
				return 1;
		}
		else {
			if (y==1)
				return 1;
			else
				return 0;
		}
	}
}

