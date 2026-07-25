def remove_string(s, n):
    if n == 0:
        return s
    elif n == len(s):
        return "empty string"
    elif n > len(s):
        return "too long"
    else:
        return s[n:]


# remove_string('abcdefg', 3)    # evaluates to 'defg'
# remove_string('abcdefg', 10)   # evaluates to 'too long'
# remove_string('abcdefg', 7)    # evaluates to 'empty string'
# remove_string('abcdefg', 0)    # evaluates to 'abcdefg'


def f_list(lst, n):
    if len(lst) == 0:
        return "empty list"
    elif n == 1:
        return max(lst)
    elif n == 2:
        return sum(lst)
    else:
        return "Invalid op"


# f_list([1, 2, 3, 4], 1)                  # evaluates to 4
# f_list([1, 2, 3, 4], 2)                  # evaluates to 10
# f_list([1, 2, 3, 4], 0)                  # evaluates to "Invalid op"
# f_list([], 1)                            # evaluates to "empty list"
# f_list([], 0)                            # evaluates to "empty list"


def check_access(role, level, badges):
    if role == "admin" and level == 5:
        allowed = True
    elif role == "staff" and level >= 2 and ("tech" in badges or "security" in badges):
        allowed = True
    else:
        allowed = False
    return allowed


# check_access("admin", 5, ["basic"])                      # evaluates to True
# check_access("admin", 4, ["tech"])                       # evaluates to False
# check_access("staff", 3, ["tech", "parking"])            # evaluates to True
# check_access("staff", 2, ["maintenance"])                # evaluates to False
# check_access("staff", 2, ["maintenance", "security"])    # evaluates to True
# check_access("staff", 1, ["tech", "security"])           # evaluates to False
