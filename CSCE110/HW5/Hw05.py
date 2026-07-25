# Q1: Palindrome Check
def palindrome_create(a):
    if a.lower() == a.lower()[::-1]:
        return a
    return a + a[:-1].lower()[::-1]


# Q2: Statistics Calculator
def stats_calc():
    numbers = []
    while True:
        n = int(input("Enter Number (-100 to 100) : "))
        if n < -100 or n > 100:
            break
        numbers.append(n)

    if not numbers:
        print("Empty")
        return

    N = len(numbers)
    total = 0
    for x in numbers:
        total += x
    mean = total / N
    var_total = 0
    for x in numbers:
        var_total += (x - mean) ** 2
    variance = var_total / N
    std_dev = variance**0.5

    print(f"Mean = {mean:.2f}")
    print(f"Variance = {variance:.2f}")
    print(f"Standard Dev. = {std_dev:.2f}")


# Q3: String Pattern Validation
def string_pattern():
    s = input("Enter String : ")

    if len(s) % 2 != 0:
        print("String length is not even.")
        return

    for i in range(len(s)):
        if not s[i].isalnum():
            print(f"Non alphanumeric character at position {i + 1}.")
            return

    if s[0].isalpha():
        pattern = 1
    else:
        pattern = 2

    for i in range(1, len(s)):
        if pattern == 1:
            if i % 2 == 1 and not s[i].isdigit():
                print(f"Two letters next to each other at {i + 1}.")
                return
            if i % 2 == 0 and not s[i].isalpha():
                print(f"Two numbers next to each other at position {i + 1}.")
                return
        else:
            if i % 2 == 1 and not s[i].isalpha():
                print(f"Two numbers next to each other at position {i + 1}.")
                return
            if i % 2 == 0 and not s[i].isdigit():
                print(f"Two letters next to each other at {i + 1}.")
                return

    print(f"String follows Pattern {pattern}")


# Q4: Pair Set Sum
def pairSetSum(a, num):
    result = set()
    lst = sorted(a)
    for i in range(len(lst)):
        for j in range(i + 1, len(lst)):
            if lst[i] + lst[j] == num:
                result.add((lst[i], lst[j]))
    return result


# Q5: Fibonacci Sequence
def FibonacciBetween(x, y):
    fibs = []
    a, b = 0, 1
    if x <= 0 <= y:
        fibs.append(0)
    while b <= y:
        if b >= x:
            fibs.append(b)
        a, b = b, a + b
    return fibs


# Q6: Integer Sum
def integerSum(lst1, lst2):
    n1 = 0
    for d in lst1:
        n1 = n1 * 10 + d
    n2 = 0
    for d in lst2:
        n2 = n2 * 10 + d
    total = n1 + n2
    if total == 0:
        return [0]
    digits = []
    while total > 0:
        digits.append(total % 10)
        total //= 10
    return digits[::-1]


# Q7: Cost Calculator
def cost_calculator(*args, drinks=[], wings=[], coupon=0):
    pizza_price = 13.00
    topping_prices = {
        "pepperoni": 1.00,
        "mushroom": 0.50,
        "olives": 0.50,
        "anchovy": 2.00,
        "ham": 1.50,
    }
    drink_prices = {"small": 2.00, "medium": 3.00, "large": 3.50, "tub": 3.75}
    wing_prices = {10: 5.00, 20: 9.00, 40: 17.50, 100: 48.00}
    total = 0.0
    for pizza in args:
        total += pizza_price
        for topping in pizza:
            total += topping_prices[topping]
    for drink in drinks:
        total += drink_prices[drink]
    for w in wings:
        total += wing_prices[w]
    total_with_tax = total + total * 0.0625
    discount = total * coupon
    return round(total_with_tax - discount, 2)
