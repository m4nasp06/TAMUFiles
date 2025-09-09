import random

# map is 2 day array 15x15 size, make the edge items of the array "O" and the rest blank
# make left and right diagonals of the map "O"
# make the center piece unicode character 
def create_map():
    map = [[" " for i in range(15)] for j in range(15)]
    for i in range(15):
        for j in range(15):
            # center piece is unicode chr(9679)
            print(i,j)
            
            if i == 0 or i == 14 or j == 0 or j == 14:
                map[i][j] = "O"
            if i == j or i + j == 14:
                map[i][j] = "O"
            if i == 7 and j == 7:
                map[i][j] = chr(9679)
    return map

# print the map
def print_map(map):
    for i in range(15):
        for j in range(15):
            print(map[i][j], end=" ")
        print()

create_map()
print_map(create_map())

# making the game of trouble, Players may move pieces out of their home onto their designated start space only when the die lands on 6. Getting a 6 at any point in the game also allows the player to take another turn, even if the player cannot move any of their pieces (as they cannot land on any of their own pieces). They also may move a new piece out even if they have another piece currently in play, and can also do the same if another player's piece is occupying their "start" space, but cannot do so when one of their own pieces is occupying their "start" space.
# Pieces move clockwise around the track. Players can send opponents' pieces back to the start by landing on them. Pieces are protected from capture after arriving in the final four slots of the finish area.
# in ours, 15x15 map, each player has 4 pieces, and each corner is a home for a player
# make function asking for how many players, then assign each player a home, and 4 pieces
# then show the map with the pieces in their homes, randomizing unicode characters for each player

def create_players():
    players = []
    num_players = int(input("How many players? "))
    for i in range(num_players):
        player = {}
        player["home"] = (0, 0) if i == 0 else (0, 14) if i == 1 else (14, 0) if i == 2 else (14, 14)
        player["pieces"] = [(player["home"][0] + 1, player["home"][1] + 1), (player["home"][0] + 1, player["home"][1] - 1), (player["home"][0] - 1, player["home"][1] + 1), (player["home"][0] - 1, player["home"][1] - 1)]
        player["piece_chars"] = [chr(random.randint(0x1F600, 0x1F64F)) for i in range(4)]
        players.append(player)
    return players

def print_players(map, players):
    for player in players:
        for piece, piece_char in zip(player["pieces"], player["piece_chars"]):
            map[piece[0]][piece[1]] = piece_char
    print_map(map)

players = create_players()
print_players(create_map(), players)
