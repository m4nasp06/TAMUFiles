import sympy as sp
x = sp.symbols('x')

f = sp.sin(x)**2 * sp.cos(x)
result = sp.integrate(f, x)
print(result)