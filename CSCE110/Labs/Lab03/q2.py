n1 = float(input("Enter Number: "))
n2 = float(input("Enter Number: "))
n3 = float(input("Enter Number: "))

mean = (n1 + n2 + n3) / 3
variance = ((n1 - mean) ** 2 + (n2 - mean) ** 2 + (n3 - mean) ** 2) / 3
sd = variance ** 0.5

print(f"Mean of {n1} , {n2} and {n3} is {round(mean, 2)}")
print(f"SD of {n1} , {n2} and {n3} is {round(sd, 2)}")
