def cleanAndSort():
    items = input("Enter a comma separated list:").split(",")
    print(f"List = {items}")
    unique = list(set(items))
    unique.sort()
    print(f"List without duplicate = {unique}")


def listOfTuples():
    students = []
    name = input("S1 : ").split()
    students.append((name[0], float(name[1])))
    name = input("S2 : ").split()
    students.append((name[0], float(name[1])))
    name = input("S3 : ").split()
    students.append((name[0], float(name[1])))
    name = input("S4 : ").split()
    students.append((name[0], float(name[1])))

    print("The list is:")
    print(students)


def testSets():
    s1 = set(input("String 1 : "))
    s2 = set(input("String 2 : "))
    universe = set("abcdefghijklmnopqrstuvwxyz")

    print(f"Set 1 : {sorted(s1)}")
    print(f"Set 2 : {sorted(s2)}")
    print(f"a: {sorted(s1 | s2)}")
    print(f"b: {sorted(s1 & s2)}")
    print(f"c: {sorted(s1 - s2)}")
    print(f"d: {sorted(s2 - s1)}")
    print(f"e: {sorted(universe - s1 - s2)}")
