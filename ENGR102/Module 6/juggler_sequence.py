# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   Lab 6.19 Juggler Sequence
# Date:         24 9 2024
#

# Get integer from user
n = int(input("Enter a positive integer: "))

# Initialize sequence
sequence = [n]

# Loop until n is 1
while n != 1:
    # If n is even
    if n % 2 == 0:
        n = int(n ** 0.5)
    # If n is odd
    else:
        n = int(n ** 1.5)
    # Add n to sequence
    sequence.append(n)

# Print sequence
print("The Juggler sequence starting at", sequence[0], "is:")
print(", ".join(map(str, sequence)))

# Print number of iterations
print(f"It took {len(sequence) - 1} iterations to reach 1")
