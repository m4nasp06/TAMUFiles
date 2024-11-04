import sympy as sp

x = sp.symbols('x')

f = 4 *sp.sin(x)
fp = sp.diff(f, x)
m = fp.subs(x,sp.pi)
y = m * (x - sp.pi) + f.subs(x,sp.pi)
# show y graph
sp.plot(y)
sp.plot(m)