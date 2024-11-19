# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   11.7 Weather inp_f
# Date:         8 November 2019
#
import csv

# Download the file and write a program named weather_inp_f.py that does the following:
# 1. Open the CSV file for reading
#     2. Read the CSV file and compute
# a. the maximum temperature seen over the 10-year period
# b. the minimum temperature seen over the 10-year period
# 3. Output the results to the console using the format below
# 4. Perform the following five inp_f analysis exercises and output the results to the console. Take as input
# from the user a month and year, then for that month,
#     a. Calculate the mean of the average temperatures (use 1 decimal place)
# b. Calculate the mean of the average dew point (use 1 decimal place)
# c. Calculate the mean relative humidity (use 1 decimal place)
# d. Calculate the mean daily wind speed (use 2 decimal places)
# e. Calculate the percentage of days with non-zero precipitation (use 1 decimal place)
# Example Output (using inputs July, 2023, but with made-up numbers):
#     10-year maximum temperature: 102 F
# 10-year minimum temperature: 7 F
# Please enter a month: July
# Please enter a year: 2023
# For July 2023:
# Mean average daily temperature: 92.5 F
# Mean average daily dew point: 78.1 F
# Mean relative humidity: 58.6%
# Mean daily wind speed: 7.34 mph
# Percentage of days with precipitation: 2.1%

# need max temp
# need min temp


with open("WeatherDataCLL.csv", 'r') as inp_f:
    inp_f_points = inp_f.readlines()

max_temps, min_temps = [], []

for dp in inp_f_points:
    dp_split = dp.split(',')
    if dp_split[0] == 'Date' or dp_split[5] == '' or dp_split[6] == '':
        continue
    max_temps.append(int(dp_split[5]))
    min_temps.append(int(dp_split[6]))

max_temp = max(max_temps)
min_temp = min(min_temps)
print("10-year maximum temperature: ", max_temp, "F")
print("10-year minimum temperature: ", min_temp, "F")




with open('WeatherDataCLL.csv') as inp_f:
    weather_data = csv.reader(inp_f)
    month = input('Please enter a month: ')
    year = int(input('Please enter a year: '))
    months = {'January': 1, 'February': 2, 'March': 3, 'April': 4,
                  'May': 5, 'June': 6, 'July': 7, 'August': 8, 'September': 9,
                  'October': 10, 'November': 11, 'December': 12}
    month_num = months[month]

    avgTempSum, avgDewSum, avgRelHumSum, DailyWindSum, numPrecip, totalDays,firstRow = 0, 0, 0, 0.0, 0, 0, True

    for row in weather_data:

        if firstRow:
            firstRow = False
            continue
        date = row[0].split('-')
        if month_num == int(date[1]) and year == int(date[0]):
            avgTempSum += int(row[2])
            avgDewSum += int(row[1])
            avgRelHumSum += int(row[3])
            DailyWindSum += float(row[4])
            if float(row[7]) > 0:
                numPrecip += 1
            totalDays += 1

    percentPrecip = numPrecip / totalDays
    percentPrecip *= 100
    print(f'For {month} {year}:')
    print(f'Mean average daily temperature: {(avgTempSum / totalDays):.1f} F')
    print(f'Mean average daily dew point: {(avgDewSum / totalDays):.1f} F')
    print(f'Mean relative humidity: {(avgRelHumSum / totalDays):.1f}%')
    print(f'Mean daily wind speed: {(DailyWindSum / totalDays):.2f} mph')
    print(f'Percentage of days with precipitation: {percentPrecip:.1f}%')


