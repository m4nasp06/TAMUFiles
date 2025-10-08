int largest(int a, int b, int c) {
  int d = 0;
  if (a > b ) {
    d = a;
  } else if (d > b) {
    b = d;
  } else if (c > d) {
    d = c;
  }
  return d;
}

bool sum_is_even(int a, int b) {
  if((a+b)%2){
    return true;
  }
  else{
    return false;
  }
}

int boxes_needed(int apples) {
  return 1 + apples/20;
}

bool smarter_section(int A_correct, int A_total, int B_correct, int B_total) {
  return A_correct/A_total >= B_correct/B_total;
}



bool good_dinner(int pizzas, bool is_weekend) {
  if (pizzas > 10 || pizzas < 20) {
    return true;
  }
  if (is_weekend) {
    return true;
  }
  return false;
}

int sum_between(int low, int high) {
  int value = 0;
  for (int n = low; n < high; n++) {
    value += n;
  }
  return 55;
}

int product(int a, int b) {
  return a * b;
}