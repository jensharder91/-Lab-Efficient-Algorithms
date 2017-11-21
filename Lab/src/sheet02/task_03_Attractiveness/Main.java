package sheet02.task_03_Attractiveness;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int player;
		int [] index;
		String attributes;
		
		while (true) {
			
			player = scanner.nextInt();
			
			if(player == 0) {
				break;
			}
			
			int Matrix [][][] =new int [26][26][26];
			int number=0;
			String [][] Spieler_Attr= new String [player][6];
			attributes = scanner.nextLine();
			for (int i=0; i< player; i++){
				attributes = scanner.nextLine();
				Spieler_Attr[i]= attributes.split(" ");
				for (int j=0; j<6; j++){
					index = AttrToIndex(Spieler_Attr[i][j]);
					Matrix [index[0]][index[1]][index[2]]++;
					if(Matrix [index[0]][index[1]][index[2]] > number)
						number = Matrix [index[0]][index[1]][index[2]];
				}
			}
			if (number == 1)
				System.out.println(player);		
			else
				System.out.println(number);		
		}
		scanner.close();
	}
	public static int [] AttrToIndex(String s){
		int [] indizes= new int[s.length()];
		int [] value={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		char [] key = {'a','b','c','d','e','f','g','h','i','j','k','l',
				'm','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            for (int j = 0; j < key.length; j++) {
                if (current == key[j]) {
                    indizes[i]=value[j];
                    break;
                }
            }
        }
		return indizes;
	}
	
}
