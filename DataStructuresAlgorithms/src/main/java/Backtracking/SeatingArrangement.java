package Backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SeatingArrangement {

	public static void main(String[] args) {
		
		String [] arr = {"B1", "B2", "G1"};
		List<String> resultList = new ArrayList<>();
		
		for(int i = 0; i< arr.length; i++) {
			Stack<String> s =  new Stack<>();
			s.push(arr[i]);
			StringBuilder str = new StringBuilder();
			int j = i;
			while(!s.isEmpty()) {
				String val = s.pop();
				str.append(val);
				
				//if()
				
			}
		}
		

	}

}
