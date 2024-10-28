# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   Split List
# Date:         05 10 2024
#

# take input from user
# numbers = input("Enter numbers: ")
numbers = "100 2 102"

# split the input into a list of integers
numbers = numbers.split()
numbers = [int(i) for i in numbers]

# initialize variables
left = []
right = []
left_sum = 0
right_sum = 0
split = False

# loop through the list without using sum() function
for i in range(len(numbers)):
    left = numbers[:i]
    right = numbers[i:]
    left_sum = 0
    right_sum = 0
    print(f"i: {i}")
    print(f"Left: {left}")
    print(f"Right: {right}")

    for j in left:
        left_sum += j
    print("Left sum: ", left_sum)

    for k in right:
        right_sum += k
    print("Right sum: ", right_sum)

    if left_sum == right_sum:
        split = True
        break

# print the results
if split:
    print(f"Left: {left}")
    print(f"Right: {right}")
    print(f"Both sum to {left_sum}")

else:
    print("Cannot split evenly")
