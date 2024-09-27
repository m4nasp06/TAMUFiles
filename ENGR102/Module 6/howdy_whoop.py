# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   Lab 6.16 Howdy Whoop
# Date:         24 9 2024
#



# Get the two integers from the user
integer1 = int(input("Enter an integer: "))
integer2 = int(input("Enter another integer: "))

# Loop through the numbers 1 to 100
for i in range(1, 101):
    # If number is evenly divisible by first integer
    if i % integer1 == 0:
        # If  number is evenly divisible by second integer
        if i % integer2 == 0:
            print("Howdy Whoop")
        else:
            print("Howdy")
    # If number is evenly divisible by second integer
    elif i % integer2 == 0:
        print("Whoop")
    # If number is not evenly divisible by either integer
    else:
        print(i)
    


    

