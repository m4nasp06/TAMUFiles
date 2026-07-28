name = input("Enter name: ")
comma = name.find(",")
first = name[:comma].strip()
last = name[comma + 1:].strip()

print(f"The name is : {last}, {first}")
