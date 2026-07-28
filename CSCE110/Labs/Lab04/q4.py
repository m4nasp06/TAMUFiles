date = input("Enter date in US format: ")
mm = date[:2]
dd = date[3:5]
yyyy = date[6:]

print(f"Date in ISO 8601 format: {yyyy}-{mm}-{dd}")
