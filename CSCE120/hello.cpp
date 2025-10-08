
#include <iostream>
#include <string>

class BankAccount {
    private:
        unsigned long long accountNumber;
        std::string ownerName;
        double currentBalance;

    public:
        // Constructor
        BankAccount();
        BankAccount(int accNum, std::string owner, double initialBalance) :
         accountNumber(accNum), ownerName(owner), currentBalance(initialBalance) {
            if (accNum < 0) {
                throw std::invalid_argument("Account number must be >= 0");
            }
            
            
        }
        // ~BankAccount();

        double getBalance() const {
            return currentBalance;
        }
        bool deposit(double amt) {
            if (amt < 0) {
                return false; // can't deposit a negative amount
            }
            currentBalance += amt;
            return true;
        }
        bool withdraw(double amt) {
            if (amt < 0 || amt > currentBalance) {
                return false; // can't withdraw a negative amount or more than the current balance
            }
            currentBalance -= amt;
            return true;
        }
        
};

