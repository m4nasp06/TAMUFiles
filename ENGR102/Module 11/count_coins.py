# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   11.15 Count Coins
# Date:         08 11 2024
#



def count_coins():

    with open("game.txt", "r") as in_f:
        instr = in_f.readlines()

    # Initialize variables
    sum_coins = 0
    idx = 0
    exec_instr = []
    coins_collected = []

    # Process instr
    while idx < len(instr):
        if idx in exec_instr:
            break

        exec_instr.append(idx)
        line = instr[idx].strip()
        parts = line.split()
        oper = parts[0]
        val = int(parts[1])

        if oper == "coin":
            sum_coins += val
            coins_collected.append(val)
            idx += 1
        elif oper == "jump":
            idx += val
        elif oper == "none":
            idx += 1

    # Write the collected coins to coins.txt
    with open("coins.txt", "w") as out_f:
        for coin in coins_collected:
            out_f.write(f"{coin}\n")

    # Print the total number of coins collected
    print(f"Total coins collected: {sum_coins}")


# Run the function
count_coins()
