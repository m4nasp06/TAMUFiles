def compare_floats():
    num1 = float(input("Float 1 : "))
    num2 = float(input("Float 2 : "))
    threshold = float(input("Threshold : "))

    if threshold < 0:
        print("Invalid Threshold. Threshold must be a positive number.")
    else:
        diff = num1 - num2
        if diff < 0:
            diff *= -1
        if diff <= threshold:
            print("The numbers may be considered equal.")
        else:
            print("The numbers are not equal.")


def prefix_calculator():
    parts = input("Enter Expression : ").split()
    operator = parts[0]

    if operator == "abs":
        n = int(parts[1])
        if n < 0:
            result = -n
        else:
            result = n
        print(f"abs({n}) = {result}")
    else:
        num1 = int(parts[1])
        num2 = int(parts[2])
        if operator == "+":
            print(f"{num1} + {num2} = {num1 + num2}")
        elif operator == "-":
            print(f"{num1} - {num2} = {num1 - num2}")
        elif operator == "*":
            print(f"{num1} * {num2} = {num1 * num2}")
        elif operator == "/":
            if num2 == 0:
                print("Division by 0")
            else:
                print(f"{num1} / {num2} = {num1 // num2}")
