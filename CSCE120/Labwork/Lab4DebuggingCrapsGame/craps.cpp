// Plays the game of craps.
#include <iostream>
#include <cstdlib>
#include <ctime>
using std::cin, std::cout, std::endl;


// function prototype / declarations
int roll1d6();
int roll2d6();		
int doGame(int, int);

int main()
{
  /*
    Normally, we would seed with time(NULL), however autograding
    is hard to do with random results. So, we are going to 
    enter a seed value to allow for autograding.
  */
  int seed;
  cin >> seed;
  srand(seed); // Seed the random number generator
  int wallet = 1000;
  bool gameOver = false;

  while (!gameOver)
  {
	cout << "Enter amount to bet. You have $" << wallet << endl;
	int bet;
	cin >> bet;
	if (bet <= 0)
		cout << "Bet must be positive." << endl;
	else if (bet > wallet)
		cout << "You don't have that much money." << endl;
	else
        wallet = doGame(bet,wallet);
	if (wallet == 0)
	{
		cout << "You are penniless. Thanks for playing." << endl;
		gameOver = true;
	}
	else {
		cout << "Play again? (y/n)" << endl;
		char c;
		cin >> c;
		if (c=='n') {
			gameOver = true;
      }
    }
  }
  cout << "You ended the game with $" << wallet << "." << endl;
  return 0;
}     

// roll 1 6-sided die and return the result
int roll1d6() {
  // rand() % 6 will give a value of 0 to 5
  // adding 1 will give a value of 1 to 6
  return rand() % 6 + 1;
}

// Simulate rolling two dice.
int roll2d6() { return roll1d6() + roll1d6(); }

int doGame(int bet, int wallet) {
    // Make initial roll
    int roll = roll2d6();
    cout << "You rolled " << roll << endl;
    // Check for a win or loss or the point
    switch (roll)
    {
        case 7:
        case 11:
            cout << "You win!" << endl;
            wallet += bet;
            break;
        case 2:
        case 3:
        case 12:
            cout << "You lose." << endl;
            wallet -= bet;
            break;
        default:
            // Continue rolling until we roll the point
            // or 7
            int point = roll;
            cout << "The point is " << point << endl;
            do
            {
                roll = roll2d6();
                cout << "You rolled " << roll << endl;
                if (roll == 7)
                {
                  cout << "You lose." << endl;
                  wallet -= bet;
                }
                else if (roll == point)
                {
                  cout << "That matches the point!" <<
                    " You win!" << endl;
                  wallet += bet;
                }
            } while ((roll != 7) && (roll != point));
    }
    return wallet;
}
   