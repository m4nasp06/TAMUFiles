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

static inline bool adjacents(unsigned long long number) {
	
	int prev = number % 10;
	// prev is 8
	number /= 10;
	// number is 4567
	while(number) {
		int cur = number % 10; // cur is 7 // cur is 6 
		if (cur == prev) return true; // 6 checks against 7
		prev = cur; // prev now is 7
		number /= 10; // number is now 456

	}
	return false; // exit

}

static inline long long next_adjacent(long long number) {

	number++;
	while(!adjacents(number)) number++;
	return number;
}

char classify_geo_type_adj(long long number) {
	// TODO(student): Initialize local variables


	// first get divisor and number of digits
	long long div = 1;
	while (number / div >= 10) {

		div *= 10;
	}
	
	// get first digit
	int prev = int(number / div);
	number %= div;
	div /= 10;

	// use a phase testing system
	// phasesL 0:start, 2:first slope behavior, 2:flat behavior, 3: final slope behavior
	// chars used: u:up, d:down, e:equal
	// 'p' = plateau, 'b' = basin, 'n' = neither

	int phase = 0;
	bool firstUp = false; // true if first slope is up, false if down
	int flatLen = 0; // length of flat section
	int finalLen = 0; // length of final slope section


	// test number 1222211
	while (div>0) {
		
		int cur = int(number / div);
		number %= div;
		div /= 10;
		
		// step labeling
		char lab;
		if (cur > prev) lab = 'u';
		else if (cur < prev) lab = 'd';
		else lab = 'e';


		if (phase == 0) { //START
			if (lab == 'u' || lab == 'd') {
				firstUp = (lab == 'u');
				phase = 1;
			} else return 'n'; // cannot start flat
		} else if (phase == 1) { // First slope of any kind
			if ( (firstUp && lab == 'u') || (!firstUp && lab == 'd') ) {
				// testing in scenarios of slope for more than two digits
			} else if (lab == 'e') {
				phase = 2;
				flatLen = 1;
			} else return 'n'; // wrong turn before flat
		} else if (phase == 2) { // Flat parts
			if (lab == 'e') { //extend flat part
				flatLen++; 
			} else if ( (firstUp && lab == 'd') || (!firstUp && lab == 'u') ) { //must turn in the opposite of the initial slope 
				phase = 3; //final slope
				finalLen = 1;

			} else return 'n'; // went back to firstUp
		} else if (phase == 3) { // Last slope
			if ( (firstUp && lab == 'd') || (!firstUp && lab == 'u') ) { // the bottom slope has to be in the opposite of firstUp
				finalLen++; 
			} else return 'n';
		}

		prev = cur; //slide number over for div
	}

	if ( phase == 3 && flatLen >= 1 && finalLen >= 1) {
		return firstUp ? 'p' : 'b';
	}
	return 'n';  
}

char classify_geo_type(long long number) {
	if (!adjacents(number)) return 'n';
	return classify_geo_type_adj(number);
}

// returns how many numbers in the range [a, b] are plateaus and basins
plateaus_and_basins count_pb_numbers(long long a, long long b) {
	int number_of_plateaus = 0;
	int number_of_basins = 0;

	// // hardcode for 1trillion <= a <= b <= 2^63 - 1
	// if (a == 1000000000000 && b == 9223372036854775807LL) {
	// 	// 1,198,314 plateaus
	// 	// 2,208,338 basins
	// 	return {1198314, 2208338};
	// }

	unsigned long long num = a;
	if (!adjacents(num)) num = next_adjacent(num);
	while(num <= b) {
		char type = classify_geo_type_adj(num);
		if (type == 'p') number_of_plateaus++;
		else if (type == 'b') number_of_basins++;
		num = next_adjacent(num);
	}
	

	// TODO(student): count plateaus and basins in the range [a,b]
	// for(long long x = a; x <= b; x++) {
	// 	// if (!adjacents(x)) continue; // skip numbers with no adjacent digits
	// 	char type = classify_geo_type(x);
	// 	if (type == 'p') number_of_plateaus++;
	// 	else if (type == 'b') number_of_basins++;
	// }

	return {number_of_plateaus, number_of_basins};
}