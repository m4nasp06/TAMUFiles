def ssnValidation(ssn):
    if len(ssn) != 9 and len(ssn) != 11:
        raise ValueError("Invalid ssn length")

    if len(ssn) == 9:
        if not ssn.isdigit():
            raise ValueError("Invalid character found in 9 digit format")
        return ssn

    # 11 char format: digits everywhere except '-' at index 3 and 6
    for i, c in enumerate(ssn):
        if i == 3 or i == 6:
            if c != "-":
                raise ValueError("Invalid character found in 11 digit format")
        elif not c.isdigit():
            raise ValueError("Invalid character found in 11 digit format")

    return ssn[:3] + ssn[4:6] + ssn[7:]


class Toy:
    def setName(self, name):
        self.name = name

    def setRecommendedAge(self, lowerLimit, upperLimit=99):
        self.lower = lowerLimit
        self.upper = upperLimit

    def setPrice(self, price):
        self.price = price

    def isRecommended(self, age):
        return self.lower <= age <= self.upper

    def isInBudget(self, budget):
        return self.price < budget

    def toString(self):
        return (
            f"{self.name} is recommended for children {self.lower} to "
            f"{self.upper} years old and costs ${self.price:.2f}"
        )


class Dictionary:
    def __init__(self):
        self.lst = [None] * 100

    def set(self, key, value):
        idx = sum(ord(c) for c in key) % 100
        self.lst[idx] = (key, value)

    def get(self, key):
        idx = sum(ord(c) for c in key) % 100
        entry = self.lst[idx]
        if entry is None:
            raise KeyError(f"{key} not found in Dictionary")
        return entry[1]
