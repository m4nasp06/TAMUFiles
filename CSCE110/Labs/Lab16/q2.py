from q1 import isPrime, isBinary

binary_input = input("Enter a binary number : ")

if not isBinary(binary_input):
    print("Invalid input")
else:
    decimal_value = int(binary_input, 2)
    if isPrime(decimal_value):
        print(f"{binary_input} is Prime.")
    else:
        print(f"{binary_input} is not Prime.")
