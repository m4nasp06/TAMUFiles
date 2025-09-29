#include <iostream>
#include <iomanip>
#include <string>
#include <vector>
#include "rank_functions.h"

// returns a copy of the input string with whitespace removed from the front and back
std::string trim(std::string s) {
    // TODO(student)
    // using isspace from std::isspace
//      " pig  ";
// size  123456
// index 012345
    size_t start = 0;
    while(start < s.size() && std::isspace(static_cast<unsigned char>(s[start]))) {
        start++;
    }
    size_t end = s.size();
    while(end > start && std::isspace(static_cast<unsigned char>(s[end - 1]))) {
        end--;
    }
    if (start < end) {
        return s.substr(start, end - start);
    }

    return "";
}


// Helper Functions
// Country Validation
bool valid_country(std::string country) {
    if (country.size() != 3) return false;

    for (size_t i = 0; i < 3; i++) {
        if (!std::isupper(static_cast<unsigned char>(country[i]))) {
            return false;
        }
    }
    return true;
}
// Last Name Validation
bool valid_name(std::string name) {
    // can only contain letters and spaces, and must be atleast 2 letters
    size_t letters = 0;
    for (size_t i = 0; i < name.size(); i++) {
        if (name[i]==' ') continue;
        if (!std::isalpha(static_cast<unsigned char>(name[i]))) {
            return false;
        }
        letters++;
    }    
    return letters >= 2;
}



// load data from standard input into a vector
//   input format := <time> <country> <number> <name>
//   examples:
//     32.7 AUS 81 Piastri
//     36.5 NED 1  Verstappen
//   rank should be initialized to 0 for each driver
// returns a vector of drivers, or an empty vector if any input is invalid
std::vector<driver> load_driver_data() {
    // TODO(student)
    std::vector<driver> drivers;

    while(true) {
        double time;
        std::string country;
        unsigned int number;

        if (!(std::cin >> time >> country >> number)) {
            if (std::cin.eof()) {
                break; // end of input
            }
            return {}; // invalid input

        }

        std::string line;
        std::getline(std::cin, line);
        std::string name = trim(line);

        // check conditions
        if (time <= 0) return {};
        if (!valid_country(country)) return {};
        if (number > 99 || number < 1) return {};
        if (!valid_name(name)) return {};
        
        drivers.push_back(driver{name, country, number, time, 0});

    }
    
    return drivers;
}

// returns a copy of the input vector with ranks set based on the time for each driver.
//   the fastest/minimum time is ranked 1
// the order of the elements in the vector should not be changed
std::vector<driver> set_rankings(std::vector<driver> drivers) {
    // TODO(student)
    //  Repeat n times:
    // Scan the vector to find the smallest time among drivers whose rank == 0 (unranked).
    // Give that driver the next rank (1, 2, 3, ...).
    // Repeat until all are ranked.

    size_t n = drivers.size();
    if (n == 0) return drivers;

    for (unsigned r = 1; r <= n; r++) {
        int best_index = -1;
        double best_time = 0;
        // sample [32.7, 36.5, 21.5]
        // sample [0 ,0, 0]
        for(size_t i = 0; i < n; i++) {
            if (drivers[i].rank == 0) {
                if(drivers[i].time < best_time || best_index == -1) {
                    best_index = static_cast<int>(i);
                    best_time = drivers[i].time;                    
                }
            }
        }
        if (best_index != -1) {
            drivers[best_index].rank  = r;
        }        
    }
    return drivers;
}

// print the results of the race
void print_results(const std::vector<driver>& drivers) {
    // get the fastest time
    double best_time;
    for (const driver& driver : drivers) {
        if (driver.rank == 1) {
            best_time = driver.time;
            break;
        }
    }

    std::cout << "Final results!";
    std::cout << std::setprecision(2) << std::showpoint << std::fixed << std::endl;
    for (unsigned rank = 1; rank <= drivers.size(); rank++) {
        for (const driver& driver : drivers) {
            if (driver.rank == rank) {
                std::string rank_str = "["+std::to_string(rank)+"]";
                std::cout << std::setw(4) << std::left << rank_str << " " << driver.time << " " << std::setw(15) << std::left << driver.lastname << " (" << driver.country << ") +" << (driver.time - best_time) << std::endl;
            }
        }
    }
}
