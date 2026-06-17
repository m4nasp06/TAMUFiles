# convert the returns to prints for all functions


def remove_string(s, n):
    if n == 0:
        print(s)
    elif n >= len(s):
        print("too long")
    elif n == len(s):
        print("empty string")
    else:
        print(s[n:])


# print(remove_string("abcdefg", 3))  # evaluates to 'defg'
# print(remove_string("abcdefg", 10))  # evaluates to 'too long'
# print(remove_string("abcdefg", 7))  # evaluates to 'empty string'
# print(remove_string("abcdefg", 0))  # evaluates to 'abcdefg'


def f_list(list, n):
    if len(list) == 0:
        print("empty list")
    elif n == 1:
        print(max(list))
    elif n == 2:
        print(sum(list))
    else:
        print("Invalid op")


# print(f_list([1, 2, 3, 4], 1))  # evaluates to 4
# print(f_list([1, 2, 3, 4], 2))
# print(f_list([1, 2, 3, 4], 0))  # evaluates to "Invalid op"
# print(f_list([], 1))  # evaluates to "empty list"
# print(f_list([], 0))  # evaluates to "empty list"


def check_access(role, level, badges):
    if role == "admin" and level == 5:
        allowed = True
    elif role == "staff" and level >= 2 and ("tech" in badges or "security" in badges):
        allowed = True
    else:
        allowed = False
    print(allowed)


# print(check_access("admin", 5, ["basic"]))                      # evaluates to True
# print(check_access("admin", 4, ["tech"]))                       # evaluates to False
# print(check_access("staff", 3, ["tech", "parking"]))            # evaluates to True
# print(check_access("staff", 2, ["maintenance"]))                # evaluates to False
# print(check_access("staff", 2, ["maintenance", "security"]))    # evaluates to True
# print(check_access("staff", 1, ["tech", "security"]))           # evaluates to False
