# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   Kaprekar's Challenge
# Date:         05 10 2024
#


total_iterations = 0


# ctivity #3 challenge: More Kaprekar’s Constant (optional 2 bonus points)
# Modify your program from Activity 4 to compute the sum of the number of iterations required to reach
# 6174 (or 0000) using Kaprekar’s routine for all four-digit numbers, from 0000 to 9999. Name your file
# kaprekars_challenge.py. If your program calculates the total number of iterations correctly, you will
# receive 2 bonus points on this assignment.
# Example output:
# Kaprekar's routine takes ????? total iterations for all four-digit numbers

# 
for i in range(10000):
    num = str(i).zfill(4)
    iterations = 0

    while num != "6174" and num != "0000":

        num_desc = "".join(sorted(num, reverse=True)).lstrip("0")
        num_asc = "".join(sorted(num)).lstrip("0")

        num_desc = int(num_desc) if num_desc else 0
        num_asc = int(num_asc) if num_asc else 0

        num = str(num_desc - num_asc).zfill(4)

        iterations += 1

    total_iterations += iterations

print(f"Kaprekar's routine takes {total_iterations} total iterations for all four-digit numbers")
