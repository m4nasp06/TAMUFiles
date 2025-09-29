// this file is provided

#ifndef FUNCTIONS_H
#define FUNCTIONS_H

struct plateaus_and_basins {
    int plateaus;
    int basins;
};

bool is_valid_range(long long a, long long b);
char classify_geo_type(long long number);
char classify_geo_type_adj(long long number);
static inline bool adjacents(unsigned long long number);
static inline long long next_adjacent(long long number);
plateaus_and_basins count_pb_numbers(long long a, long long b);

#endif  // FUNCTIONS_H