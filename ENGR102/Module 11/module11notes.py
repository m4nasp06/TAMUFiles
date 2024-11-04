#reading and writing from files

#to open file
# file_var_name= open('file_path', mode)
# modes are 'w', 'r', 'r+', 'a'

# my_file = open('my_file.txt', 'r+')

# my_file.close()

# an alternative:
# with open('my_file.txt', 'r+') as my_file:

# writing
# my_file.write('some text')
# only writes one line, only strings, need to have \n

# reading
# given_line = my_file.readline()

# reading multiple lines
# for line in my_file:
    # print(line)

# split() splits the string into a list of strings based on the delimiter
# strip() removes any leading or trailing whitespace
