# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   11.16 Pixel Painter
# Date:         08 11 2024
#

input_file = input("Enter the filename: ")
# input_file = "pixel_triangle.csv"
# input_file = "pixel_tea.csv"
character = input("Enter a character: ")
lines = []
with open(input_file, 'r') as inp_f:
    lines = inp_f.readlines()


split_lines = []
for line in lines:
#    split each list
    split_line = line.split(",")
    split_lines.append(split_line)


file_name = input_file.split(".")[0]
output_file = file_name + ".txt"
print(f"{output_file} created!")
with open(output_file, 'w') as out_f:
    for line in split_lines:
        i = 0
        for item in line:
            number = int(item)
            print(number)
            if i % 2 == 0:
                out_f.write(" " * number)
            if i % 2 == 1:
                out_f.write(character * number)
            i += 1
        out_f.write("\n")
