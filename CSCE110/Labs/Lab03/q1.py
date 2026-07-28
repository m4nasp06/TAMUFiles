num = int(input("Enter base-10 number: "))
base = int(input("Enter base for conversion: "))

digit7, r = divmod(num, base ** 7)
digit6, r = divmod(r, base ** 6)
digit5, r = divmod(r, base ** 5)
digit4, r = divmod(r, base ** 4)
digit3, r = divmod(r, base ** 3)
digit2, r = divmod(r, base ** 2)
digit1, r = divmod(r, base ** 1)
digit0, r = divmod(r, base ** 0)

print(f"The base-10 number {num} in base-{base} is {digit7}{digit6}{digit5}{digit4}{digit3}{digit2}{digit1}{digit0}")
