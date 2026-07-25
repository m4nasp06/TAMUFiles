from wrongImplementations import fibonacci, combinations


def testing(n, k):
    return (fibonacci(n) * combinations(n, k)) / fibonacci(fibonacci(n))
