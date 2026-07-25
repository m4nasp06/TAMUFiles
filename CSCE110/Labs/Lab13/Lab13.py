def factorials():
    num = int(input("Enter Number : "))

    while num < 1 or num > 20:
        num = int(input("Out of range. Enter number between 1 and 20 : "))

    factorial_list = []

    for i in range(1, num + 1):
        fact = 1

        for j in range(1, i + 1):
            fact *= j

        factorial_list.append(fact)

    print(f"Factorials up to {num} are:")
    print(factorial_list)


def roll_dice(n, k):
    if n < 4 or k < 2 or k > 2 * n:
        return "Invalid input"

    count = 0
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if i + j == k:
                count += 1

    probability = count / (n * n)
    return probability


def probability_table():
    n = int(input("Enter number of sides : "))
    if n < 4:
        print("Invalid input")
        return

    print(f"{'Sum':<10}{'Probability':<15}")
    for k in range(2, 2 * n + 1):
        prob = roll_dice(n, k)
        print(f"{k:<10}{prob:<15.6f}")
