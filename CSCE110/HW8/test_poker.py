from poker_game import PlayingCard, PlayerHand, HandRanker, HAND_NAMES

ranker = HandRanker()


def make_hand(hole_ranks_suits):
    h = PlayerHand()
    for rank, suit in hole_ranks_suits:
        h.deal_in(PlayingCard(rank, suit))
    return h


def make_board(ranks_suits):
    cards = []
    for r, s in ranks_suits:
        cards.append(PlayingCard(r, s))
    return cards


def check_category(name, hole, board, expected_name):
    h = make_hand(hole)
    rank = ranker.best_of(h, make_board(board))
    got = HAND_NAMES[rank]
    status = "PASS" if got == expected_name else "FAIL"
    print("[" + status + "] " + name + ": expected " + expected_name + ", got " + got)
    return status == "PASS"


def kicker_test(name, hole1, hole2, board, expected_winner):
    h1 = make_hand(hole1)
    h2 = make_hand(hole2)
    b = make_board(board)
    r1 = ranker.best_of(h1, b)
    r2 = ranker.best_of(h2, b)

    if r1 != r2:
        result = "Player 1" if r1 > r2 else "Player 2"
    else:
        cmp = ranker.compare(h1.best_five, h2.best_five, r1)
        if cmp > 0:
            result = "Player 1"
        elif cmp < 0:
            result = "Player 2"
        else:
            result = "TRUE TIE"

    status = "PASS" if result == expected_winner else "FAIL"
    print("[" + status + "] " + name + ": expected " + expected_winner + ", got " + result +
          " (P1=" + HAND_NAMES[r1] + ", P2=" + HAND_NAMES[r2] + ")")
    return status == "PASS"


results = []

print("=== Hand Category Tests ===")
results.append(check_category(
    "Royal Flush", [(14, "♥"), (13, "♥")],
    [(12, "♥"), (11, "♥"), (10, "♥"), (2, "♣"), (3, "♦")], "Royal Flush"))

results.append(check_category(
    "Straight Flush (9-high)", [(9, "♠"), (8, "♠")],
    [(7, "♠"), (6, "♠"), (5, "♠"), (2, "♣"), (14, "♦")], "Straight Flush"))

results.append(check_category(
    "Four of a Kind", [(5, "♥"), (5, "♠")],
    [(5, "♦"), (5, "♣"), (2, "♦"), (3, "♣"), (9, "♥")], "Four of a Kind"))

results.append(check_category(
    "Full House", [(6, "♥"), (6, "♠")],
    [(6, "♦"), (9, "♣"), (9, "♦"), (2, "♣"), (3, "♥")], "Full House"))

results.append(check_category(
    "Flush", [(2, "♣"), (7, "♣")],
    [(9, "♣"), (11, "♣"), (13, "♣"), (5, "♦"), (4, "♥")], "Flush"))

results.append(check_category(
    "Straight (regular)", [(9, "♥"), (8, "♠")],
    [(7, "♦"), (6, "♣"), (5, "♥"), (2, "♣"), (14, "♦")], "Straight"))

results.append(check_category(
    "Wheel Straight (A-2-3-4-5)", [(14, "♥"), (2, "♠")],
    [(3, "♦"), (4, "♣"), (5, "♥"), (9, "♣"), (10, "♦")], "Straight"))

results.append(check_category(
    "Three of a Kind", [(4, "♥"), (4, "♠")],
    [(4, "♦"), (9, "♣"), (2, "♦"), (5, "♣"), (7, "♥")], "Three of a Kind"))

results.append(check_category(
    "Two Pair", [(8, "♥"), (8, "♠")],
    [(3, "♦"), (3, "♣"), (2, "♦"), (5, "♣"), (7, "♥")], "Two Pair"))

results.append(check_category(
    "One Pair", [(10, "♥"), (10, "♠")],
    [(3, "♦"), (9, "♣"), (2, "♦"), (5, "♣"), (7, "♥")], "One Pair"))

results.append(check_category(
    "High Card", [(2, "♥"), (7, "♠")],
    [(9, "♦"), (11, "♣"), (4, "♦"), (5, "♣"), (13, "♥")], "High Card"))

print()
print("=== Kicker / Tiebreak Tests ===")
results.append(kicker_test(
    # Board has an Ace, which is a shared kicker available to both players'
    # two-pair hands, so this is correctly a TRUE TIE.
    "Two Pair: shared Ace kicker on board -> true tie",
    [(12, "♠"), (7, "♣")], [(11, "♥"), (9, "♠")],
    [(3, "♣"), (3, "♦"), (14, "♥"), (2, "♦"), (2, "♥")],
    "TRUE TIE"))

results.append(kicker_test(
    "Pure High Card: K-high vs 10-high",
    [(13, "♠"), (4, "♣")], [(10, "♥"), (6, "♦")],
    [(8, "♣"), (5, "♦"), (3, "♥"), (2, "♠"), (7, "♣")],
    "Player 1"))

results.append(kicker_test(
    "High Card: A-Q vs A-J",
    [(14, "♠"), (12, "♣")], [(14, "♥"), (11, "♦")],
    [(8, "♣"), (5, "♦"), (3, "♥"), (2, "♠"), (7, "♣")],
    "Player 1"))

results.append(kicker_test(
    "True tie: identical best 5",
    [(4, "♠"), (3, "♣")], [(4, "♥"), (3, "♦")],
    [(14, "♣"), (13, "♦"), (12, "♥"), (11, "♠"), (10, "♣")],
    "TRUE TIE"))

results.append(kicker_test(
    "One Pair: K kicker vs 9 kicker",
    [(13, "♠"), (2, "♣")], [(9, "♥"), (3, "♦")],
    [(8, "♣"), (8, "♦"), (5, "♥"), (4, "♠"), (6, "♣")],
    "Player 1"))

results.append(kicker_test(
    "Two Pair: Q kicker vs J kicker",
    [(12, "♠"), (4, "♣")], [(11, "♥"), (4, "♦")],
    [(3, "♣"), (3, "♦"), (9, "♥"), (2, "♦"), (2, "♥")],
    "Player 1"))

results.append(kicker_test(
    "High Card: board dominates -> true tie",
    [(2, "♠"), (3, "♣")], [(2, "♥"), (4, "♦")],
    [(14, "♣"), (13, "♦"), (12, "♥"), (11, "♠"), (9, "♣")],
    "TRUE TIE"))

print()
print(str(sum(results)) + "/" + str(len(results)) + " tests passed")
if all(results):
    print("ALL TESTS PASSED")
else:
    print("SOME TESTS FAILED")
