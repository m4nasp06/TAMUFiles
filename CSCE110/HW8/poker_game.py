import random

SUIT_SYMBOLS = ["♠", "♥", "♦", "♣"]
FACE_NAMES = {11: "J", 12: "Q", 13: "K", 14: "A"}

HAND_NAMES = [
    "High Card",
    "One Pair",
    "Two Pair",
    "Three of a Kind",
    "Straight",
    "Flush",
    "Full House",
    "Four of a Kind",
    "Straight Flush",
    "Royal Flush",
]


class PlayingCard:
    def __init__(self, rank, suit):
        self.rank = rank
        self.suit = suit

    def __str__(self):
        if self.rank in FACE_NAMES:
            label = FACE_NAMES[self.rank]
        else:
            label = str(self.rank)
        return label + self.suit

    # ascii-art box used when printing a card on its own row
    def art(self):
        if self.rank in FACE_NAMES:
            label = FACE_NAMES[self.rank]
        else:
            label = str(self.rank)
        lines = [
            "┌───────┐",
            "|" + f"{label:<7}" + "|",
            "|       |",
            "|   " + self.suit + "   |",
            "|       |",
            "|" + f"{label:>7}" + "|",
            "└───────┘",
        ]
        return lines

    @staticmethod
    def render_row(cards):
        if not cards:
            return "[none]"
        boxes = []
        for card in cards:
            boxes.append(card.art())

        text = ""
        for row in range(7):
            for box in boxes:
                text += box[row] + "  "
            text += "\n"
        return text


class CardDeck:
    def __init__(self):
        self.cards = []
        for suit in SUIT_SYMBOLS:
            for rank in range(2, 15):
                self.cards.append(PlayingCard(rank, suit))

    def shuffle(self):
        random.shuffle(self.cards)

    def draw(self):
        return self.cards.pop()


class PlayerHand:
    def __init__(self):
        self.hole_cards = []
        self.best_rank = -1
        self.best_five = []

    def deal_in(self, card):
        self.hole_cards.append(card)

    def record_result(self, rank, five_cards):
        self.best_rank = rank
        self.best_five = five_cards

    def reset(self):
        self.hole_cards = []
        self.best_rank = -1
        self.best_five = []


class BettingPot:
    def __init__(self):
        self.chips = 0
        self.call_amount = 0

    def collect(self, amount):
        self.chips += amount

    def clear(self):
        self.chips = 0
        self.call_amount = 0


class Participant:
    def __init__(self, name, chips):
        self.name = name
        self.chips = chips
        self.hand = PlayerHand()
        self.wagered = 0
        self.folded = False
        self.all_in = False

    def wager(self, amount):
        self.chips -= amount
        self.wagered += amount
        if self.chips <= 0:
            self.all_in = True
        return amount

    def fold(self):
        self.folded = True

    def award(self, amount):
        self.chips += amount

    def start_new_hand(self):
        self.wagered = 0
        self.folded = False
        self.all_in = False
        self.hand.reset()

    def start_new_street(self):
        self.wagered = 0

    # prompts this participant for fold/check/call/raise/all-in
    def take_turn(self, call_amount, pot):
        input("--- Pass to " + self.name + " and press Enter ---")
        print("Your hole cards:")
        print(PlayingCard.render_row(self.hand.hole_cards))

        while True:
            print(self.name + ", your current chips: " + str(self.chips))
            print("Current bet to call: " + str(call_amount))
            print("Pot size: " + str(pot.chips))
            action = input("Enter your action (fold, call, check, raise, all in): ").lower()

            if action == "fold":
                self.fold()
                print()
                return
            elif action == "check":
                if call_amount > self.wagered:
                    print("You cannot check, there is a bet to call.")
                    continue
                print()
                return
            elif action == "call":
                self.wager(min(call_amount - self.wagered, self.chips))
                print()
                return
            elif action == "all in":
                print(self.name + " goes ALL IN with " + str(self.chips) + " chips!")
                self.wager(self.chips)
                print()
                return
            elif action == "raise":
                floor = min(call_amount - self.wagered + 1, self.chips)
                target = int(input("Enter total chips to put in (min " + str(floor) + ", max " + str(self.chips) + "): "))
                if target < floor or target > self.chips:
                    print("Invalid raise amount.")
                    continue
                self.wager(target)
                print()
                return
            else:
                print("Invalid action. Please try again.")


class HandRanker:
    # tries every 5-card combo out of the 7 available (2 hole + 5 board)
    # and keeps the strongest one
    def best_of(self, hand, board):
        pool = hand.hole_cards + board
        top_rank = -1
        top_five = []
        for i in range(7):
            for j in range(i + 1, 7):
                five = []
                for k in range(7):
                    if k != i and k != j:
                        five.append(pool[k])

                rank = self.score(five)
                if rank > top_rank or (rank == top_rank and self.compare(five, top_five, rank) > 0):
                    top_rank = rank
                    top_five = five

        hand.record_result(top_rank, top_five)
        return top_rank

    def score(self, five):
        rank_counts = [0] * 15
        first_suit = five[0].suit
        is_flush = True

        for card in five:
            rank_counts[card.rank] += 1
            if card.suit != first_suit:
                is_flush = False

        pairs = 0
        trips = 0
        quads = 0
        for count in rank_counts:
            if count == 2:
                pairs += 1
            elif count == 3:
                trips += 1
            elif count == 4:
                quads += 1

        is_straight = self.has_straight(rank_counts)

        if is_straight and is_flush and rank_counts[14] == 1 and rank_counts[10] == 1:
            return 9  # royal flush
        if is_straight and is_flush:
            return 8  # straight flush
        if quads == 1:
            return 7
        if trips == 1 and pairs == 1:
            return 6  # full house
        if is_flush:
            return 5
        if is_straight:
            return 4
        if trips == 1:
            return 3
        if pairs == 2:
            return 2
        if pairs == 1:
            return 1
        return 0  # high card

    def has_straight(self, rank_counts):
        adjusted = rank_counts.copy()
        adjusted[1] = adjusted[14]  # lets an Ace count low for A-2-3-4-5

        consecutive = 0
        for i in range(1, len(adjusted)):
            if adjusted[i] > 0:
                consecutive += 1
                if consecutive == 5:
                    return True
            else:
                consecutive = 0
        return False

    def sorted_ranks(self, cards):
        ranks = []
        for card in cards:
            ranks.append(card.rank)
        ranks.sort(reverse=True)
        return ranks

    # builds a list of ranks to compare hands of the same category by,
    # ordered so the most important rank (trips before kicker, etc) comes first
    def compare_key(self, five, rank):
        rank_counts = [0] * 15
        for card in five:
            rank_counts[card.rank] += 1

        if rank == 9:  # royal flush is always a tie
            return [0]
        if rank == 8 or rank == 4:  # straight flush or straight
            if rank_counts[14] > 0 and rank_counts[2] > 0 and rank_counts[3] > 0 and rank_counts[4] > 0 and rank_counts[5] > 0:
                return [5]
            for r in range(14, 1, -1):
                if rank_counts[r] > 0:
                    return [r]
            return [0]
        if rank == 7:  # four of a kind
            quads_rank = -1
            kicker = -1
            for r in range(14, 1, -1):
                if rank_counts[r] == 4:
                    quads_rank = r
                elif rank_counts[r] == 1 and kicker == -1:
                    kicker = r
            return [quads_rank, kicker]
        if rank == 6:  # full house
            trips_rank = -1
            pair_rank = -1
            for r in range(14, 1, -1):
                if rank_counts[r] == 3:
                    trips_rank = r
                elif rank_counts[r] == 2:
                    pair_rank = r
            return [trips_rank, pair_rank]
        if rank == 5 or rank == 0:  # flush or high card
            return self.sorted_ranks(five)
        if rank == 3:  # three of a kind
            trips_rank = -1
            kickers = []
            for r in range(14, 1, -1):
                if rank_counts[r] == 3:
                    trips_rank = r
                elif rank_counts[r] == 1 and len(kickers) < 2:
                    kickers.append(r)
            return [trips_rank] + kickers
        if rank == 2:  # two pair
            high_pair = -1
            low_pair = -1
            kicker = -1
            for r in range(14, 1, -1):
                if rank_counts[r] == 2:
                    if high_pair == -1:
                        high_pair = r
                    else:
                        low_pair = r
                elif rank_counts[r] == 1 and kicker == -1:
                    kicker = r
            return [high_pair, low_pair, kicker]
        if rank == 1:  # one pair
            pair_rank = -1
            kickers = []
            for r in range(14, 1, -1):
                if rank_counts[r] == 2:
                    pair_rank = r
                elif rank_counts[r] == 1 and len(kickers) < 3:
                    kickers.append(r)
            return [pair_rank] + kickers
        return self.sorted_ranks(five)

    def compare(self, five_a, five_b, rank):
        if not five_b:
            return 1
        key_a = self.compare_key(five_a, rank)
        key_b = self.compare_key(five_b, rank)
        for i in range(len(key_a)):
            if key_a[i] != key_b[i]:
                return key_a[i] - key_b[i]
        return 0


class TexasHoldEm:
    def __init__(self, participants, small_blind):
        self.participants = participants
        self.deck = CardDeck()
        self.pot = BettingPot()
        self.board = []
        self.button = 0
        self.small_blind = small_blind
        self.big_blind = small_blind * 2
        self.ranker = HandRanker()

    def run(self):
        while len(self.participants) > 1:
            sb_idx = (self.button + 1) % len(self.participants)
            button_player = self.participants[self.button]
            self.play_one_hand(sb_idx)
            self.remove_busted()
            if len(self.participants) > 1:
                if button_player in self.participants:
                    self.button = (self.participants.index(button_player) + 1) % len(self.participants)
                else:
                    self.button = self.button % len(self.participants)

        print("Game Over! Winner: " + self.participants[0].name)

    def play_one_hand(self, sb_idx):
        self.deck = CardDeck()
        self.deck.shuffle()
        self.board = []
        self.pot.clear()
        for p in self.participants:
            p.start_new_hand()

        self.post_blinds(sb_idx)
        self.deal_hole_cards()
        self.show_board()
        self.run_betting((sb_idx + 2) % len(self.participants))

        self.deal_board_cards(3)  # flop
        self.start_new_street()
        self.show_board()
        self.run_betting(sb_idx)

        self.deal_board_cards(1)  # turn
        self.start_new_street()
        self.show_board()
        self.run_betting(sb_idx)

        self.deal_board_cards(1)  # river
        self.start_new_street()
        self.show_board()
        self.run_betting(sb_idx)

        self.showdown()

    def show_board(self):
        if not self.board:
            return
        print("Community cards: ")
        print(PlayingCard.render_row(self.board))

    def start_new_street(self):
        self.pot.call_amount = 0
        for p in self.participants:
            p.start_new_street()

    def remove_busted(self):
        for i in range(len(self.participants) - 1, -1, -1):
            if self.participants[i].chips <= 0:
                print(self.participants[i].name + " is eliminated!")
                self.participants.pop(i)

    def post_blinds(self, sb_idx):
        bb_idx = (sb_idx + 1) % len(self.participants)
        sb_player = self.participants[sb_idx]
        bb_player = self.participants[bb_idx]

        sb_paid = min(self.small_blind, sb_player.chips)
        sb_player.wager(sb_paid)
        print(sb_player.name + " posts small blind: " + str(sb_paid))

        bb_paid = min(self.big_blind, bb_player.chips)
        bb_player.wager(bb_paid)
        print(bb_player.name + " posts big blind: " + str(bb_paid))

        self.pot.collect(sb_paid + bb_paid)
        self.pot.call_amount = self.big_blind

    def deal_hole_cards(self):
        for p in self.participants:
            p.hand.deal_in(self.deck.draw())
            p.hand.deal_in(self.deck.draw())

    def deal_board_cards(self, count):
        self.deck.draw()  # burn card
        for _ in range(count):
            self.board.append(self.deck.draw())

    def run_betting(self, start_idx):
        active = 0
        for p in self.participants:
            if not p.folded:
                active += 1
        if active <= 1:
            return

        call_amount = self.pot.call_amount
        someone_raised = True
        while someone_raised:
            someone_raised = False
            for offset in range(len(self.participants)):
                p = self.participants[(start_idx + offset) % len(self.participants)]
                if p.folded or p.all_in:
                    continue

                wagered_before = p.wagered
                p.take_turn(call_amount, self.pot)
                self.pot.collect(p.wagered - wagered_before)

                if p.wagered > call_amount:
                    call_amount = p.wagered
                    self.pot.call_amount = call_amount
                    someone_raised = True

                still_active = 0
                for q in self.participants:
                    if not q.folded:
                        still_active += 1
                if still_active <= 1:
                    return

    def showdown(self):
        still_in = []
        for p in self.participants:
            if not p.folded:
                still_in.append(p)

        top_rank = -1
        for p in still_in:
            rank = self.ranker.best_of(p.hand, self.board)
            top_rank = max(top_rank, rank)

        winners = []
        for p in still_in:
            if p.hand.best_rank == top_rank:
                winners.append(p)

        if len(winners) > 1:
            true_winners = [winners[0]]
            for i in range(1, len(winners)):
                cmp = self.ranker.compare(winners[i].hand.best_five, true_winners[0].hand.best_five, top_rank)
                if cmp > 0:
                    true_winners = [winners[i]]
                elif cmp == 0:
                    true_winners.append(winners[i])
            winners = true_winners

        share = self.pot.chips // len(winners)
        if len(winners) == 1:
            print(winners[0].name + " wins the pot of " + str(self.pot.chips) + " chips!")
            print("Winning hand: " + HAND_NAMES[top_rank])
            print(PlayingCard.render_row(winners[0].hand.best_five))
            print()
        else:
            print("Tie! Splitting pot of " + str(self.pot.chips) + " chips between " + str(len(winners)) + " players.")
            print("Tied hand: " + HAND_NAMES[top_rank])

        for w in winners:
            w.award(share)


def run_cli():
    print("Texas Hold'em Poker")

    num_players = 0
    while num_players < 2 or num_players > 6:
        num_players = int(input("Enter number of players (2-6): "))
        if num_players < 2 or num_players > 6:
            print("Must be between 2 and 6.")

    starting_chips = 0
    while starting_chips <= 0:
        starting_chips = int(input("Enter starting chips per player: "))

    small_blind = 0
    while small_blind <= 0:
        small_blind = int(input("Enter small blind amount: "))

    participants = []
    for i in range(num_players):
        name = input("Enter name for player " + str(i + 1) + ": ")
        participants.append(Participant(name, starting_chips))

    print()
    print("Starting game with " + str(num_players) + " players, " + str(starting_chips) +
          " chips each, blinds " + str(small_blind) + "/" + str(small_blind * 2))
    print()

    TexasHoldEm(participants, small_blind).run()


if __name__ == "__main__":
    run_cli()
