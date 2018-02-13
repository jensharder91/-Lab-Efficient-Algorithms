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
		for (int guess=0; guess<65536; guess++ ){
			String binary = Integer.toBinaryString(guess);
			NumberChanges=CountChanges(binary);
			if (possible)
				break;
		}
		
		if (possible)
			System.out.println(NumberChanges);
		else
			System.out.println("-1");
	}
	
	
	
	public static int CountChanges(String start){
		int[] prevLine=new int[16];
		int[] thisLine= new int[16];
		int[] nextLine= new int[16];
		int count=0;
		char[] temp=new char[16];
		temp=start.toCharArray();
		
		//set first line and count
		for(int i=0; i<temp.length; i++){
			if(temp[i]==1){
				thisLine[i]=1;
			}
		}
		
		for (int i=0; i<16; i++){
			//set bits on/off on right and left
			if(thisLine[i]==1 )
			// Number of changes
			count++;
			for(int j=0; j<16; j++){
				if(thisLine[j]==1){
					nextLine[i]=XOR(nextLine[i], thisLine[i]);
					if (i>0)
						nextLine[i-1]=XOR(nextLine[i-1], thisLine[i]);
					if(i<15)
						nextLine[i+1]=XOR(nextLine[i+1], thisLine[i]);
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
			prevLine=thisLine;
			thisLine=nextLine;
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

