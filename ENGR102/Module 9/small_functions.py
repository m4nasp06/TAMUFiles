# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   9.20 Small Functions
# Date:         22 October 2024
#



from math import *

# a) Volume of a sphere with a hole
def parta(rSphere, rHole):
    volume = (4/3*pi) * (rSphere**2 - rHole**2)**( 3/2 ) 
    return volume


# part b gives the even numbers that add up to n
def partb(n):
    for initial in range(2, n, 2):
        sum = 0
        even_nums = []

        for num in range(initial, n, 2):
            sum += num
            even_nums.append(num)
            if sum == n:
                return even_nums
            elif sum > n:
                break
    return False


def partc(border_char, name, company, email):
    
    inputs = [ name, company, email ]
    max_length = max(len(inp) for inp in inputs)
    total_length = max_length + 4  # 2 spaces on each side of the longest entry
    
    card = border_char * (total_length + 2) + "\n"
    for inp in inputs:
        card += f"{border_char}  {inp.center(max_length)}  {border_char}\n"
    card += border_char * (total_length + 2)
    
    return card


def partd(list_of_nums):

    sorted_list = sorted(list_of_nums)
    min_value = sorted_list[0] # first element
    max_value = sorted_list[-1] # last element
    median_value = sorted_list[len(sorted_list) // 2] if len(sorted_list) % 2 != 0 else (
        sorted_list[len(sorted_list) // 2 - 1] + sorted_list[len(sorted_list) // 2]) / 2
    return min_value, median_value, max_value


# e) Velocity calculation
def parte(list_of_times, list_of_distances):
    velocity_list = []
    for i in range(1, len(list_of_times)):
        change_distance = list_of_distances[i] - list_of_distances[i - 1]
        change_time = list_of_times[i] - list_of_times[i - 1]
        velocity = change_distance / change_time
        velocity_list.append(velocity)

    return velocity_list


# f) Sum equals 2028
def partf(list_of_numbers):

    num_set = set()
    for num in list_of_numbers:
        comp = 2028 - num
        if comp in num_set:
            return num * comp
        num_set.add(num)
    return False


# g) Series expansion (bonus)
def partg(x, tol):

    if not (-1 < x < 1):
        raise ValueError("x must be between -1 and 1 (exclusive)")
    
    n = 1
    sum = 0
    while True:
        term = (2 / (2 * n - 1)) * (x ** (2 * n - 1))
        if abs(term) < tol:
            break
        sum += term
        n += 1
    return sum