loan = 15000
rate_pct = float(input("Enter interest rate (in percentage) = "))
years = float(input("Enter loan period = "))

r = rate_pct / 100
payoff = loan * (1 + r) ** years

print(f"At {rate_pct}% interest rate, you will need to pay ${round(payoff, 2)} in {years} years.")
