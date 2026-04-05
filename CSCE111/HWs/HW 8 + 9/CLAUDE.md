# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Text-based Texas Hold'em Poker game written in Java (CSCE 111 HW 8+9). Supports 2–6 human players on one machine via terminal text input.

## Build & Run

```bash
# Compile all Java files
javac *.java

# Run the game
java PokerGame

# Compile and run a single file for testing
javac Card.java && java Card
```

## Architecture

Seven classes with a clear dependency order. Implement in this sequence:

1. **`Card`** — rank (int 2–14) + suit (String). No dependencies.
2. **`Deck`** — `ArrayList<Card>`, 52 cards, Fisher-Yates shuffle via `Math.random()`.
3. **`Hand`** — holds hole cards + evaluated best hand. No logic, just data.
4. **`Player`** — owns a `Hand`, chip stack, and fold/all-in state. Reads decisions from `Scanner`.
5. **`Pot`** — tracks total chips and current round's highest bet.
6. **`PokerGame`** — top-level game loop controller. Has `Deck`, `Pot`, `ArrayList<Player>`, and community `ArrayList<Card>`.
7. **`HandEvaluator`** — utility class. Evaluates all C(7,5)=21 combos of 7 cards; hand ranks 0–9 (High Card → Royal Flush).

Key relationships:
- `PokerGame` calls `HandEvaluator.evaluate()` at showdown only
- `HandEvaluator` returns rank and sets best 5-card combo on `Hand` via `setHandValue()`
- `Player.makeDecision(int currentBet, Pot pot)` → void — uses while loop for input validation, handles fold/call/raise
- `Player` exposes `isFolded()` and `isAllIn()` getters

## Rules & Constraints

- Use `Math.random()` for all randomness — no `Random` class
- No AI players — all input is via `Scanner`
- Card display format: `A-S`, `10-H`, `K-D`, `2-C` (no Unicode suit symbols)
- Blind structure: bigBlind = 2 × smallBlind; dealer button rotates clockwise each hand
- Game ends when one player holds all chips

## Hand Rank Scale

```
0=High Card, 1=One Pair, 2=Two Pair, 3=Three of a Kind,
4=Straight, 5=Flush, 6=Full House, 7=Four of a Kind,
8=Straight Flush, 9=Royal Flush
```
