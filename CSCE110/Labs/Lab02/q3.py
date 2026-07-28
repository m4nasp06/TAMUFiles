seconds = int(input("Enter number of seconds: "))
hours = seconds // 3600
minutes = (seconds % 3600) // 60
secs = seconds % 60

print(f"{seconds} seconds = {hours} hours, {minutes} minutes and {secs} seconds")
