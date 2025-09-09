# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   Lab 13 Individual
# Date:         1 December 2024
import turtle as t
from math import gcd

def parta(turn_angle):
    """
    Draws a figure based on a single turn angle and determines
    the smallest number of iterations for the turtle to return
    to the starting point.

    Parameters:
    turn_angle (float): The turn angle in degrees.
    """
    def find_lcm(value1, value2):
        return abs(value1 * value2) // gcd(value1, value2)

    num_iterations = find_lcm(360, turn_angle) // turn_angle

    t.reset()
    t.speed(0)
    t.dot(10, "red")
    for _ in range(num_iterations):
        t.left(turn_angle)
        t.forward(100)


def partb(angle_sequence):
    """
    Draws a figure based on a sequence of 0s and 1s where:
    0 corresponds to a 30° turn, and 1 corresponds to -114° turn.

    Parameters:
    angle_sequence (str): A string of 0s and 1s.
    """
    turn_angle_zero = 30
    turn_angle_one = -114
    total_angle = sum(turn_angle_zero if char == '0' else turn_angle_one for char in angle_sequence)

    def find_lcm(value1, value2):
        return abs(value1 * value2) // gcd(value1, value2)

    num_iterations = find_lcm(360, abs(total_angle)) // abs(total_angle)

    t.reset()
    t.speed(0)
    t.dot(10, "red")
    for _ in range(num_iterations):
        for char in angle_sequence:
            if char == '0':
                t.left(turn_angle_zero)
            elif char == '1':
                t.left(turn_angle_one)
            t.forward(5)


def partc(num_turns_one, zero_turn_angle=90, one_turn_angle=0, step_size=5):
    """
    Draws a spiral figure based on a sequence of 1s followed by
    increasing numbers of 0s.

    Parameters:
    num_turns_one (int): Number of 1s in the sequence.
    zero_turn_angle (float): Turning angle for 0.
    one_turn_angle (float): Turning angle for 1.
    step_size (int): Forward step size.
    """
    spiral_sequence = []
    for i in range(num_turns_one):
        spiral_sequence.append('1')
        spiral_sequence.extend(['0'] * i)

    t.reset()
    t.speed(0)
    t.dot(10, "red")
    for char in spiral_sequence:
        if char == '1':
            t.left(one_turn_angle)
        elif char == '0':
            t.left(zero_turn_angle)
        t.forward(step_size)


# main code
parta(160)
input()  
t.reset()    
parta(141)
input()  
t.reset()    
partb("01001")
input()  
t.reset()    
partb("01001011")
input()  
t.reset()    
spiral_sequence1 = ""  # spiral with 20 ones
partc(spiral_sequence1, 0, 90)
input()  
t.reset()    
partc(spiral_sequence1, 0, 30)
input()  
t.reset()    
spiral_sequence2 = ""  # spiral with 50 ones
partc(spiral_sequence2, 0, 150)
input()  
t.reset()    
partc(spiral_sequence2, 5, 108)
input()  
t.reset()
