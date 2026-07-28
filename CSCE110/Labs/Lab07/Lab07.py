def list_as_queue():
    queue = []
    queue.append(input("Welcome to our Coffee Shop. What is your name : "))
    queue.append(input("Welcome to our Coffee Shop. What is your name : "))
    queue.append(input("Welcome to our Coffee Shop. What is your name : "))
    queue.append(input("Welcome to our Coffee Shop. What is your name : "))
    queue.append(input("Welcome to our Coffee Shop. What is your name : "))

    print(f"Here is your coffee {queue.pop(0)}. Have a nice day.")
    print(f"Here is your coffee {queue.pop(0)}. Have a nice day.")
    print(f"Here is your coffee {queue.pop(0)}. Have a nice day.")
    print(f"Here is your coffee {queue.pop(0)}. Have a nice day.")
    print(f"Here is your coffee {queue.pop(0)}. Have a nice day.")


def list_as_stack():
    stack = []
    stack.append(input("Thanks for submitting. What is your name: "))
    stack.append(input("Thanks for submitting. What is your name: "))
    stack.append(input("Thanks for submitting. What is your name: "))
    stack.append(input("Thanks for submitting. What is your name: "))

    name = stack.pop()
    print(f"{name}, your score is: {ord(name[0])}")
    name = stack.pop()
    print(f"{name}, your score is: {ord(name[0])}")
    name = stack.pop()
    print(f"{name}, your score is: {ord(name[0])}")
    name = stack.pop()
    print(f"{name}, your score is: {ord(name[0])}")


def list_test():
    nums = [
        int(input("1st : ")),
        int(input("2nd : ")),
        int(input("3rd : ")),
        int(input("4th : ")),
    ]
    nums.sort()
    print(f"Ascending Order : {nums}")
    nums.sort(reverse=True)
    print(f"Descending Order : {nums}")


def process_line():
    record = input("Enter Student's Record:")
    uin, first, last, activities, labs, hw, exams = record.split(",")
    activities = float(activities)
    labs = float(labs)
    hw = float(hw)
    exams = float(exams)
    total = activities + labs + hw + exams

    print("Formatted Result:")
    print(f"{'UIN':<12}{'Last Name':<12}{'First Name':<12}{'Activities':<12}{'Labs':<12}{'Homework':<12}{'Exams':<12}Total")
    print(f"{uin.strip():<12}{last.strip():<12}{first.strip():<12}{activities:<12.2f}{labs:<12.2f}{hw:<12.2f}{exams:<12.2f}{total:.2f}")


def dot_product(l1, l2):
    return l1[0] * l2[0] + l1[1] * l2[1] + l1[2] * l2[2]
