from Lab22_provided import RentalVehicle


class ElectricVehicle(RentalVehicle):
    def __init__(self, make, model, daily_rate, battery_kwh):
        super().__init__(make, model, daily_rate)
        self.fuel_type = "Electric"
        self.min_rental_age = 25
        self.battery_kwh = battery_kwh
        self.charge_cycles = 0

    def refuel(self, amount):
        self.charge_cycles += 1
        return f"Charging battery with {amount} kWh."

    def __str__(self):
        return f"{super().__str__()} [Battery: {self.battery_kwh} kWh]"

    def apply_mileage_charge(self, miles):
        if miles <= 100:
            return 0.0
        return (miles - 100) * 0.15


class Truck(RentalVehicle):
    def __init__(self, make, model, daily_rate, cargo_tons):
        super().__init__(make, model, daily_rate)
        self.cargo_tons = cargo_tons

    def calculate_rental_cost(self, days):
        total = super().calculate_rental_cost(days)
        if days <= 7:
            total += 50
        else:
            print("Surcharge Notice: Long-term discount applied to heavy-duty fee.")
            total += 20
        return total

    def can_rent(self, driver_age):
        if not super().can_rent(driver_age):
            return False
        if self.cargo_tons > 5.0:
            return driver_age >= 25
        return True

    def __str__(self):
        return f"{super().__str__()} [Cargo: {self.cargo_tons} tons]"

    def perform_inspection(self):
        if self.cargo_tons > 7.0:
            self.maintenance_status = "Requires Certified Weight Inspection"
            return "Requires Certified Weight Inspection"
        return super().perform_inspection()
