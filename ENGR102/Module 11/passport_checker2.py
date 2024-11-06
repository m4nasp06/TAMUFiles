# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   11-10 Passport Checker 2
# Date:         04 11 2024
#


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

# file_path = input("Enter the name of the file: ")

# c:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 11/scanned_passports.txt

# input_file_path = 'C:/Users/sripa/Desktop/TAMUFiles/ENGR102/Module 11/scanned_passports.txt'
input_file_path = input("Enter the name of the file: ")

valid_passports = []

# call is_valid for each passport in passports, and add to valid_passports if valid
passports = get_passports(input_file_path)
for passport in passports:
    if is_valid(passport):
        valid_passports.append(passport)

# print("There are", len(valid_passports), "valid passports")
# function to write valid passports to file, taking in valid passports, use start_index and end_index to write to file
def write_valid_passports(valid_passports, output_file, input_file):
    with open(input_file, 'r') as f:
        with open(output_file, 'w') as f_out:
            lines = f.readlines()
            for passport in valid_passports:
                f_out.write("".join((lines[passport['start_index']:passport['end_index']])))





# writing_file = 'C:/Users/sripa/Desktop/TAMUFiles/ENGR102/Module 11/valid_passports.txt'
# writing_file = 'valid_passports.txt'
# write_valid_passports(valid_passports, writing_file , input_file_path)

# byr – Birth year – four digits, between 1920 and 2008, inclusive
# iyr – Issue year – not required
# eyr – Expiration year – four digits, between 2024 and 2034, inclusive
# hgt – Height – a number followed by either cm or in
# If cm, the number must be between 150 and 193, inclusive
# If in, the number must be between 59 and 76, inclusive
# hcl – Hair color – a # followed by exactly 6 characters (0-9 or a-f)
# ecl – Eye color – exactly one of the following: amb, blu, brn, gry, grn, hzl, oth
# pid – Passport ID – a nine-digit number, including leading zeroes
# cid – Country ID – a three-digit number, NOT including leading zeroes

valid2 = []
def verify(passports):
    for passport in passports:
        hair_color_allowed = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f']
        birth_str = passport['byr']
        birth_year = int(passport['byr'])
        exp_str = passport['eyr']
        exp_year = int(passport['eyr'])
        height = passport['hgt']
        hair_color = passport['hcl']
        eye_color = passport['ecl']
        pid = passport['pid']
        cid = passport['cid']
        if len(birth_str) != 4 or 1920 > birth_year or birth_year > 2008:
            continue
        if len(exp_str) != 4 or 2024 > exp_year or exp_year > 2034:
            continue
#       check to see if height is in cm or in, if cm or inch string is not present, continue
        if height[-2:] == 'cm':
            height_cm = int(height[:-2])
            if 150 > height_cm or height_cm > 193:
                continue
        elif height[-2:] == 'in':
            height_in = int(height[:-2])
            if 59 > height_in or height_in > 76:
                continue
        else:
            continue
        if hair_color[0] != '#' or len(hair_color) != 7 or hair_color[1] not in hair_color_allowed or hair_color[2] not in hair_color_allowed or hair_color[3] not in hair_color_allowed or hair_color[4] not in hair_color_allowed or hair_color[5] not in hair_color_allowed or hair_color[6] not in hair_color_allowed:
            continue
        if eye_color not in ['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth']:
            continue
        if len(pid) != 9:
            continue
        if len(cid.lstrip('0')) != 3:
            continue
        valid2.append(passport)


verify(valid_passports)
print("There are", len(valid2), "valid passports")

# path: C:/Users/sripa/Desktop/TAMUFiles/ENGR102/Module 11/valid_passports2.txt
# writing_file2 = 'C:/Users/sripa/Desktop/TAMUFiles/ENGR102/Module 11/valid_passports2.txt'
writing_file2 = 'valid_passports2.txt'
write_valid_passports(valid2, writing_file2, input_file_path)




