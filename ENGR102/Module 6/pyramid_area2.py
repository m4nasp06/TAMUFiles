# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Names:        Samanyu Tatineni
#               Karl Artates
#               Ash Gottipatti
#               Manas Paramathmuni
# Section:      522
# Assignment:   6.12 Pyramid Area Part 2
# Date:         27 September 2024

#This is the answer 

side_length=float(input('Enter the side length in meters:'))
layers=int(input('Enter the number of layers:'))

total_area=(side_length**2)*(3*layers**2+2*layers)

print(f"You need {total_area:.2f} m^2 of gold foil to cover the pyramid")