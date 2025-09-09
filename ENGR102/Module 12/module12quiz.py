str = "TRHSRHTEEYEOUATALNNHNIIDISFVENWEEVGETRE"
# As an example, a strip of paper is wound around a rod eight times such that five letters are displayed
# around the circumference (a key of five). The message “THE ANSWER TO LIFE THE UNIVERSE AND
# EVERYTHING” is written across the paper, omitting the spaces. When the paper is unwound, it reads
# “TRHSRHTEEYEOUATALNNHNIIDISFVENWEEVGETRE” (vertically), as shown below. To decrypt the
# message, wrap the paper strip around a rod of the same diameter and read across. In this example,
# every fifth letter (key = 5) will appear on the same line.

# Write a program that decrypts the message in the string str. The key is 5.
# decrypt message returns string


# print(decrypt_message(str, 5))
test_str = "IOUBWRMCLNLIOMAVPETGINERMHRNSFOSPAG"

def decrypt_message(str, key):
    decrypted_message = ""
    for i in range(key):
        for j in range(i, len(str), key):
            decrypted_message += str[j]
    return decrypted_message


with open('C:/Users/sripa/Desktop/TAMUFiles/ENGR102/Module 12/module12quizF24.txt', "r") as inp_file:
    data = []
    matrix_data = []

    for line in inp_file:
        data.append(int(line.strip()))

    for i in range(0,100,10):
        matrix_data.append(data[i:i+10])

    sum_row_seven = 0
    for i in range(10):
        sum_row_seven += matrix_data[6][i]

    sum_column_five = 0
    for i in range(10):
        sum_column_five += matrix_data[i][4]


    matrix_mode = 1
    for i in range(1,10):
        if data.count(i) > data.count(matrix_mode):
            matrix_mode = i

    key = sum_row_seven - sum_column_five + matrix_mode
print(key)
print(decrypt_message(test_str, key))


quiz_str = "ENISNENFGEGUIRIN"
quiz_key = 4
print(decrypt_message(quiz_str, quiz_key))


