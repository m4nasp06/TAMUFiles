
# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Names:        Samanyu Tatineni
#               Karl Artates
#               Ash Gottipatti
#               Manas Paramathmuni
# Section:      522
# Assignment:   7(team)
# Date:         04 october 2024


from colorama import Fore, Style

# Initialize a 9x9 board using lists of lists filled with empty circles
board = [[chr(9675) for _ in range(9)] for _ in range(9)]  # chr(9675) is '◯'

# Dictionary to convert letter coordinates (A-I) to column indices (0-8)
columns = {'A': 0, 'B': 1, 'C': 2, 'D': 3, 'E': 4, 'F': 5, 'G': 6, 'H': 7, 'I': 8}

# Start the game with player 'X' represented by a filled circle (●)
current_player = chr(9679)  # '●' for black stones
empty_spot = chr(9675)  # '◯' for empty spots

# Display the initial board
for row in board:
    for i in row:
        print(i, end=" ")
    print()  # Empty line for better


# Main game loop
while True:
    # Ask the user to enter a move
    move = input(f"Player {current_player}, enter your move (e.g., B5) or type 'stop' to end the game: ").strip()

    # Check if the user wants to stop the game
    if move.lower() == "stop":
        print("Game over! Final board state:")
        for row in board:
            print(" ".join(row))
        break

    # Check if the input is valid
    if len(move) < 2 or move[0].upper() not in columns or not move[1].isdigit() or not (1 <= int(move[1]) <= 9):
        print("Invalid input. Please enter a valid move like 'B5'.")
        continue

    # Convert the input to row and column indices
    col = columns[move[0].upper()]
    row = int(move[1]) - 1

    # Check if the selected position is already occupied
    if board[row][col] != empty_spot:
        print("That position is already occupied. Please choose another.")
        continue

    # Place the current player's stone on the board
    board[row][col] = current_player

    # Display the updated board with colored stones (if using colorama)
    for row_display in board:
        formatted_row = " ".join(row_display)
        print(formatted_row)
    print()  # Empty line for better formatting

    # Switch players after a valid move
    if current_player == chr(9679):  # If current player is '●'
        current_player = chr(9678)  # Switch to empty circle for next turn (◯)
    else:
        current_player = chr(9679)  # Switch back to filled circle (●)