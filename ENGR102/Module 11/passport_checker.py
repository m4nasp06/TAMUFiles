# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   11-9 Passport Checker
# Date:         04 11 2024
#


def get_passports(file_path):
    passports = []
    with open(file_path, 'r') as f:
        passport = {}
        for line in f:
            if line == '\n':
                passports.append(passport)
                passport = {}
            else:
                for field in line.strip().split():
                    key, value = field.split(':')
                    passport[key] = value

        passports.append(passport)
    return passports

# def write_valid_passports(valid_passports):
#     with open('C:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 11/valid_passports.txt', 'w') as f:
#         for passport in valid_passports:
#             for key, value in passport.items():
#                 f.write(f'{key}:{value} ')
#             f.write('\n')
#             f.write('\n')
#         f.write('\n')

# take the scanned_passports.txt, and delete the invalid passports, and keep the valid ones as is
def write_valid_passports(valid_passports):
    with open('C:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 11/valid_passports.txt', 'w') as f:
        with open('C:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 11/scanned_passports.txt', 'r') as g:
            for line in g:
                for field in line.strip().split():
                    key, value = field.split(':')
                for passport in valid_passports:
                    if key in passport:
                        f.write(line)
                f.write('\n')






# line_wanted = ''
# with open('C:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 11/scanned_passports.txt', 'r') as g:
#     line_wanted = g.readline()
#     print(line_wanted)

# print(valid_passports[0])
# key_id, value = line_wanted.split(':')
# print(key_id)

# if key_id in valid_passports[0]:
    
#     print('yes')
        

def is_valid(passport):
    required_fields = ['byr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid', 'cid']
    for field in required_fields:
        if field not in passport:
            return False
    return True






# file_path = input("Enter the name of the file: ")
file_path = 'C:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 11/scanned_passports.txt'
# c:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 11/scanned_passports.txt

valid_passports = []

# call is_valid for each passport in passports, and add to valid_passports if valid
passports = get_passports(file_path)
for passport in passports:
    if is_valid(passport):
        valid_passports.append(passport)

write_valid_passports(valid_passports)






print("There are", len(valid_passports), "valid passports")









