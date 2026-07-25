import math
import random


def generate_playlist(song_pool, target_duration):
    remaining = dict(song_pool)
    playlist = []
    total_time = 0

    while remaining:
        fitting = {
            title: duration
            for title, duration in remaining.items()
            if total_time + duration <= target_duration
        }
        if not fitting:
            break
        title = random.choice(list(fitting.keys()))
        playlist.append(title)
        total_time += remaining.pop(title)

    return playlist, total_time


def analyze_trajectories(v_initial):
    if v_initial <= 0:
        return {}

    g = 9.81
    result = {}
    for angle in range(15, 76, 5):
        theta = math.radians(angle)
        d = (v_initial**2 * math.sin(2 * theta)) / g
        h = (v_initial**2 * math.sin(theta) ** 2) / (2 * g)
        result[angle] = (round(d, 2), round(h, 2))

    return result


def monte_carlo_sphere_volume(radius, num_samples):
    if radius <= 0 or num_samples <= 0:
        return (0.0, 0.0)

    inside_count = 0
    for _ in range(num_samples):
        x = random.uniform(-radius, radius)
        y = random.uniform(-radius, radius)
        z = random.uniform(-radius, radius)
        if math.sqrt(x**2 + y**2 + z**2) <= radius:
            inside_count += 1

    cube_volume = (2 * radius) ** 3
    estimated_volume = (inside_count / num_samples) * cube_volume
    actual_volume = (4 / 3) * math.pi * radius**3

    return (round(estimated_volume, 4), round(actual_volume, 4))
