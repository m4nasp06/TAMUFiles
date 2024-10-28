# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:
# Section:      522
# Assignment:   8.18 LAB: Leet speak
# Date:         18/10/2024
#

#
# YOUR CODE HERE
#
#Take input from the user
Text = input("Enter some text: ")
#Now let's define the leet values using dictionaries
leet = {
    'a': '4',
    'e': '3',
    'o': '0',
    's': '5',
    't': '7'
}
leet_text =""
#Loop through the text and see if it exists in leet if it does add that to leen_text if it doesn't simply add i
for i in Text:
  if i in leet:
    print(i)
    leet_text = leet_text + leet[i]
  else:
    leet_text += i
print(f'In leet speak, "{Text}" is: {leet_text}')