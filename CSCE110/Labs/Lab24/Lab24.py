def writeFile(filename, content):
    file = open(filename, "w")
    file.write(content)
    file.close()

def countAs(filename):
    file = open(filename, "r")
    text = file.read()
    file.close()

    count = 0
    for letter in text:
        if letter == "A" or letter == "a":
            count += 1

    return count

def summary(inputFile, outputFile):
    input_file = open(inputFile)
    numbers = []
    for line in input_file:
        for token in line.split():
            numbers.append(float(token))
    input_file.close()

    total = sum(numbers)
    average = total / len(numbers)

    output_file = open(outputFile, "w")
    output_file.write(f"Sum is: {total:.2f}\n")
    output_file.write(f"Average is: {average:.2f}\n")
    output_file.close()
