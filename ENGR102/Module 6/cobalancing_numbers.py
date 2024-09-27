# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   Lab 6.20 Co-Balancing Numbers
# Date:         24 9 2024
#

# Get integer from user
n = int(input("Enter a value for n: "))

# Initialize sum
sum1 = 0
sum2 = 0

# Loop through numbers from 1 to n
for i in range(1, n + 1):
    sum1 += i

i = n + 1
while sum2 < sum1:
    sum2 += i
    i += 1

if sum1 == sum2:
    print(f"{n} is a co-balancing number with r={i-n-1}")
else:
    print(f"{n} is not a co-balancing number")


