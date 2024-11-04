import itertools
import math

def no_three_in_line(n):
    def are_aligned(p1, p2, p3):
        x1, y1 = p1
        x2, y2 = p2
        x3, y3 = p3
        return (y2 - y1) * (x3 - x2) == (y3 - y2) * (x2 - x1)

    target_points = math.ceil(1.8 * n)
    points = [(i, j) for i in range(n) for j in range(n)]
    wanted_points = []

    # Alternating placement with additional checks to avoid alignment issues
    for x in range(n):
        for y in range(n):
            current_point = (x, y)
            three_in_line = False

            # Check for alignment with existing points
            for p1, p2 in itertools.combinations(wanted_points, 2):
                if are_aligned(p1, p2, current_point):
                    three_in_line = True
                    break

            # Add the point if no alignment issues are found
            if not three_in_line:
                wanted_points.append(current_point)

            # Stop if we reach the target number of points
            if len(wanted_points) >= target_points:
                return wanted_points

    return wanted_points

# Example usage for testing:
if __name__ == "__main__":
    n = 7
    points_combination = no_three_in_line(n)
    print(points_combination)
