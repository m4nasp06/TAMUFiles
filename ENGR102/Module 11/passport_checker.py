# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   11-9 Passport Checker
# Date:         04 11 2024
#


# 'C:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 11/valid_passports.txt'
# 'C:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 11/scanned_passports.txt'
def get_passports(file_path):
    passports = []
    with open(file_path, 'r') as f:
        passport = {}
        start_line = 0
        i = 0
        for line in f:
            if line == '\n':
                passport['start_index'] = start_line
                passport['end_index'] = i

                passports.append(passport)
                start_line = i
                passport = {}
            else:
                for field in line.strip().split():
                    key, value = field.split(':')
                    passport[key] = value
            i += 1

        passport['start_index'] = start_line
        passport['end_index'] = i
        passports.append(passport)
    return passports


def is_valid(passport):
    required_fields = ['byr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid', 'cid']
    for field in required_fields:
        if field not in passport:
            return False
    return True

input_file_path = input("Enter the name of the file: ")
# input_file_path = 'C:/Users/sripa/Desktop/TAMUFiles/ENGR102/Module 11/scanned_passports.txt'
# c:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 11/scanned_passports.txt

# input_file_path = input("Enter the name of the file: ")

valid_passports = []

# call is_valid for each passport in passports, and add to valid_passports if valid
passports = get_passports(input_file_path)
for passport in passports:
    if is_valid(passport):
        valid_passports.append(passport)

print("There are", len(valid_passports), "valid passports")
# function to write valid passports to file, taking in valid passports, use start_index and end_index to write to file
def write_valid_passports(valid_passports, output_file, input_file):
    with open(input_file, 'r') as f:
        with open(output_file, 'w') as f_out:
            lines = f.readlines()
            for passport in valid_passports:
                f_out.write("".join((lines[passport['start_index']:passport['end_index']])))





# writing_file = 'C:/Users/sripa/Desktop/TAMUFiles/ENGR102/Module 11/valid_passports.txt'
writing_file = 'valid_passports.txt'
write_valid_passports(valid_passports, writing_file , input_file_path)







