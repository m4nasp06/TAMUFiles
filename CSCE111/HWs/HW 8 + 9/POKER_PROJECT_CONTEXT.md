# Texas Hold'em Poker — Project Context

## Overview
A text-based Texas Hold'em Poker game written in Java, designed for 2–6 human players
playing on the same machine. Players enter actions via text input. The project is built
in layers: bare-bones terminal first, then text-art card rendering, then optionally a
JavaFX/Swing GUI later.

---

## Game Rules Summary
- Each player is dealt 2 private hole cards
- 5 community cards are revealed in stages: Flop (3), Turn (1), River (1)
- Betting rounds occur after each stage: Pre-Flop, Flop, Turn, River
- Available actions: check, bet/raise, call, fold, all-in
- Best 5-card hand from any combination of hole + community cards wins the pot
- Two players post forced bets each hand: Small Blind and Big Blind
- Dealer button rotates clockwise each hand
- Game ends when one player holds all the chips

---

## Classes

### Card
Represents a single playing card.

**Attributes:**
- `rank` — private int — Numeric rank (2–14, where 11=J, 12=Q, 13=K, 14=A)
- `suit` — private String — "H", "S", "C", or "D"

**Methods:**
- `Card(int rank, String suit)` — constructor — initializes rank and suit
- `getRank()` → int — returns numeric rank
- `getSuit()` → String — returns suit string
- `toString()` → String — returns display string e.g. "A-S" or "10-H"

---

### Deck
Manages a full 52-card collection.

**Attributes:**
- `cards` — private ArrayList\<Card\> — all cards currently in the deck

**Methods:**
- `Deck()` — constructor — builds all 52 cards
- `shuffle()` → void — randomizes order using Math.random() Fisher-Yates
- `dealCard()` → Card — removes and returns top card

---

### Hand
Holds and tracks a player's cards and evaluated hand strength.

**Attributes:**
- `holeCards` — private ArrayList\<Card\> — the 2 private cards dealt to the player
- `handValue` — private int — best hand rank (0=High Card … 9=Royal Flush)
- `yourHand` — private ArrayList\<Card\> — best 5-card combination found

**Methods:**
- `Hand()` — constructor — initializes empty hand
- `addCard(Card c)` → void — adds a card to hole cards
- `clearHand()` → void — resets hand for new round
- `setHandValue(int value, ArrayList<Card> best)` → void — sets handValue and yourHand
- `getHandValue()` → int — returns handValue integer
- `getHoleCards()` → ArrayList\<Card\> — returns hole cards
- `getYourHand()` → ArrayList\<Card\> — returns best 5-card combo (yourHand)

---

### Player
Represents a human player. Reads decisions from Scanner (text input).

**Attributes:**
- `name` — private String — player's display name
- `chips` — private int — current chip stack
- `hand` — private Hand — player's current Hand object
- `currentBet` — private int — amount wagered this betting round
- `folded` — private boolean — true if player has folded this hand
- `allIn` — private boolean — true if player is all-in

**Methods:**
- `Player(String name, int chips)` — constructor
- `placeBet(int amount)` → int — deducts chips, updates currentBet, returns actual amount
- `fold()` → void — sets folded to true
- `receiveChips(int amount)` → void — adds chips when winning pot
- `resetNewRound()` → void — resets fold/allIn/bet state, clears hand
- `isFolded()` → boolean
- `isAllIn()` → boolean
- `getChips()` → int
- `getName()` → String
- `getHand()` → Hand
- `makeDecision(int currentBet, Pot pot)` → void — prompts via Scanner in a while loop (re-prompts on invalid input), handles fold/call/raise

---

### Pot
Tracks all wagered chips for the current hand.

**Attributes:**
- `totalChips` — private int — total chips in the pot
- `currentBet` — private int — highest bet this round others must match

**Methods:**
- `Pot()` — constructor — initializes both to 0
- `addChips(int amount)` → void — adds to pot total
- `setCurrentBet(int bet)` → void — updates round's highest bet
- `getCurrentBet()` → int
- `getPotSize()` → int
- `reset()` → void — resets pot to 0 for new hand

---

### HandEvaluator
Utility class. Given 7 cards (2 hole + 5 community), finds and ranks the best 5-card hand.

**Hand rank scale:**
```
0 = High Card
1 = One Pair
2 = Two Pair
3 = Three of a Kind
4 = Straight
5 = Flush
6 = Full House
7 = Four of a Kind
8 = Straight Flush
9 = Royal Flush
```

**Attributes:**
- `HAND_NAMES` — public static String[] — maps rank int (0–9) to display name

**Methods:**
- `evaluate(Hand hand, ArrayList<Card> community)` → int — combines hole+community, tries all C(7,5)=21 combos via nested skip-two-index loops, calls hand.setHandValue(), returns best rank
- `handValue(ArrayList<Card> five)` → int (private) — one-pass evaluator: builds freq[15] array + flush boolean, counts pairs/trips/quads, calls isStraight(), returns rank 0–9
- `isStraight(int[] freq)` → boolean (private) — clones freq, sets adjusted[1]=adjusted[14] for Ace-low, counts 5 consecutive non-zero entries

---

### PokerGame
Top-level controller. Manages game loop, players, board, deck, pot, and round progression.

**Attributes:**
- `players` — private ArrayList\<Player\> — all active players (2–6)
- `deck` — private Deck — current hand's deck
- `pot` — private Pot — current hand's pot
- `communityCards` — private ArrayList\<Card\> — shared board cards
- `dealerIndex` — private int — index of current dealer in players list
- `smallBlind` — private int — small blind chip value
- `bigBlind` — private int — big blind chip value (always 2× smallBlind)

**Methods:**
- `PokerGame(int numPlayers, int startingChips, int smallBlind)` — constructor — prompts for names, sets up all state
- `startGame()` → void — outer loop, plays hands until one player remains
- `playHand()` → void — runs one full hand (shuffle → deal → blinds → betting → showdown)
- `dealHoleCards()` → void — deals 2 cards to each active player
- `dealCommunityCards(int count)` → void — deals count cards to board
- `bettingRound()` → void — loops active players, calls makeDecision(), updates pot
- `showdown()` → void — evaluates all hands, compares, awards pot
- `postBlinds()` → void — forces SB and BB bets from correct players
- `eliminateBrokePlayers()` → void — removes 0-chip players
- `displayGameState()` → void — prints board, pot, and each player's chip count

---

## Data Types Used
- **Primitives:** `int`, `boolean`
- **Java built-ins:** `String`, `String[]`
- **Collections:** `ArrayList<Card>`, `ArrayList<Player>`, `int[]`
- **Custom classes:** `Card`, `Hand`, `Pot`, `Deck`, `Player`

---

## Class Relationships
- `Deck` has a list of `Card`
- `Player` has a `Hand`
- `Hand` has a list of `Card`
- `PokerGame` has a `Deck`, a `Pot`, a list of `Player`, and a list of `Card` (community)
- `PokerGame` uses `HandEvaluator` at showdown
- `HandEvaluator` uses `Card`

---

## Display Conventions
- Cards displayed as letter-dash-letter: `A-S`, `10-H`, `K-D`, `2-C`
- Suits: S = Spades, H = Hearts, D = Diamonds, C = Clubs
- No Unicode suit symbols (terminal compatibility)

---

## Build Layers (in order)
1. **Bare bones** — fully working text-based game (current goal)
2. **Text art** — box-drawn cards in terminal using ASCII/box characters
3. **GUI** — JavaFX or Swing with visual table layout (resume piece)

---

## Notes
- Use `Math.random()` for all randomness (no `Random` class)
- No AI players — all players are human, entering input via Scanner
- `HandEvaluator` is a separate utility class (not a method on Hand) — keeps evaluation logic decoupled from data
- `Player` is a plain concrete class — no inheritance needed
- Start coding order: Card → Deck → Hand → Player → Pot → PokerGame (basic loop) → HandEvaluator (hardest, save for last)
