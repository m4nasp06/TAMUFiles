// add more includes as necessary
#include "functions.h"
#include <iostream>
#include <string>
#include <sstream>

// deobfuscate a sentence
// arg 1: obfuscated sentence
// arg 2: deobfuscation details
// returns the deobfuscated sentence
std::string deobfuscate(const std::string& sentence, const std::string& details) {
    // TODO(student)
    // sentence is "Thisisasentence" details is "4218"
    // returns "This is a sentence" 4 letters 2letters 1 letter 8 letters
    std::string result;
    std::size_t pos = 0;
    bool first = true;

    for (char ch : details) {
        int len = ch - '0';
        if (len <= 0) {
            return ""; // invalid detail
        }
        if (pos + len > sentence.size()) {
            return ""; // detail too long
        }
        if (!first) result += ' ';
        result += sentence.substr(pos, len);
        pos += len;
        first = false;
    }

    return result;
}

// replace filter word with octothorpes (#)
// arg 1: sentence
// arg 2: filter word
// returns the filtered sentence
std::string wordFilter(const std::string& sentence, const std::string& filter) {
    // TODO(student)
    // Replace all instances of the filter word in the sentence with octothorpes (#)
    if (filter.empty()) return sentence;
    std::string result;
    std::size_t pos = 0;
    while (pos < sentence.size()) {
        std::size_t found = sentence.find(filter, pos);
        if (found == std::string::npos) {
            result += sentence.substr(pos);
            break;
        }

        result += sentence.substr(pos, found - pos);

        // left check
        bool left_check = (found == 0 || !std::isalpha(sentence[found - 1]));
        // right check
        bool right_check = (found + filter.size() == sentence.size() || !std::isalpha(sentence[found + filter.size()]));

        if (left_check  && right_check) {
            result += std::string(filter.size(), '#');
        } else {
            result += filter;
        }
        pos = found + filter.size();

    }
    return result;
}

// convert a string to a secure password
// arg 1: text
// returns a secure password based on the text
std::string passwordConverter(const std::string&) {
    // TODO(student)
    return "";
}

// calculate the result of an arithmetic expression in words
// arg 1: expression using words
// returns an arithmetic equation using numerals and arithmetic symbols
std::string wordCalculator(const std::string&) {
    // TODO(student)
    return "";
}

// count the palindromes in the text
// arg 1: text
// returns the number of palindromes in the text
unsigned int palindromeCounter(const std::string& text) {
    // TODO(student)

    std::istringstream iss(text);
    std::string word;
    unsigned int count = 0;

    while(iss>>word) {
        bool is_palindrome = true;
        size_t i = 0, j = word.size() - 1;
        while (i < j) {
            if (word[i] != word[j]) {
                is_palindrome = false;
                break;
            }
            i++;
            j--;
        }
        if (is_palindrome) count++;
    }
    return count;
}

