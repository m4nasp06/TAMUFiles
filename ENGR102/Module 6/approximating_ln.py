# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."

# Name:         Samanyu Tatineni
#               Karl Artates
#               Ash Gottipatti
#               Manas Paramathmuni
# Section:      522
# Assignment:   6.15 Approximating ln(x)
# Date:         27 09 2024


#imporitng math
import math
from math import *


# keep asking for an input if input is not greater than 0, and less than or equal to 2
x = float(input("Enter a value for x: "))
while x<=0 or x>2: 
    x = float(input("Out of range! Try again: "))


tolerance = float(input("Enter the tolerance: "))


term = x-1
sum_ln = 0.0

n = 1

while abs(term) >= tolerance: 
    
    if n%2 == 1: 
            sum_ln += term

    if n%2 == 0:
            sum_ln -= term

    n = n + 1
    term = ((x-1)**n)/n
    

print(f"ln({x}) is approximately {sum_ln}")
print(f"ln({x}) is exactly {math.log(x)}")
print(f"The difference is {abs(sum_ln - math.log(x))}")