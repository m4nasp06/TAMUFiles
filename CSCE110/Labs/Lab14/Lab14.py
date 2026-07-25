def isSorted(lst):
    if len(lst) <= 1:
        return True

    for i in range(len(lst) - 1):
        if lst[i] > lst[i + 1]:
            return False
    return True


def transform_and_filter():
    nums = list(int(x) for x in input("Enter a list of numbers : ").split())
    multiples_of_3 = [num for num in nums if num % 3 == 0]
    print(f"The list is : {nums}")
    if len(multiples_of_3) == 0:
        print("There are no multiples of 3 in the list.")
    elif len(multiples_of_3) == 1:
        print("There is 1 multiple of 3 in the list.")
    else:
        print(f"There are {len(multiples_of_3)} multiples of 3 in the list.")


def consolidate_inventory(warehouses):
    warehouse_dict = {}

    for name, inv in warehouses.items():
        for product, quantity in inv.items():
            if product in warehouse_dict:
                warehouse_dict[product] += quantity
            else:
                warehouse_dict[product] = quantity

    return warehouse_dict
