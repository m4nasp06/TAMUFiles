# three global vars, inventory, products, storeName

# inventory is blank dict
# inventory key is item name, val is quantity in stock
inventory = {}
# prodcuts key is item name, val is list of supplier and price
products = {}
storeName = ""


# Section 1
def changeStoreName(name):
    global storeName
    storeName = name


def resetInventory():
    inventory.clear()
    products.clear()


# Section 2
def addProduct(item, supplier="", price=9999.00):
    products[item] = [supplier, price]


def addInventoryItem(item, quantity=0):
    if item in inventory:
        inventory[item] += quantity
    else:
        inventory[item] = quantity


# Section 3
def getPrice(item, quantity=1):
    if item in products:
        return products[item][1] * quantity


def checkStock(item):
    if item in inventory:
        return inventory[item]
    else:
        return 0


# Section 4
def restockItem(item, quantity=0):
    inventory[item] += quantity


def renameProduct(old_name, new_name):  # using .pop, rename for products and inventory
    products[new_name] = products.pop(old_name)
    inventory[new_name] = inventory.pop(old_name)


def updatePrice(item, new_price):
    products[item][1] = new_price


def adjustPrice(item, price_change):
    products[item][1] += price_change


def applyDiscount(item, discount_percent):
    products[item][1] *= 1 - discount_percent


def applyMarkup(item, markup_percent):
    products[item][1] *= 1 + markup_percent


# Section 5
def sellOneItem(item, quantity=1):
    if item in inventory:
        inventory[item] -= quantity
        # print formatted string, item name left alligned field size 20, quantity left alligned field 5, cost right alligned size 10, to 2 decimals
        print(f"{item:<20} x{quantity:<4} : ${getPrice(item, quantity):>10.2f}")
        return getPrice(item, quantity)


def sellTwoItem(
    items,
):  # list containing 2 tuples, each tuple has item name and quantity
    # call sellOneItem twice
    total_cost = 0
    for item, quantity in items:
        total_cost += sellOneItem(item, quantity) or 0
    return total_cost


# Section 6
def removeProduct(item):
    if item in products:
        del products[item]
    if item in inventory:
        del inventory[item]


# Section 7
def printInventory():
    print("_____________________________________________")
    print(inventory)
    print("_____________________________________________")


def getUniqueProductCount():
    return len(products)


def getTotalStockCount():
    return sum(inventory.values())


def printStoreReport():
    print(f"\n=== {storeName} OVERVIEW ===")
    print(f"Unique Products Carried: {getUniqueProductCount()}")
    print(f"Total Physical Items in Stock: {getTotalStockCount()}")
    print("============================\n")


# Q2
def createTree():
    node2 = [2, None, None]
    node1 = [1, None, node2]
    node4 = [4, None, None]
    node3 = [3, node1, node4]
    node6 = [6, None, None]
    node7 = [7, node6, None]
    node9 = [9, None, None]
    node8 = [8, node7, node9]
    return [5, node3, node8]
