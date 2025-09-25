// this file is provided

#include <iostream>
#include "functions.h"


using std::cin, std::cout, std::endl;


// int main() {
// 	bool quit = false;
// 	// Note: we use long long because the input number can exceed the system max limit
// 	// for what an int can hold
// 	long long a = 0;
// 	long long b = 0;
// 	while (!quit) {
// 		std::cout << "Enter range: ";
// 		std::cin >> a >> b;
// 		if(a == 0) {
// 			quit = true;
// 			break;
// 		}
// 		while(!is_valid_range(a, b)) {
// 			std::cout << "Invalid Range" << std::endl;
// 			std::cout << "Enter range: ";
// 			std::cin >> a >> b;
// 		}
// 		plateaus_and_basins count = count_pb_numbers(a, b);
// 		std::cout << "There are " << count.plateaus << " plateaus"
// 	              << " and " << count.basins << " basins"
// 	              << " between " << a << " and " << b << "." << std::endl;
// 	}
// 	return 0;
// }

bool is_valid_range(long long a, long long b) {
	// TODO(student): return true if and only if 1,000 <= a <= b < 1,000,000,000,000
	if (a >= 1000 && a <= b && b < 1000000000000) {
		return true;
	}
	return false;
}

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
	cout << "step ";
	cout << "number ";
	cout << "q ";
	cout << "r" << endl;
	int step = 1;
	// TODO(student): Determine whether number is a plateau: this is the key loop
	while (temp != 0) {
		cout << step << " ";
		cout << temp << " ";
		long long q = temp / 10;
		cout << q << " ";
		long long remainder = temp % 10;
		cout << remainder<< endl;
		step++;
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

int main() {
	// bool quit = false;
	// // Note: we use long long because the input number can exceed the system max limit
	// // for what an int can hold
	// long long a = 0;
	// long long b = 0;
	// while (!quit) {
	// 	std::cout << "Enter range: ";
	// 	std::cin >> a >> b;
	// 	if(a == 0) {
	// 		quit = true;
	// 		break;
	// 	}
	// 	while(!is_valid_range(a, b)) {
	// 		std::cout << "Invalid Range" << std::endl;
	// 		std::cout << "Enter range: ";
	// 		std::cin >> a >> b;
	// 	}
	// 	plateaus_and_basins count = count_pb_numbers(a, b);
	// 	std::cout << "There are " << count.plateaus << " plateaus"
	//               << " and " << count.basins << " basins"
	//               << " between " << a << " and " << b << "." << std::endl;
	// }

	// cout << "Input a" << endl;
	long long a = 8675309;
	// cin >> a;
	// cout << "Input b" << endl;
	// long long b;
	// cin >> b;

	classify_geo_type(a);

	return 0;
}