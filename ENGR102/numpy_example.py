
# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Names:        Samanyu Tatineni
#               Karl Artates
#               Ash Gottipatti
#               Manas Paramathmuni
# Section:      522
# Assignment:   12 Team - Numpy Example
# Date:         11 November 2024
# numpy_example.py

import numpy as np

# As a team, we have gone through all required sections of the tutorial, and each team member understands the material.

# Create matrices A, B, and C
A = np.arange(0, 12).reshape(3, 4)
B = np.arange(0, 8).reshape(4, 2)
C = np.arange(0, 6).reshape(2, 3)

# Print matrices A, B, and C with a blank line between them
print("A =", A, "\n")
print("B =", B, "\n")
print("C =", C, "\n")

# Compute D = A * B * C
D = A @ B @ C
print("D =", D, "\n")

# Print transpose of D
D_transpose = D.T
print("D^T =", D_transpose, "\n")

# Calculate and print E = sqrt(D / 2)
E = np.sqrt(D) / 2
print("E =", E)
