// These headers define some of the classes and functions we need
#include <iostream>
#include <string>
#include <sstream>
#include <limits>

// ONLY MAKE CHANGES WHERE THERE IS A TODO(student)

// These using declarations let us refer to things more simply
// e.g. instead of "std::cin" we can just write "cin"
using std::cin, std::cout, std::endl;
using std::string, std::getline;

// These methods are defined below the main function

// print instructions for inputting grades
void print_instructions();

// pretty print a summary of the grades
void print_results(double exam_average,
                   double hw_average,
                   double lw_average,
                   double reading,
                   double engagement,
                   double weighted_total,
                   char final_letter_grade);

// extract the category and score from the line
// and store the values in the provided variables
// if line := "exam 95", then category := "exam" and score := 95
// if the line is invalid, then category := "ignore"
void get_category_and_score(const string& line,
                            string* category,
                            double* score);

int main() {
    print_instructions();

    // ONLY MAKE CHANGES WHERE THERE IS A TODO(student)
    

    // TODO(student): declare and initialize variables
    double final_exam_score{};
    double exam1_score{};
    double exam2_score{};
    double exam3_score{};
    double hw_score{};
    
    int exam_count{0};
    int exams_needed{2};
    int hw_count{};
    int lw_completed{};
    int lw_count{};
    int reading_completed{};
    int reading_count{};
    int engagement_completed{};
    int engagement_count{};

    string line;
    // read one line from standard input (discards the ending newline character)
    getline(cin, line);
    // read lines until an empty line is read
    while (!line.empty()) {
        string category;
        double score;
        get_category_and_score(line, &category, &score);

        // process the grade entry
        if (category == "exam") {
            // TODO(student): process midterm exam score
            // If an exam score is missing, then assume the score is zero (0).
            // The average of your two higher midterm exam grades out of three (i.e. the average of your exam grades with the lowest midterm grade dropped)
            if (exam_count == 0) {
                exam1_score = score;
                
            } else if (exam_count == 1) {
                exam2_score = score;
            } else if (exam_count == 2) {
                exam3_score = score;
            }
            exam_count++;
        } else if (category == "final-exam") {
            // TODO(student): process final exam score
            final_exam_score += score;
        } else if (category == "hw") {
            // TODO(student): process hw score
            hw_score += score;
            hw_count++;
        } else if (category == "lw") {
            // TODO(student): process lw score
            // a lab score has to be alteasy 50 to be counted for credit
            // only add to the score if it is alteasy 50, but still add to the count
            // if its atleast 50, add to completed lw count, if not, just add to total lw count
            if (score >= 50) {
                lw_completed++;
            }
            lw_count++;

        } else if (category == "reading") {
            // TODO(student): process reading score
            // readings will be either 1 or 0, same as lw, add succesfful readings to a count, and keep a total count
            if (score == 1) {
                reading_completed++;
            }
            reading_count++;
        } else if (category == "engagement") {
            // TODO(student): process engagement score
            // engagetment based on number completed, greater than zero is considered completed
            if (score > 0) {
                engagement_completed++;
            }
            engagement_count++;
        } else {
            cout << "ignored invalid input" << endl;
        }

        // get the next line from standard input
        getline(cin, line);
    }

    double hw_average = 0;
    double lw_average = 0;
    double exam_average = 0;
    double reading = 15;
    double engagement = 15;
    // TODO(student): compute component averages and assign to the above variables
    // exam_average = ???; // etc.

    if (hw_count != 0) {
        hw_average = (hw_score / hw_count) ;
        if (hw_average > 100) {
            hw_average = 100;
        }
    }
    if (lw_count != 0) {
        lw_average = (static_cast<double>(lw_completed) / lw_count);
        lw_average *= 100;
    }
    if (exam_count > 0) {
        if (exam_count == 1) {
            exam_average = exam1_score / exams_needed;
        } else if (exam_count == 2) {
            exam_average = (exam1_score + exam2_score) / exams_needed;
        } else {
            // drop the lowest score
            if (exam1_score <= exam2_score && exam1_score <= exam3_score) {
                exam_average = (exam2_score + exam3_score) / exams_needed;
            } else if (exam2_score <= exam1_score && exam2_score <= exam3_score) {
                exam_average = (exam1_score + exam3_score) / exams_needed;
            } else {
                exam_average = (exam1_score + exam2_score) / exams_needed;
            }
        
        }
    }
    if (reading_count != 0) {
        reading = (static_cast<double>(reading_completed) / reading_count) * 100;
        // average is given average plus 15 points for a max of 100, so cap it at 100
        if (reading < 100) {
            reading += 15;
            if (reading > 100) {
                reading = 100;
            }
        } else {
            reading = 100;
        }
    } 

    if (engagement_count != 0) {
        
        engagement = (static_cast<double>(engagement_completed) / engagement_count) * 100;
        // average is given average plus 15 points for a max of 100, so cap it at 100
        if (engagement < 100) {
            engagement += 15;
            if (engagement > 100) {
                engagement = 100;
            }
        } else {
            engagement = 100;
        }
    }



    // TODO(student): compute weighted total of components
    double weighted_total = 0;
    // HW : 20%
    // LW : 5%
    // Midterm: 36%
    // Final: 24%
    // Readings: 5%
    // Engagement: 5%
    exam_average = (exam_average * 0.60) + (final_exam_score * 0.40);


    weighted_total = (exam_average * .60) + (hw_average * 0.20) + (lw_average * 0.10) + (reading * 0.05) + (engagement * 0.05);
    // TODO(student): compute final letter grade
    char final_letter_grade = 'X';
    // cast into int
    double added = weighted_total + .5;
    int casted_weighted_total = static_cast<int>(added);
    if (casted_weighted_total >= 90) {
        final_letter_grade = 'A';
    } else if (casted_weighted_total >= 80) {
        final_letter_grade = 'B';
    } else if (casted_weighted_total >= 70) {
        final_letter_grade = 'C';
    } else if (casted_weighted_total >= 60) {
        final_letter_grade = 'D';
    } else {
        final_letter_grade = 'F';
    }


    // Do not modify print_results since this will not help
    print_results(
        exam_average, hw_average, lw_average, reading, engagement,
        weighted_total, final_letter_grade);
}

// These methods are already implemented for you
// You should not need to modify them
// Even minor changes might cause you to fail test cases for the wrong reasons

void print_instructions() {
    cout << "enter grades as <category> <score>" << endl;
    cout << "  <category> := exam | final-exam | hw | lw | reading | engagement" << endl;
    cout << "     <score> := numeric value" << endl;
    cout << "enter an empty line to end input" << endl;
}

// YOU ARE NOT EXPECTED TO UNDERSTAND THIS ONE... YET
void get_category_and_score(
    const string& line,
    string* category,
    double* score) {
    // turn the string into an input stream
    std::istringstream sin(line);

    // read the category (as string) and score (as double) from the stream
    sin >> *category;
    sin >> *score;

    if (sin.fail()) {
        // the stream is in a fail state (something went wrong)
        // clear the flags
        sin.clear();
        // clear the stream buffer (throw away whatever garbage is in there)
        sin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        // signal that the line was invalid
        *category = "ignore";
    }
}

void print_results(
    double exam_average,
    double hw_average,
    double lw_average,
    double reading,
    double engagement,
    double weighted_total,
    char final_letter_grade) {
    cout << "summary:" << endl;
    cout << "        exam average: " << exam_average << endl;
    cout << "          hw average: " << hw_average << endl;
    cout << "          lw average: " << lw_average << endl;
    cout << "     reading average: " << reading << endl;
    cout << "  engagement average: " << engagement << endl;
    cout << "  -------------------" << endl;

    cout << "      weighted total: " << weighted_total << endl;

    cout << "  final letter grade: " << final_letter_grade << endl;
}
