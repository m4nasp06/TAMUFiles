def addition():
    while True:
        vals = input("Enter two numbers: ").split()

        if len(vals) > 2:
            print("You entered too many numbers. Try again.")
            continue
        if len(vals) < 2:
            print("You entered too few numbers. Try again.")
            continue

        try:
            n1 = int(vals[0])
        except ValueError:
            print("The first value is not a number. Try again.")
            continue

        try:
            n2 = int(vals[1])
        except ValueError:
            print("The second value is not a number. Try again.")
            continue

        print(f"{n1} + {n2} = {n1 + n2}")
        return


def listSum(lst):
    tot = 0
    for i, e in enumerate(lst):
        try:
            tot += e
        except TypeError as err:
            # bad element hit, report it and bail
            return f"Addition of element {i + 1} raises error : {err}"
    return tot


def remove_evens(lst):
    rm = []
    for i, e in enumerate(lst):
        if isinstance(e, (int, float)) and e % 2 == 0:
            rm.append(i)

    # pop back to front so earlier indices don't shift
    for i in reversed(rm):
        lst.pop(i)
