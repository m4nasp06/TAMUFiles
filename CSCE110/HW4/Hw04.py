# Q1


def print_separator():
    d = "-"
    print(f"|{d * 3}|{d * 14}|{d * 14}|{d * 12}|{d * 9}|")


def print_row(num, name, unit_price, quantity, total):
    print(f"|{num:<3}|{name:<14}|{unit_price:>14.2f}|{quantity:>12.2f}|{total:>9.2f}|")


def receipt_entry_and_print():
    receipt = {}

    data = input("Enter item : ").split()
    receipt[1] = (data[0], float(data[1]), float(data[2]))

    data = input("Enter item : ").split()
    receipt[2] = (data[0], float(data[1]), float(data[2]))

    data = input("Enter item : ").split()
    receipt[3] = (data[0], float(data[1]), float(data[2]))

    data = input("Enter item : ").split()
    receipt[4] = (data[0], float(data[1]), float(data[2]))
    print()
    print(f"Data as dictionary : {receipt}")
    print()

    print(f"|{'___':<3}|{'':_<14}|{'':_<14}|{'':_<12}|{'':_<9}|")
    print(f"|{'No.':<3}|{'Item':>14}|{'Unit Price':>14}|{'Quantity':>12}|{'Total':>9}|")

    name1, price1, qty1 = receipt[1]
    total1 = price1 * qty1
    name2, price2, qty2 = receipt[2]
    total2 = price2 * qty2
    name3, price3, qty3 = receipt[3]
    total3 = price3 * qty3
    name4, price4, qty4 = receipt[4]
    total4 = price4 * qty4
    total_all = total1 + total2 + total3 + total4

    print_row(1, name1, price1, qty1, total1)
    print_row(2, name2, price2, qty2, total2)
    print_row(3, name3, price3, qty3, total3)
    print_row(4, name4, price4, qty4, total4)
    print_separator()
    print(f"|{'':3}|{'':14}|{'':14}|{'Total':>12}|{total_all:>9.2f}|")
    print_separator()


# Q2


def shapes():
    pi = 3.141592653
    shape = input("Select shape (circle, square, triangle, rectangle) : ")

    if shape == "circle":
        r = float(input("Enter radius = "))
        area = pi * r * r
        print(f"Area of circle = {area:.3f}")
    elif shape == "square":
        s = float(input("Enter side : "))
        area = s * s
        print(f"Area of square = {area:.3f}")
    elif shape == "rectangle":
        sides = input("Enter two sides : ").split()
        area = float(sides[0]) * float(sides[1])
        print(f"Area of rectangle = {area:.3f}")
    elif shape == "triangle":
        sides = input("Enter three sides : ").split()
        a, b, c = float(sides[0]), float(sides[1]), float(sides[2])
        s = (a + b + c) / 2
        area = (s * (s - a) * (s - b) * (s - c)) ** 0.5
        print(f"Area of triangle = {area:.3f}")
    else:
        print("Invalid shape")


# Q3


def transform_word(word):
    if len(word) == 3 and (word[0] == "t" or word[0] == "T"):
        return word.upper()
    if len(word) > 5 and "e" in word:
        return word.replace("e", "*")
    return word


def analyze_three_words(word_list):
    word_list[0] = transform_word(word_list[0])
    word_list[1] = transform_word(word_list[1])
    word_list[2] = transform_word(word_list[2])


# Q4


def check_ride_access(rider, ride):
    result = {}

    if rider["heart_condition"]:
        result["status"] = "Denied"
        result["reason"] = "Safety Risk"
        return result

    if rider["height_cm"] < ride["min_height"]:
        result["status"] = "Denied"
        result["reason"] = "Too Short"
        return result

    if ride["is_extreme"] and rider["age"] < 16:
        result["status"] = "Denied"
        result["reason"] = "Too Young for Extreme Rides"
        return result

    result["status"] = "Approved"
    result["reason"] = "N/A"
    return result


# Q5


def letters_and_vowels():
    fullStr = input("Enter string : ")
    letters, vowels = 0, 0
    for char in fullStr:
        if char.isalpha():
            letters += 1
            if char.lower() in "aeiou":
                vowels += 1
    print(f"The string has {letters} letters and {vowels} vowels")


# Q6


def reverse_string(text):
    if len(text) <= 1:
        return text
    return reverse_string(text[1:]) + text[0]
