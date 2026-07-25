def isPrime(n):
    for i in range(2, n):
        if n % i == 0:
            return False
    return True


def isBinary(n):
    for char in n:
        if char != "0" and char != "1":
            return False
    return True
