# from Hw06 import convertTwoDigit, convertThreeDigit, amountInWords
# # before submitting to gradescope, swap the import above for:
from Hw06_Instrumented import amountInWords, convertTwoDigit, convertThreeDigit


def convertTwoDigit_test():
    tests = [
        (57, 'Fifty Seven'),
        (0, ''),
        (5, 'Five'),
        (13, 'Thirteen'),
        (20, 'Twenty'),
        (30, 'Thirty'),
        (61, 'Sixty One'),
        (99, 'Ninety Nine'),
    ]
    for num, exp in tests:
        got = convertTwoDigit(num)
        res = 'Pass' if got == exp else 'Fail'
        print(f"convertTwoDigit({num}) = '{got}' | Expected = '{exp}' | {res}")


def convertThreeDigit_test():
    tests = [
        (571, 'Five Hundred Seventy One'),
        (600, 'Six Hundred'),
        (61, 'Sixty One'),
        (0, ''),
        (100, 'One Hundred'),
        (999, 'Nine Hundred Ninety Nine'),
    ]
    for num, exp in tests:
        got = convertThreeDigit(num)
        res = 'Pass' if got == exp else 'Fail'
        print(f"convertThreeDigit({num}) = '{got}' | Expected = '{exp}' | {res}")


def amountInWords_test():
    tests = [
        (
            57167832567,
            'Fifty Seven Billion One Hundred Sixty Seven Million '
            'Eight Hundred Thirty Two Thousand Five Hundred Sixty Seven',
        ),
        (61, 'Sixty One'),
        (0, ''),
        (1000, 'One Thousand'),
        (1000000, 'One Million'),
        (100, 'One Hundred'),
    ]
    for num, exp in tests:
        got = amountInWords(num)
        res = 'Pass' if got == exp else 'Fail'
        print(f"amountInWords({num}) = '{got}' | Expected = '{exp}' | {res}")
