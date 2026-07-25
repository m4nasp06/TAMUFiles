import math
import random


# Q1 Part A: Amounts Dictionary
amounts = {
    1: 'One', 2: 'Two', 3: 'Three', 4: 'Four', 5: 'Five',
    6: 'Six', 7: 'Seven', 8: 'Eight', 9: 'Nine', 10: 'Ten',
    11: 'Eleven', 12: 'Twelve', 13: 'Thirteen', 14: 'Fourteen', 15: 'Fifteen',
    16: 'Sixteen', 17: 'Seventeen', 18: 'Eighteen', 19: 'Nineteen', 20: 'Twenty',
    30: 'Thirty', 40: 'Forty', 50: 'Fifty', 60: 'Sixty',
    70: 'Seventy', 80: 'Eighty', 90: 'Ninety',
}


# Q1 Part B: Two Digit Conversion
def convertTwoDigit(num):
    if num == 0:
        return ''
    if num <= 20:
        return amounts[num]

    tens = (num // 10) * 10
    ones = num % 10
    words = amounts[tens]
    if ones > 0:
        words += ' ' + amounts[ones]
    return words


# Q1 Part C: Three Digit Conversion
def convertThreeDigit(num):
    if num == 0:
        return ''

    hund = num // 100
    rest = num % 100

    words = ''
    if hund > 0:
        words = amounts[hund] + ' Hundred'

    rest_words = convertTwoDigit(rest)
    if rest_words:
        words = words + ' ' + rest_words if words else rest_words

    return words


# Q1 Part D: Amount in Words
def amountInWords(num):
    if num == 0:
        return ''

    chunks = [
        (1_000_000_000, 'Billion'),
        (1_000_000, 'Million'),
        (1_000, 'Thousand'),
        (1, ''),
    ]

    words = []
    left = num
    for size, tag in chunks:
        part = left // size
        left = left % size
        if part == 0:
            continue

        part_words = convertThreeDigit(part)
        if tag:
            words.append(part_words + ' ' + tag)
        else:
            words.append(part_words)

    return ' '.join(words)


# Q2: Stock Price Trajectory Risk Assessment
def simulate_stock_risk(portfolio, risk_free_rate, days, num_trials):
    if num_trials <= 0 or days <= 0 or not portfolio:
        return {}

    dt = 1 / 252
    res = {}

    for stock in portfolio:
        ticker = stock["ticker"]
        price0 = stock["current_price"]
        vol = stock["volatility"]
        strike = stock["strike_price"]

        hits = 0
        for trial in range(num_trials):
            price = price0
            for day in range(days):
                z = random.gauss(0, 1)
                price = price * math.exp(
                    (risk_free_rate - vol**2 / 2) * dt + vol * z * math.sqrt(dt)
                )
            if price > strike:
                hits += 1

        res[ticker] = round(hits / num_trials, 4)

    return res
