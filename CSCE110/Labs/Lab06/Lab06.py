total_points = 0


def add_points(student_points, min_points=0, max_points=100):
    """Adds student_points to the global total_points, clamped between min_points and max_points. Does not return anything."""
    global total_points
    total_points += max(min_points, min(student_points, max_points))


def grade_calc():
    """Prompts for class activities, reading, and homework scores, adds each to total_points via add_points with its own min/max limits, then prints the running total to two decimal places."""
    activities = float(input("Enter class activities score: "))
    reading = float(input("Enter reading score: "))
    homework = float(input("Enter homework score: "))
    add_points(activities, min_points=0.75, max_points=5)
    add_points(reading, min_points=0.75, max_points=5)
    add_points(homework, max_points=20)
    print(f"Total Score is: {total_points:.2f}")


def addition(x, y):
    return str(int(x) + int(y))


def round(number, places=2):
    factor = 10 ** places
    return int(number * factor + 0.5) / factor
