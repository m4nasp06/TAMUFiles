# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   Kaprekar's Constant
# Date:         05 10 2024
#

# take input from user
initial_num = input("Enter a four-digit integer: ")

# initialize variables
num = initial_num.zfill(4)
iterations = 0

# loop until 6174 is reached
while num != "6174" and num != "0000":  
    
    # sort  digits in descending and ascending order
    num_desc = "".join(sorted(num, reverse=True)).lstrip("0")
    num_asc = "".join(sorted(num)).lstrip("0")

    num_desc = int(num_desc) if num_desc else 0
    num_asc = int(num_asc) if num_asc else 0

    # subtract  smaller number from  bigger number
    num = str(num_desc - num_asc).zfill(4)

    # print the sequence
    if iterations == 0:
        print(initial_num.lstrip("0"), end=" > ")

    if num == "6174":
        print(num)
    elif num == "0000":
        print("0")
    else:
        print(num.lstrip("0"), end=" > ")

    # increment  number of iterations
    iterations += 1

# Handle special case 
if num == "0000":
    print(f"{initial_num.lstrip('0')} reaches 0 via Kaprekar's routine in {iterations} iterations")
else:
    print(f"{initial_num} reaches 6174 via Kaprekar's routine in {iterations} iterations")
