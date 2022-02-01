/**
 * Code by Jeffrey Daniel
 * CWID: 10469475
 * Stevens Institute of Technology
 * Hoboken, New Jersey
 * 
 * CPE 593 Data Structures and Algorithms
 * Prof. Dov Kruger
 * 
 * Version 1.1: last edit: 01 FEB 2022 3:17 PM
 * Bugs and issues: None known
 * Future ideas: check for Carmichael numbers? 
 * 
 * Acknowledgements:
 * Certain code/ideas taken from/inspired by:
 * https://www.geeksforgeeks.org/fermats-little-theorem/
 * https://www.geeksforgeeks.org/primality-test-set-2-fermet-method/
 * https://www.geeksforgeeks.org/biginteger-class-in-java/
 * https://www.geeksforgeeks.org/bigdecimal-class-java/
 * https://careerkarma.com/blog/java-math-random/
 * Prof Kruger's class notes
 * */

import java.util.*;
import java.math.*;
import java.lang.Math.*;
public class fermatBigInteger{
	public static void main(String[] args){
		BigInteger num, numOfTrials;
		Scanner sc = new Scanner(System.in);
		System.out.print("Input the number to be checked: ");
		num = new BigInteger(sc.next());
		System.out.print("\nInput the number of trials to be conducted (higher is better, but may result in degraded performance): ");
		numOfTrials = new BigInteger(sc.next());
		String output = ferm(num, numOfTrials)?"\nPRIME":"\nNOT PRIME";
		System.out.println(output);
	}
	
	private static boolean ferm(BigInteger number, BigInteger trials){
		for(BigInteger i = BigInteger.ONE; i.compareTo(trials)<0; i = i.add(BigInteger.ONE)){
			BigInteger randa = randomBigInt(number);
			if(powermod(randa, number.subtract(BigInteger.ONE),number).compareTo(BigInteger.ONE)!=0)
				return false;
		}
		return true;
	}
	
	private static BigInteger randomBigInt(BigInteger num){
		BigDecimal randDec = new BigDecimal(Math.random());
		BigDecimal number = new BigDecimal(num.toString());
		randDec = randDec.multiply(number.subtract(BigDecimal.ONE).subtract(BigDecimal.ONE)).add(BigDecimal.ONE);
		BigInteger randInt = randDec.toBigInteger();
		return randInt;
	}
		
	private static BigInteger powermod(BigInteger a, BigInteger b, BigInteger m){
		BigInteger prod = new BigInteger("1");
		while(b.compareTo(BigInteger.ZERO)==1){
			if(((b.mod(new BigInteger("2"))).compareTo(BigInteger.ZERO))!=0)
				prod = (prod.multiply(a)).mod(m);
			a = (a.multiply(a)).mod(m);
			b = b.divide(new BigInteger("2"));
		}
		return prod;
	}	
}
