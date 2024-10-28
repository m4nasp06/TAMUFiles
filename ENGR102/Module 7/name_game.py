# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   Name_Game
# Date:         05 10 2024
#

# (X), (X), Bo-B(Y)
# Banana-Fana Fo-F(Y)
# Me Mi Mo-M(Y)
# (X)!

# Given name(x), (y) is the name without first consonant sound
# Example output (using input Niki):
# What is your name? Niki
# Niki, Niki, Bo-Biki
# Banana-Fana Fo-Fiki
# Me Mi Mo-Miki
# Niki!



str = input("What is your name? ")

vowels = ['a', 'e', 'i', 'o', 'u']

name = str.lower()


# if vowel first, then lowercase
if name[0].lower() in vowels:
    cut_name = name.lower()

else:
    for i in range(len(name)): # go through name letter by letter
        if name[i].lower() in vowels: # if a vowel is found, make a string from vowel to end
            cut_name = name[i:]
            break


print(f"{str}, {str}, Bo-B{cut_name}")
print(f"Banana-Fana Fo-F{cut_name}")
print(f"Me Mi Mo-M{cut_name}")
print(f"{str}!")

