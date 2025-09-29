#include <iostream>
#include "rank_functions.h"

int main() {
    // TODO(student): create and load driver data into a vector of drivers using load_driver_data()
    std::vector<driver> drivers = load_driver_data();


    // TODO(student): if loading driver data failed,
    //                1) print "Bad input" to standard output
    //                2) exit the program by returning 1
    if (drivers.size() == 0) {
        std::cout << "Bad input" << std::endl;
        return 1;
    }

    // TODO(student): set the rankings of the drivers using set_rankings()
    drivers = set_rankings(drivers);


    // TODO(student): print the results using print_results()
    print_results(drivers);

    return 0;
}
