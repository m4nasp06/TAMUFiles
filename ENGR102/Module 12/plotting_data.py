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


def line_graph(lines):
    total_days = 0
    all_wind_speeds = []
    all_average_temperatures = []


    for line in lines:
        columns = line.strip().split(",")
        total_days += 1
        if columns[4]:
            all_wind_speeds.append(float(columns[4]))
        if columns[2]:
            all_average_temperatures.append(float(columns[2]))
        else:
            continue

    fig, ax = plt.subplots()
    ax.set_xlabel('date')
    ax.set_ylabel('Average Temperature, F')
    ax.plot(all_average_temperatures, color='red', label='Avg Temp')


    ax1 = ax.twinx()
    ax1.set_ylabel('Average Wind Speed,mph')
    ax1.plot(all_wind_speeds, color='blue', label='Avg Wind Speed')
    plt.title('Average Temperature Over Time')
    lines, labels = ax.get_legend_handles_labels()
    lines2, labels2 = ax1.get_legend_handles_labels()
    ax.legend(lines + lines2, labels + labels2, loc=1)
    plt.tight_layout()
    plt.show()
def histogram(lines):
# histogram for average wind speed
    wind_speeds = []
    for line in lines:
        columns = line.strip().split(",")
        if columns[4]:
            wind_speeds.append(float(columns[4]))
        else:
            continue
    fig, ax = plt.subplots()
    ax.hist(wind_speeds, bins=35, color='green', edgecolor='black')
    # need to show 0.0 on x-axis but need whitespace on left side and right side
    ax.set_xlabel('Average Wind Speed, mph')
    ax.set_ylabel('Number of days')
    plt.title('Histogram of Average Wind Speed')
    plt.show()

def scatter_plot(lines):
#     Create a scatterplot indicating the relationship (or lack thereof) between average relative
# humidity and average dew point (one on each axis)
    humidity, dew_points = [], []
    for line in lines:
        columns = line.strip().split(",")
        if columns[3] and columns[1]:
            humidity.append(float(columns[3]))
            dew_points.append(float(columns[1]))
        else:
            continue
    fig, ax = plt.subplots()
    ax.scatter(dew_points, humidity, color='black', marker='4')
    ax.set_xlabel('Average Dew Point, F')
    ax.set_ylabel('Average Relative Humidity, %')
    plt.title('Average Relative Humidity vs Average Dew Point')
    plt.show()

def bar_chart(lines):
    months_avg_temp,months_high_temps, months_low_temps,months_avg_precip = {}, {}, {}, {}
    for month in month_list:
        months_avg_temp[month] = []
        months_high_temps[month] = []
        months_low_temps[month] = []
        months_avg_precip[month] = []

    for line in lines:
        columns = line.strip().split(",")
        columns[0] = columns[0].split("-")
        month = columns[0][1]
        # check to see if value even exists
        if columns[2]:
            months_avg_temp[month].append(float(columns[2]))
        if columns[5]:
            months_high_temps[month].append(float(columns[5]))
        if columns[6]:
            months_low_temps[month].append(float(columns[6]))
        if columns[7]:
            months_avg_precip[month].append(float(columns[7]))
        else:
            continue


    for month in months_avg_temp:
        months_avg_temp[month] = sum(months_avg_temp[month]) / len(months_avg_temp[month])
        months_high_temps[month] = max(months_high_temps[month])
        months_low_temps[month] = min(months_low_temps[month])
        months_avg_precip[month] = sum(months_avg_precip[month]) / len(months_avg_precip[month])

    fig, ax = plt.subplots()
    ax.bar(month_dict.values(), months_avg_temp.values(), color='green')
    ax.plot(month_dict.values(), months_high_temps.values(), color='red', label='High Temp')
    ax.plot(month_dict.values(), months_low_temps.values(), color='blue', label='Low Temp')
    ax2 = ax.twinx()
    ax2.plot(month_dict.values(), months_avg_precip.values(), color='cyan', label='Avg Precip')
    ax2.set_ylim(0, 5)
    # hide y-axis for ax2
    ax2.yaxis.set_visible(False)
    ax.set_xlabel('Month')
    ax.set_ylabel('Average Temperature, F\nMonthly Precipitation, in')
    ax.legend()
    plt.title('Temperature and Precipitation by Month')
    plt.show()

# with open('C:/Users/manas/Desktop/TAMUFiles/ENGR102/Module 12/WeatherDataCLL.csv', 'r') as file:
with open('C:/Users/sripa/Desktop/TAMUFiles/ENGR102/Module 12/WeatherDataCLL.csv', 'r') as file:

    data_lines = file.readlines()[1:]
    month_dict = {'January': "01", 'February': "02", 'March': "03", 'April': "04", 'May': "05", 'June': "06",
                  'July': "07", 'August': "08", 'September': "09", 'October': "10", 'November': "11", 'December': "12"}
    month_list = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"]
    year_list = ["2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024"]
    line_graph(data_lines)
    histogram(data_lines)
    scatter_plot(data_lines)
    bar_chart(data_lines)







