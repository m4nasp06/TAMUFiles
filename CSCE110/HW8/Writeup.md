# HW8: Object Oriented Programming 2 - Writeup

## 1. Game

Texas Hold'em Poker. Each player gets 2 hole cards, and 5 community cards get
dealt out in stages (flop, turn, river). Players bet in rounds and can fold,
check, call, raise, or go all in. Best 5-card hand out of the 7 available
(2 hole + 5 community) wins the pot at showdown.

## 2. Objects in the game

- Cards (rank + suit)
- A deck of cards
- A player's hole cards / hand
- Players
- The pot / chips
- The community cards ("the board")
- The dealer button / blinds

## 3. Classes

- `PlayingCard`: one playing card
- `CardDeck`: the 52-card deck, shuffling and dealing
- `PlayerHand`: a player's hole cards plus their best evaluated 5-card hand
- `BettingPot`: chips in the middle and the current bet to call
- `Participant`: a person at the table (chips, hand, betting actions)
- `HandRanker`: ranks a hand and breaks ties
- `TexasHoldEm`: runs the actual game loop (blinds, betting rounds, showdown)

## 4. Relationships

- `TexasHoldEm` has many `Participant`s, one `CardDeck`, one `BettingPot`, and
  a list of `PlayingCard`s for the board.
- Each `Participant` has one `PlayerHand`.
- Each `PlayerHand` has a list of `PlayingCard`s (hole cards) and a list of
  `PlayingCard`s (best 5-card hand once evaluated).
- `HandRanker` takes in a `PlayerHand` and the board and sets the hand's
  rank. It doesn't own any of them, just operates on them.

## 5. Attributes

- `PlayingCard`: `rank`, `suit`
- `CardDeck`: `cards` (list of `PlayingCard`)
- `PlayerHand`: `hole_cards`, `best_rank`, `best_five`
- `BettingPot`: `chips`, `call_amount`
- `Participant`: `name`, `chips`, `hand`, `wagered`, `folded`, `all_in`
- `TexasHoldEm`: `participants`, `deck`, `pot`, `board`, `button`,
  `small_blind`, `big_blind`, `ranker`

## 6. Methods

- `PlayingCard`: `__str__`, `art` (ascii-art card), `render_row` (prints a
  row of cards side by side)
- `CardDeck`: `shuffle`, `draw`
- `PlayerHand`: `deal_in`, `reset`, `record_result`
- `BettingPot`: `collect`, `clear`
- `Participant`: `wager`, `fold`, `award`, `start_new_hand`,
  `start_new_street`, `take_turn` (prompts for fold/call/check/raise/all-in)
- `HandRanker`: `best_of` (finds best 5-card hand and its rank), `score`
  (rates a 5-card hand), `has_straight` (checks for a straight, including the
  A-2-3-4-5 wheel), `compare` (breaks ties by kicker)
- `TexasHoldEm`: `run`, `play_one_hand`, `post_blinds`, `deal_hole_cards`,
  `run_betting`, `deal_board_cards`, `showdown`, `remove_busted`

## Notes

- Code prints game state after every street (hole cards, community cards,
  chip counts, pot size) and at showdown.
- User input for moves is handled in `Participant.take_turn`.
- Dealer button and blinds rotate every hand; players with 0 chips get
  eliminated between hands.
