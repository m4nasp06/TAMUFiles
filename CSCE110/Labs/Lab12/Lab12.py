def user_max():

    numbers = []

    num = int(input("Enter Number : "))

    while num != 0:
        numbers.append(num)

        num = int(input("Enter Number : "))

    print("The numbers are :", numbers)

    if numbers == []:
        print("Max is undefined")

    else:
        biggest = numbers[0]

        for num in numbers:
            if num > biggest:
                biggest = num

        print("Max is :", biggest)


def letter_and_vowels():
    fullStr = input("Enter string : ")
    letters, vowels = 0, 0
    for char in fullStr:
        if char.isalpha():
            letters += 1
            if char.lower() in "aeiou":
                vowels += 1
    print(f"The string has {letters} Letters and {vowels} vowels")


def factorial():

    number = int(input("Enter number : "))
    while number < 1 or number > 20:
        number = int(input("Out of range, Enter number between 1 and 20 : "))
    factorial = 1
    for i in range(1, number + 1):
        factorial *= i
    print(f"Factorial of {number} is {factorial}.")
