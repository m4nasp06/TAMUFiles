# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   11.1 - Pretty Plot
# Date:         15 November 2024
#

import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt

# Create the matrix and point (0, 1)
matrix = np.array([[1.02, 0.095], [-0.095, 1.02]])
point = np.array([0, 1])

# Create a list to store the points
points = [point]

# Multiply the matrix by the point 250 times
for i in range(250):
    point = np.dot(matrix, point)
    points.append(point)

# Extract the x and y coordinates from the points
x = [point[0] for point in points]
y = [point[1] for point in points]

# Plot the points
plt.plot(x, y)
plt.xlabel('X')
plt.ylabel('Y')
plt.title('Spiral Pattern')
plt.show()


