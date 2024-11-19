# matplotlib_example.py

import numpy as np
import matplotlib.pyplot as plt

# As a team, we have gone through all required sections of the tutorial, and each team member understands the material.

# Plot 1: Parabolas with different focal lengths
x = np.linspace(-2.0, 2.0, 100) 
f1, f2 = 2, 6
y1 = (1 / (4 * f1)) * x**2
y2 = (1 / (4 * f2)) * x**2

plt.figure()
plt.plot(x, y1, label="f = 2", linewidth=2, color="blue")
plt.plot(x, y2, label="f = 6", linewidth=6, color="red")
plt.title("Parabolas with Different Focal Lengths")
plt.xlabel("x")
plt.ylabel("y")
plt.legend()
plt.show()

# Plot 2: Cubic polynomial
x = np.linspace(-4.0, 4.0, 25)
y = 2 * x**3 + 3 * x**2 - 11 * x - 6

plt.figure()
plt.plot(x, y, 'o-', color="purple") # the o- q
plt.title("Cubic Polynomial")
plt.xlabel("x")
plt.ylabel("y")
plt.show()

# Plot 3: Sine and Cosine functions on subplots
x = np.linspace(-2 * np.pi, 2 * np.pi, 100)
y_sin = np.sin(x)
y_cos = np.cos(x)

fig, (ax1, ax2) = plt.subplots(2, 1)
ax1.plot(x, y_sin, color="green")
ax1.set_title("Sine Function")
ax1.grid(True)

ax2.plot(x, y_cos, color="orange")
ax2.set_title("Cosine Function")
ax2.grid(True)

plt.tight_layout()
plt.show()

