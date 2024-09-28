# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Samanyu Tatineni
#               Karl Artates
#               Ash Gottipatti
#               Manas Paramathmuni
# Section:      522
# Assignment:   6.11 Pyramid Area Part 1
# Date:         27 09 2024
#


side_length = float(input("Enter the side length in meters: "))
layers = int(input("Enter the number of layers: "))

total_area = 0

# except for if there is one cube, if each layer has n cubes in length, the number of sides that don't need be covered are 2*n
# so layer 2 has 2 cubes by 2 cubes, so 4 cubes, 4 sides don't need to be covered, and layer 3 has 3 cubes by 3 cubes, so nine cubes, 
# so 18 sides don't need to be covered, so for layer n, 2*n^2 sides don't need to be covered. 
# we also need to account that every layers's floor needs to be covered, so we need to subtract the bottom surface area from each layer.
# So if if we have 3 layers, we can subtract 1ms^2 for the top layer, and 4 ms^2 from the second layer, and 9 ms^2 from the third layer.

# Loop through each layer
for i in range(layers, 0, -1):
    total_area+=(4*i+i**2-(i-1)**2)*(side_length**2)


# Print the result with two decimal points
print(f"You need {total_area:.2f} m^2 of gold foil to cover the pyramid")



