#include <iostream>
#include <string>

using namespace std;

unsigned int countLetters(std::string str);

int main() {
    cout << countLetters("Hello, World!") << endl;
    cout << "Hello, World!" << endl;
    return 0;
}

// unsigned int countLetters(std::string str) {
//     // this function should count the number of letters in a string
//     // TODO(student): find and fix the error

//     std::cout << "--- countLetters ---" << std::endl;

//     unsigned int count = 0;
//     for (unsigned int i=0; i<str.size(); ++i) {
//             if (isalpha(static_cast<unsigned char>(str.at(i)))) {
//             count++;
//         }
//         else {
//             cout << "Non-letter character: " << str.at(i) << endl;
//         }
//     }
//     return count;
// }
unsigned int countLetters(std::string str) {
    // this function should count the number of letters in a string
    // TODO(student): find and fix the error

    std::cout << "--- countLetters ---" << std::endl;

    unsigned int count = 0;
    for (unsigned int i=0; i<str.size(); ++i) {
        if (isalpha(static_cast<unsigned char>(str.at(i)))) {
            count++;
        }
        else {
            cout << "Non-letter character: " << str.at(i) << endl;
        }
    }
    return count;
}