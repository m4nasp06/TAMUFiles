class Money:
    # rate = value of 1 USD in that currency
    exchange_rates = {'USD': 1.0, 'EUR': 0.9, 'GBP': 0.8}

    def __init__(self, amount, currency):
        if amount < 0:
            raise ValueError("Amount cannot be negative")
        if currency not in Money.exchange_rates:
            raise ValueError(f"Currency {currency} is not supported")
        self.amount = float(amount)
        self.currency = currency

    def convert_to(self, target_currency):
        if target_currency not in Money.exchange_rates:
            raise ValueError(f"Currency {target_currency} is not supported")
        usd_amount = self.amount / Money.exchange_rates[self.currency]
        new_amount = usd_amount * Money.exchange_rates[target_currency]
        return Money(new_amount, target_currency)

    def __add__(self, other):
        other = other.convert_to(self.currency)
        return Money(self.amount + other.amount, self.currency)

    def __sub__(self, other):
        other = other.convert_to(self.currency)
        result = self.amount - other.amount
        if result < 0:
            raise ValueError("Resulting amount cannot be negative")
        return Money(result, self.currency)

    def __lt__(self, other):
        other = other.convert_to(self.currency)
        return self.amount < other.amount

    def __eq__(self, other):
        other = other.convert_to(self.currency)
        return self.amount == other.amount

    def __str__(self):
        return f"{self.amount:.2f} {self.currency}"


class Product:
    def __init__(self, name, price, stock):
        self.name = name
        self.price = price
        self.stock = stock

    def restock(self, amount):
        if amount < 0:
            raise ValueError("Amount cannot be negative")
        self.stock += amount

    def reduce_stock(self, amount):
        if amount > self.stock:
            raise ValueError("Not enough stock available")
        self.stock -= amount


class Store:
    sales_tax_rate = 0.05

    def __init__(self, name):
        self.name = name
        self.inventory = []
        self.revenue = Money(0.0, "USD")

    def add_product(self, product):
        if self.find_product(product.name) is not None:
            raise ValueError("Product already exists")
        self.inventory.append(product)

    def find_product(self, product_name):
        for product in self.inventory:
            if product.name == product_name:
                return product
        return None

    def process_order(self, product_name, quantity, payment):
        product = self.find_product(product_name)
        if product is None:
            raise ValueError(f"Product {product_name} not found")

        # subtotal via loop, per spec (no Money * int overload)
        subtotal = Money(0.0, product.price.currency)
        for _ in range(quantity):
            subtotal = subtotal + product.price

        grand_total = Money(subtotal.amount * (1 + Store.sales_tax_rate), subtotal.currency)

        if payment < grand_total:
            raise ValueError("Payment is less than grand total")

        product.reduce_stock(quantity)
        self.revenue = self.revenue + grand_total
        return payment - grand_total


if __name__ == "__main__":
    store_name = input("Enter store name: ")
    store = Store(store_name)

    while True:
        print(f"\n--- {store.name} ---")
        print("1. Add Product")
        print("2. Restock Product")
        print("3. Process Sale")
        print("4. Exit")
        choice = input("Choice: ")

        if choice == "1":
            try:
                name = input("Enter product name: ")
                amount = float(input("Enter price amount: "))
                currency = input("Enter price currency: ")
                stock = int(input("Enter initial stock: "))
                price = Money(amount, currency)
                store.add_product(Product(name, price, stock))
                print("Product added successfully!")
            except ValueError as e:
                print(f"Error: {e}")

        elif choice == "2":
            try:
                name = input("Enter product name: ")
                amount = int(input("Enter restock amount: "))
                product = store.find_product(name)
                if product is None:
                    print(f"Error: Product {name} not found")
                else:
                    product.restock(amount)
                    print("Product restocked successfully!")
            except ValueError as e:
                print(f"Error: {e}")

        elif choice == "3":
            try:
                name = input("Enter product name: ")
                quantity = int(input("Enter quantity: "))
                payment_amount = float(input("Enter payment amount: "))
                payment_currency = input("Enter payment currency: ")
                payment = Money(payment_amount, payment_currency)
                change = store.process_order(name, quantity, payment)
                print(f"Success! Your change is {change}.")
            except ValueError as e:
                print(f"Error: {e}")

        elif choice == "4":
            print(f"Exiting program. Final Store Revenue: {store.revenue}.")
            break
