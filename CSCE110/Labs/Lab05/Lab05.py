def toUpperCase(letter):
    return chr(ord(letter) - 32)


def numbers(n):
    nn = n * 10 + n
    nnn = n * 100 + n * 10 + n
    return n + nn + nnn


def banner(name, frameChar):
    frame = frameChar * (len(name) + 5)
    print(f"{frame} {name} {frame}")


def getName():
    return input("Enter a name: ")


def multipleBanners():
    for _ in range(4):
        banner(getName(), '#')
