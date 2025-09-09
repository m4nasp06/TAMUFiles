import turtle
import random
import time

# Define the board size and player colors
BOARD_SIZE = 400
PLAYER_COLORS = ['red', 'blue', 'green', 'yellow']

# Initialize turtle screen
wn = turtle.Screen()
wn.title("Trouble Game with Turtle Graphics")
wn.bgcolor("lightgrey")
wn.setup(width=BOARD_SIZE + 100, height=BOARD_SIZE + 100)

# Draw the board
def draw_board():
    board_pen = turtle.Turtle()
    board_pen.speed(0)
    board_pen.pensize(3)

    # Draw outer boundary
    for _ in range(4):
        board_pen.forward(BOARD_SIZE)
        board_pen.left(90)

    # Draw inner diagonals
    board_pen.penup()
    board_pen.goto(-BOARD_SIZE // 2, -BOARD_SIZE // 2)
    board_pen.pendown()
    board_pen.goto(BOARD_SIZE // 2, BOARD_SIZE // 2)

    board_pen.penup()
    board_pen.goto(-BOARD_SIZE // 2, BOARD_SIZE // 2)
    board_pen.pendown()
    board_pen.goto(BOARD_SIZE // 2, -BOARD_SIZE // 2)

    board_pen.hideturtle()

# Player class using turtle graphics to represent pieces
class Player:
    def __init__(self, name, color):
        self.name = name
        self.color = color
        self.pieces = [turtle.Turtle() for _ in range(4)]
        self.positions = [0, 0, 0, 0]  # Positions on the track (0 means at home)

        # Initialize player pieces
        for piece in self.pieces:
            piece.shape("circle")
            piece.color(self.color)
            piece.penup()
            piece.goto(-BOARD_SIZE // 2 - 20, -BOARD_SIZE // 2 - 20)  # Off the board initially

    def roll_dice(self):
        input(f"{self.name}, press Enter to roll the dice...")
        return random.randint(1, 6)

    def move_piece(self, piece_index, steps):
        if self.positions[piece_index] == 0:
            if steps == 6:
                # Move out of home
                self.positions[piece_index] = 1
                self.pieces[piece_index].goto(-BOARD_SIZE // 2, 0)
                print(f"{self.name} moved piece {piece_index + 1} out of home.")
            else:
                print(f"{self.name} cannot move piece {piece_index + 1} out of home without rolling a 6.")
        else:
            # Move along the track
            new_position = self.positions[piece_index] + steps
            if new_position > 28:
                print(f"{self.name} cannot move piece {piece_index + 1} beyond the track.")
            else:
                self.positions[piece_index] = new_position
                self.animate_movement(piece_index, steps)
                print(f"{self.name} moved piece {piece_index + 1} to position {new_position}.")

    def animate_movement(self, piece_index, steps):
        piece = self.pieces[piece_index]
        for _ in range(steps):
            # For simplicity, we're just moving to the right in this example
            piece.forward(20)
            time.sleep(0.2)

# Game class to manage turns and gameplay
class TroubleGame:
    def __init__(self, players):
        self.players = players
        self.current_player_index = 0

    def play_turn(self):
        player = self.players[self.current_player_index]
        print(f"\n{player.name}'s turn:")

        roll = player.roll_dice()
        print(f"{player.name} rolled a {roll}")

        # Move a piece out of home if possible
        if roll == 6:
            for i in range(4):
                if player.positions[i] == 0:
                    player.move_piece(i, roll)
                    break

        # If player has pieces on the track, move one of them
        if any(piece > 0 for piece in player.positions):
            movable_pieces = [i for i, p in enumerate(player.positions) if p > 0]
            piece_index = random.choice(movable_pieces)
            player.move_piece(piece_index, roll)

        # Check if player has won
        if player.has_won():
            print(f"{player.name} has won the game!")
            return True

        # Switch to next player
        self.current_player_index = (self.current_player_index + 1) % len(self.players)
        return False

    def start_game(self):
        print("Starting Trouble Game!")
        game_over = False
        while not game_over:
            game_over = self.play_turn()

# Draw the game board
draw_board()

# Define players with different colors
players = [
    Player(name="Player 1", color=PLAYER_COLORS[0]),
    Player(name="Player 2", color=PLAYER_COLORS[1]),
    Player(name="Player 3", color=PLAYER_COLORS[2]),
    Player(name="Player 4", color=PLAYER_COLORS[3])
]

# Start the game
game = TroubleGame(players)
game.start_game()

# Keep the window open
wn.mainloop()

