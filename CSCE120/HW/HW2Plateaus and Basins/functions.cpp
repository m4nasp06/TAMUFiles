#include <iostream>
#include "functions.h"
using std::cin, std::cout, std::endl;

// returns the boolean value true if and only if 1,000 <= a <= b < 1,000,000,000,000
bool is_valid_range(long long a, long long b) {
	// TODO(student): return true if and only if 1,000 <= a <= b < 1,000,000,000,000
	if (a >= 1000 && a <= b && b < 1000000000000) {
		return true;
	}
	return false;
}

// returns the 'p' if number is a plateau, 'b' if number is a basin, and 'n' if number is neither
// Digits in numbers representing plateaus increase monotonically first, then remain constant for at least two occurrences and finally decrease monotonically.
// For basins, they decrease monotonically first, then remain constant for at least two occurrences and finally increase monotonically.
// You have been hired to write a program that, given two integers a and b, determines how many plateaus and basins exist in the range between a and b, inclusive.

// Examples of plateaus and basins:
// 1220 has 1 < 2, 2 = 2, and 2 > 0, so it is a plateau. (by the way 1220 is the smallest plateau)
// 1001 has 1 >0, 0 = 0, and 0 < 1, so it is a basin. (by the way 1001 is the smallest basin)
// 75224 has 7 > 5, 5 > 2, 2 = 2 and 2 < 4, so it is a basin. (note the up slope and down slopes do not have to be equal)
// Examples of neither plateau nor basin:
// 124424 follows neither pattern. Note 12442 would have been a plateau, but the last digit 4 breaks the up, same, down pattern
// 112344321 is neither. At the beginning of the number (11), the two digits are not monotonically increasing.
// 120 is neither. It increases first and then decreases, but doesn’t stay constant between the two patterns.   But also, having only 3 digits, it cannot be either a plateau or a basin.
// 1234 is neither. It is just increasing.
// Between a = 1000 and b = 1020, there are 0 plateaus and 9 basins.
// The numbers 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009 are basins.
// The rest of the numbers are neither plateaus nor basins.
// Between a = 1200 and b = 1220, there are 1 plateau and 0 basins.
// The number 1220 is a plateau.
// The rest of the numbers are neither plateaus nor basins.
// Between a = 100000 and b = 200000, there are 1634 plateaus and 129 basins.

// basically in any given number, we're looking for two numbers that are the same, but the number before it is higher, and then the number after is higher, for a basin, and opposite for a plateau
// find plateau algorithm, and invert basically for a basin
// we can only use number peeling/slicing, no strings or arrays


char classify_geo_type(long long number) {
	// TODO(student): Initialize local variables
	// know this number could have 4 to 12 digits

// 	You might like to know of this interesting technique called "number peeling" or “number slicing”.  Given a decimal (base-10) number like 8675309, we can read each digit, one-by-one as follows:
// let number be 8675309
// while number is not 0 do
//   divide number by 10 to get a quotient q and a remainder r
//   print r
//   set number to q 
// end-while

	long long temp = number;
	cout << "step";
	cout << "number";
	cout << "q";
	cout << "r";
	int step = 0;
	// TODO(student): Determine whether number is a plateau: this is the key loop
	while (temp != 0) {
		
		long long remainder = temp % 10;
		temp /= 10;
		
	}

	// TODO(student): Determine whether number is a basin
	//                If you get the logic for plateau right, minor tweaks can create the code for basins.

	// TODO(student): return the appropriate char, p for plateau, b for basin, n for neither
	return 'x';
}

// returns how many numbers in the range [a, b] are plateaus and basins
plateaus_and_basins count_pb_numbers(long long a, long long b) {
	int number_of_plateaus = 0;
	int number_of_basins = 0;

	// TODO(student): count plateaus and basins in the range [a,b]

	return {number_of_plateaus, number_of_basins};
}