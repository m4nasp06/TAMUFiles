import sympy as sp
n = sp.symbols('n', integer=True)
an = 1/(2*n+17)
print(sp.summation(an, (n,1,sp.oo)).evalf())