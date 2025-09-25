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

char classify_geo_type(long long number) {
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

// returns how many numbers in the range [a, b] are plateaus and basins
plateaus_and_basins count_pb_numbers(long long a, long long b) {
	int number_of_plateaus = 0;
	int number_of_basins = 0;

	// TODO(student): count plateaus and basins in the range [a,b]
	for(long long x =a; x <= b; x++) {
		char type = classify_geo_type(x);
		if (type == 'p') number_of_plateaus++;
		else if (type == 'b') number_of_basins++;
	}

	return {number_of_plateaus, number_of_basins};
}