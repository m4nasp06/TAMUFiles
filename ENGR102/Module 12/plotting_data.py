# By submitting this assignment, I agree to the following:
#   "Aggies do not lie, cheat, or steal, or tolerate those who do."
#   "I have not given or received any unauthorized aid on this assignment."
#
# Name:         Manas Paramathmuni
# Section:      522
# Assignment:   11.2 - Plotting Data
# Date:         15 November 2024
# C:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 12/WeatherDataCLL.csv


import matplotlib.pyplot as plt

with open('C:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 12/WeatherDataCLL.csv', 'r') as file:
    lines = file.readlines()[1:]

    month_dict = {'January': "01", 'February': "02", 'March': "03", 'April': "04", 'May': "05", 'June': "06", 
                  'July': "07", 'August': "08", 'September': "09", 'October': "10", 'November': "11", 'December': "12"}
    year_list = ["2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024"]
    month_list = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"]

    temp_values = []
    dew_points = []
    humidity = []
    wind_speed_values = []
    precipitation_days = 0
    total_days = 0

    daily_temps = []
    daily_wind_speeds = []

    for line in lines:
        columns = line.split(',')
        if any(line.startswith(year) for year in year_list):
            total_days += 1
            
            try:
                dew_point = float(columns[1]) if columns[1] else None 
                temp = float(columns[2]) if columns[2] else None
                rel_humidity = float(columns[3]) if columns[3] else None
                wind_speed = float(columns[4]) if columns[4] else None
                precipitation = float(columns[7]) if columns[7] else 0
            except ValueError:
                continue

            if dew_point is not None:
                dew_points.append(dew_point)
            if temp is not None:
                temp_values.append(temp)
                daily_temps.append(temp)
            if rel_humidity is not None:
                humidity.append(rel_humidity)
            if wind_speed is not None:
                wind_speed_values.append(wind_speed)
                daily_wind_speeds.append(wind_speed)
            
            if precipitation > 0:
                precipitation_days += 1
    
    mean_temp = sum(temp_values) / len(temp_values) if temp_values else 0
    mean_dew_point = sum(dew_points) / len(dew_points) if dew_points else 0
    mean_humidity = sum(humidity) / len(humidity) if humidity else 0
    mean_wind_speed = sum(wind_speed_values) / len(wind_speed_values) if wind_speed_values else 0
    precipitation_percentage = (precipitation_days / total_days) * 100 if total_days > 0 else 0

    min_length = min(len(daily_temps), len(daily_wind_speeds))
    daily_temps = daily_temps[:min_length]
    daily_wind_speeds = daily_wind_speeds[:min_length]

    days = list(range(1, min_length + 1))

    # fig, ax1 = plt.subplots()
    #
    # ax1.set_xlabel('Days')
    # ax1.set_ylabel('Average Temperature, F', color='tab:red')
    # ax1.plot(days, daily_temps, 'r', label='Temperature') #r is for red
    # ax1.tick_params(axis='y', labelcolor='tab:red')
    #
    # #second y-axis for wind speed
    # ax2 = ax1.twinx()
    # ax2.set_ylabel('Wind Speed, mph', color='tab:blue')
    # ax2.plot(days, daily_wind_speeds, 'b', label='Wind Speed')
    # ax2.tick_params(axis='y', labelcolor='tab:blue')
    #
    # ax1.legend(loc=2)
    # ax2.legend(loc=1)
    # plt.title('Average Temperature and Wind Speed Over Time')
    # plt.show()
    #
    #
    # plt.hist(daily_wind_speeds, bins=20, color='green')
    # plt.xlabel('Average Wind Speed, mph')
    # plt.ylabel('Number of Days')
    # plt.title('Histogram of Average Wind Speed')
    # plt.show()
    #
    # min_length_humidity_dew = min(len(humidity), len(dew_points))
    # humidity_points = humidity[:min_length_humidity_dew]
    # dew = dew_points[:min_length_humidity_dew]
    # plt.scatter(humidity_points, dew, color='black', marker='+')
    # plt.xlabel('Average Dew Point, F')
    # plt.ylabel('Average Relative Humidity, %')
    # plt.title('Average Relative Humidity vs Average Dew Point')
    # plt.show()

    # Create a bar chart, with one bar per calendar month (each month from all 10 years), showing
    # the mean average temperature for each month, along with lines indicating the highest high
    # temperature, the lowest low temperature, and the mean total precipitation from that month.
    # a. Note: You may want to create new lists of data, and you may also find it useful to use
    # the max/min/sum functions on lists.
    # b. This is a great problem to practice using dictionaries!
    months_avg_temp = {month: [] for month in month_list}

    for line in lines:
        columns = line.split(",")
        columns[0] = columns[0].split("-")
        month = columns[0][1]
        # once gotten month, the third item in the columns list is the average temp, so add that to the dict
        months_avg_temp[month].append(float(columns[2]))

    print(months_avg_temp)

