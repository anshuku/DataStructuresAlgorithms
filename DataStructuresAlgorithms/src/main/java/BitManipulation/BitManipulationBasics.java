package BitManipulation;

/*
 * Bitwise left shift and right shift operators
 * 
 * 
 * For any positive representation of a number in binary 2's complement 
 * N the negative counterpart -N is pretty simple to find, just invert the bits, and add one. 
 * Example: 7 in 2's complement is 0111, inverting these bits gives: 1000, 
 * adding one gives 1001, which is -7 in 2's complement!
 * 
 * For any positive representation of a number in binary 1's complement 
 * N the negative counterpart -N is pretty simple to find, just invert the bits, and add one. 
 * Example: 7 in 2's complement is 0111, inverting these bits gives: 1000, 
 * adding one gives 1001, which is -7 in 2's complement!
 */
public class BitManipulationBasics {

	public static void main(String[] args) {

		int a = 5;//101
		System.out.println(a >> 1);
		System.out.println(a >> 2);
		System.out.println(a >> 3);
		System.out.println(a >> 32);
		System.out.println(a >> 31);
		System.out.println();

//		int b = -3;//...011 -> ...100 -> ...101 = -2^2+2^0 = -3
//		System.out.println(b << 1);//1010 = -6
//		System.out.println(b << 2);//10100 = -12
//		System.out.println(b << 3);//101000 = -24
//		System.out.println(b << 32);// = -3
//		System.out.println(b << 31);// = big number
//		System.out.println();

//		int c = -4;//...0100 -> ...1011 -> ...1100
//		
		int x = -5;// ...0101 -> ...1010 -> ...1011
		// ...0101 -> ...1010 -> ...1011 = -2^3 + 2^1 + 2^0 = -5
		// ...1011 -> ...0100 -> ...0101 = 2^2 + 2^0 = 5
		System.out.println(x >> 1);// ...1101
		System.out.println(x >> 2);// 1
		System.out.println(x >> 3);// 0
		System.out.println(x >> 4);
		System.out.println(x >> 32);
		System.out.println(x >> 31);
		System.out.println();

		int y = -5;
		System.out.println(y >>> 1);
		System.out.println(y >>> 2);
		System.out.println(y >>> 32);
		System.out.println(y >>> 31);
		System.out.println(y >>> 30);
		System.out.println(y >>> 29);

		// ...101100 -> -2^5+2^3+2^2 = -32+8+4 = - 20
		// ...101100 -> ...010011 -> ...010100 = 2^4+2^2 = 20

		// Negative float in binary to decimal

		// 101.1011
		// 2's complement
		// Left most bit(LMB) is telling it's a negative number
		// -2^2 + 2^0 = -3 for the integer part (left-side of the decimal point)
		// 2^-1 + 2^-3 + 2^-4 = 0.5+0.125+0.0625 =0.6875
		// so 101.1011 = -3 + 0.6875 = -2.3125

		// 1's complement
		// Left most bit(LMB) is telling it's a negative number
		// Invert every bit and take left most bit as sign bit(sign value)
		// 101.1011 -> -10.0100
		// -2^1 + 2^0 = -2 for the integer part (left-side of the decimal point)
		// .0100 = 2^-2 = 0.25
		// so 101.1011 -> -10.01 = -2 + 0.25 = -2.25

	}

}
