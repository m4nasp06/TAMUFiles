
# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Names:        Samanyu Tatineni
#               Karl Artates
#               Ash Gottipatti
#               Manas Paramathmuni
# Section:      522
# Assignment:   8(Team) - ASCII Clock
# Date:         04 october 2024
 



# Main Program
def main():
    # Step 1: Get user input
    time_str = input("Enter the time: ")  # e.g., 13:49
    clock_type = int(input("Choose the clock type (12 or 24): "))  # e.g., 12 or 24
    allowed_chars = 'abcdeghkmnopqrsuvwxyz@$&*='
    allowed_chars_list = list(allowed_chars)

    not_allowed = True
    i = 0
    while not_allowed:
        
        if i == 0:
            preferred_char = input("Enter your preferred character: ")
        else:
            preferred_char = input("Character not permitted! Try again: ")
        # preferred_char = input("Enter your preferred character: ")
        if preferred_char == "":
            not_allowed = False
        elif preferred_char in allowed_chars_list:
            not_allowed = False
        
            
        i += 1
        
    print()

    # Step 2: Convert time format if needed
    if clock_type == 12:
        time_str = convert_to_12_hour_format(time_str)

    # Step 3: Generate ASCII representation
    ascii_art_lines = generate_ascii_representation(time_str, preferred_char)

    # Step 4: Print the final ASCII representation
    print_ascii_art(ascii_art_lines)

# Step 2: Convert Time Format
def convert_to_12_hour_format(time_str):
    hours, minutes = map(int, time_str.split(':'))
    period = "AM"

    if hours >= 12:
        period = "PM"
        if hours > 12:
            hours -= 12
    elif hours == 0:
        hours = 12

    return f"{hours}:{minutes:02} {period}"

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

# Step 5: Print the Final ASCII Representation
def print_ascii_art(ascii_art_lines):
    for line in ascii_art_lines:
        print(line)

# Run the program
if __name__ == "__main__":
    main()