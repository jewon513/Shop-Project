package com.biz.test;

import java.util.ArrayList;
import java.util.List;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int sum;
		List<Integer> ret = new ArrayList<Integer>();
		int[] prices = {1,2,3,2,3};
		for(int i = 0 ; i < prices.length ; i++ ) {
			sum = 0;
			for(int j =i + 1 ; j < prices.length ; j++) {
				if(prices[i] <= prices[j]) {
					sum ++;
//					if(prices[i] == prices[j]) continue;
					
				}
				
			}
			ret.add(sum);
			
		}
		
		Object[] answer = ret.toArray();
		for(Object i : answer) {
			
			System.out.println(i);
		}
		for(Integer i : ret) {
			System.out.print(" " + i);
		}
	}

}
