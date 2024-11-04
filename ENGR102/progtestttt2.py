import math
import itertools
# Using combinatorial backtracking to ensure 13 points for n = 7
def no_three_in_line_backtracking(n):
    def are_aligned(p1, p2, p3):
        x1, y1 = p1
        x2, y2 = p2
        x3, y3 = p3
        return (y2 - y1) * (x3 - x2) == (y3 - y2) * (x2 - x1)

    target_points = math.ceil(1.8 * n)
    points = [(i, j) for i in range(n) for j in range(n)]
    wanted_points = []

    def backtrack(index):
        # If we reach the target number of points, return True (solution found)
        if len(wanted_points) == target_points:
            return True

        # Iterate through the remaining points
        for i in range(index, len(points)):
            new_point = points[i]
            aligned = False

            # Check if adding this point causes any three points to align
            for p1, p2 in itertools.combinations(wanted_points, 2):
                if are_aligned(p1, p2, new_point):
                    aligned = True
                    break

            # If not aligned, add the point and continue
            if not aligned:
                wanted_points.append(new_point)
                if backtrack(i + 1):  # Recur to try the next placement
                    return True
                wanted_points.pop()  # Backtrack if no solution was found

        return False

    # Start the backtracking search
    backtrack(0)
    return wanted_points

# Running the backtracking function with n = 7
n = 4
points_combination_n7_backtracking = no_three_in_line_backtracking(n)
print(len(points_combination_n7_backtracking), points_combination_n7_backtracking)
