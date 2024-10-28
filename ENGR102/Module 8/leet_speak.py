# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   8.18 Leet Speak
# Date:         05 10 2024
#


# get input 
original_text = input("Enter some text:")
leet = {'a':'4' , 'e':'3' , 'o':'0' , 's':'5' , 't':'7'}

leet_text = ""

for i in original_text:
	if i in leet:
		leet_text += leet[i]
	else:
		leet_text += i

print(f'In leet speak, "{original_text}" is: {leet_text}')

