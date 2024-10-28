# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   8.18 ASCII Clock Individual
# Date:         05 10 2024
#


# Step 3: Define ASCII Notation Using a Dictionary
ascii_digits = {
    '0': ["000","0 0", "0 0", "0 0", "000"],
    '1': [" 1 ", "11 ", " 1 ", " 1 ", "111"],
    '2': ["222", "  2", "222", "2  ", "222"],
    '3': ["333", "  3", "333", "  3", "333"],
    '4': ["4 4", "4 4", "444", "  4", "  4"],
    '5': ["555", "5  ", "555", "  5", "555"],
    '6': ["666", "6  ", "666", "6 6", "666"],
    '7': ["777", "  7", "  7", "  7", "  7"],
    '8': ["888", "8 8", "888", "8 8", "888"],
    '9': ["999", "9 9", "999", "  9", "999"],
    ':': [" ", ":", " ", ":", " "],
    'A': [" A ", "A A", "AAA", "A A", "A A"],
    'M': ["M   M", "MM MM", "M M M", "M   M", "M   M"],
    'P': ["PPP", "P P", "PPP", "P  ", "P  "]
}

# Step 4: Generate ASCII Representation for the Time
def generate_ascii_representation(time_str, preferred_char):
    ascii_art_lines = ["", "", "", "", ""]

    for char in time_str: #time_str = 13:49
        if char in ascii_digits:
            # print(char)
            for i in range(5): # 0, 1, 2, 3, 4
                line = ascii_digits[char][i]
                # print("curent line: ", line)
                # Replace characters with the preferred character if specified
                if preferred_char:
                    line = ''.join(preferred_char if c.isdigit() else c for c in line)
                # if char is M or last character, dont add space
                if char == 'M' or char == time_str[-1]:
                    ascii_art_lines[i] += line
                else:
                    ascii_art_lines[i] += line + " "

    return ascii_art_lines