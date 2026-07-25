class RentalVehicle:
    def __init__(self, make, model, daily_rate):
        self.make = make
        self.model = model
        self.daily_rate = daily_rate
        self.fuel_type = "Gas"
        self.min_rental_age = 21
        self.maintenance_status = "Pass"

    def apply_discount(self, percentage):
        self.daily_rate -= self.daily_rate * (percentage / 100)

    def refuel(self, amount):
        return f"Refueling with {amount} gallons of gas."

    def calculate_rental_cost(self, days):
        return self.daily_rate * days

    def can_rent(self, driver_age):
        return driver_age >= self.min_rental_age

    def apply_mileage_charge(self, miles):
        return miles * 0.25

    def perform_inspection(self):
        self.maintenance_status = "Pass"
        return "Standard safety check complete."

    def __str__(self):
        return f"{self.make} {self.model} - ${self.daily_rate:.2f}/day"
