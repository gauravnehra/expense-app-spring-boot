package com.example.demo;

public class VarArgs {

	static void add(int ...a) {
		int sum = 0;
			for(int i : a) {
				sum+=i;
			}
			System.out.println(sum); 
	}
public static void main(String[] args) {
	// TODO Auto-generated method stub
	add();
	add(10,20);
	add(1,2,3);
	add(new int[] {1,2,3,4,5,6,7,8,9});

}

}