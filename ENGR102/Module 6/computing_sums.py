# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   Lab 6.17 Computing Sums
# Date:         24 9 2024
#


# Get two integers from user
integer1 = int(input("Enter an integer: "))
integer2 = int(input("Enter another integer: "))

# Initialize sum
sum = 0

# Loop through numbers from first integer to second integer
for i in range(integer1, integer2 + 1):
    # Add the number to the sum
    sum += i

# Print the sum
print(f"The sum of all integers from {integer1} to {integer2} is {sum}")

