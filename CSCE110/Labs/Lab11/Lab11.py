fibCount = 0
memory = {}

def power(base, exponent):
    
    if exponent <= -1:
        return None
    if exponent == 0:
        return 1
    return base * power(base, exponent - 1)

def fib(n):
    
    global fibCount
    fibCount += 1

    if n == 0:
        return 0

    if n == 1:
        return 1

    if (n - 1) in memory:
        f1 = memory[n - 1]
    else:
        f1 = fib(n - 1)

    if (n - 2) in memory:
        f2 = memory[n - 2]
    else:
        f2 = fib(n - 2)
    f = f1 + f2

    memory[n] = f
    return f
