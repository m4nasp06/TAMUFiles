import sympy as sp

x = sp.symbols('x')
f = x**3 - 4*x**2 - 6*x - 2
fpp = sp.diff(f, x, 2)

c = 12
test = fpp.subs(x, c)

if (test>0):
    print('A')
elif (test<0):
    print('B')

# it will print A if f is concave up at x=c, and B if f is concave down at x=c