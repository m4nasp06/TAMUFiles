class ElectronicDevice:
    def __init__(self, brand, model, price):
        self.brand = brand
        self.model = model
        self.price = price

    def apply_discount(self, percentage):
        self.price -= self.price * (percentage / 100)

    def __str__(self):
        return f"{self.brand} {self.model} - ${self.price:.2f}"

    def __lt__(self, other):
        return self.price < other.price


class SmartPhone(ElectronicDevice):
    def __init__(self, brand, model, price, storage_gb):
        super().__init__(brand, model, price)
        self.storage_gb = storage_gb

    def install_app(self, app_name):
        return f"Installing {app_name} on {self.brand} {self.model}..."


class Laptop(ElectronicDevice):
    def __init__(self, brand, model, price, ram_gb):
        super().__init__(brand, model, price)
        self.ram_gb = ram_gb

    def upgrade_ram(self, additional_ram):
        self.ram_gb += additional_ram
        return f"{self.brand} {self.model} upgraded to {self.ram_gb}GB RAM."


if __name__ == "__main__":
    device = ElectronicDevice("Generic", "Gadget", 100.00)
    device.apply_discount(15)
    print(device)

    phone = SmartPhone("Google", "Pixel 8", 699.99, 128)
    print(phone.install_app("Mobile Python IDE"))
    phone.apply_discount(10)
    print(phone)

    laptop = Laptop("Dell", "XPS 13", 1199.99, 16)
    print(laptop.upgrade_ram(16))

    phone2 = SmartPhone("Google", "Pixel 8", 629.99, 128)
    print(phone2 < laptop)
