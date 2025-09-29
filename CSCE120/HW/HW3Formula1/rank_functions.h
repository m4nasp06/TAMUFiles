// this file is provided.  do not submit.
#ifndef RANKFUNCTIONS_H
#define RANKFUNCTIONS_H

#include <string>
#include <vector>

struct driver {
    std::string lastname = "";
    std::string country = "";
    unsigned int number = 0;
    double time = 0.0;
    unsigned int rank = 0;
};

std::vector<driver> load_driver_data();
std::string trim(std::string);
std::vector<driver> set_rankings(std::vector<driver>);
void print_results(const std::vector<driver>&);
bool valid_country(std::string);
bool valid_name(std::string);


#endif
