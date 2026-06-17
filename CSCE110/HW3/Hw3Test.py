# ==============================================================================
# COMPREHENSIVE REGRESSION & DEFAULT-VALUE TEST SUITE
# ==============================================================================
from Hw03 import *

print("--- STEP 1: TESTING CREATION & DEFAULT PARAMETERS ---")
# Testing addProduct() defaults: supplier should become "", price should become 9999.00
addProduct("Mystery Toy")

# Testing addInventoryItem() default: quantity should become 0
addInventoryItem("Mystery Toy")

# Standard explicit insertion
addProduct("Blocks", "ToyCo", 15.00)
addInventoryItem("Blocks", 10)

print(
    f"Verified Default Supplier (Should be empty string): '{products['Mystery Toy'][0]}'"
)
print(f"Verified Default Price (Should be 9999.00): ${products['Mystery Toy'][1]:.2f}")
printInventory()


print("--- STEP 2: TESTING GETTERS & RESTOCK DEFAULTS ---")
# Testing getPrice() default parameter logic
print(f"Blocks Unit Price: ${getPrice('Blocks'):.2f}")
print(f"Current Blocks Stock Level: {checkStock('Blocks')}")

# Testing restockItem() default: quantity should default to 0 (stock remains 10)
restockItem("Blocks")
print(f"Stock after default restock (should still be 10): {checkStock('Blocks')}")

# Explicit restock (+5, total should become 15)
restockItem("Blocks", 5)
print(f"Stock after explicit restock (should be 15): {checkStock('Blocks')}")


print("\n--- STEP 3: TESTING SALES & DEFAULT QUANTITIES ---")
print("RECEIPT LINE ITEMS:")
# Testing sellOneItem() default: quantity should default to 1 (Stock: 15 -> 14)
cost_default = sellOneItem("Blocks")
print(f"Returned cost for default sale: ${cost_default:.2f}")

# Explicit sale of multiple items (Stock: 14 -> 10)
cost_explicit = sellOneItem("Blocks", 4)
print(f"Returned cost for explicit sale: ${cost_explicit:.2f}")
printInventory()


print("--- STEP 4: TESTING MULTI-ITEM TRANSACTIONS ---")
# Set up a shopping cart list of tuples to test compound sales
shopping_cart = [("Blocks", 2), ("Mystery Toy", 1)]
print("RECEIPT LINE ITEMS:")
grand_total = sellTwoItem(shopping_cart)
print(f"Grand Total Combined Sale: ${grand_total:.2f}")
printInventory()  # Blocks should be 8; Mystery Toy will drop to -1


print("--- STEP 5: TESTING PRICE AND PRODUCT MAINTENANCE ---")
# Test overwrite
updatePrice("Blocks", 20.00)
print(f"After updatePrice (Should be 20.00): ${products['Blocks'][1]:.2f}")

# Test relative adjustment
adjustPrice("Blocks", -5.00)
print(f"After adjustPrice -5.00 (Should be 15.00): ${products['Blocks'][1]:.2f}")

# Test percentage mutations
applyDiscount("Blocks", 0.20)  # 20% off of 15.00 = 12.00
print(f"After 20% applyDiscount (Should be 12.00): ${products['Blocks'][1]:.2f}")

applyMarkup("Blocks", 0.10)  # 10% markup on 12.00 = 13.20
print(f"After 10% applyMarkup (Should be 13.20): ${products['Blocks'][1]:.2f}")

# Test rebranding
renameProduct("Mystery Toy", "Puzzle")
print(f"Keys after renameProduct: {list(inventory.keys())}")


print("\n--- STEP 6: TESTING REMOVAL & GLOBAL METRICS ---")
# Drop the puzzle product entirely
removeProduct("Puzzle")

# Change store name context
changeStoreName("Target")

# Print summary metrics to terminal
printStoreReport()


print("--- STEP 7: TESTING TOTAL INVENTORY RESET ---")
resetInventory()
print("Inventory dictionary after reset (Should be empty):", inventory)
print("Products dictionary after reset (Should be empty):", products)


### Expected output shown in multiline comment below  ###
"""
--- STEP 1: TESTING CREATION & DEFAULT PARAMETERS ---
Verified Default Supplier (Should be empty string): ''
Verified Default Price (Should be 9999.00): $9999.00
_____________________________________________
{'Mystery Toy': 0, 'Blocks': 10}
_____________________________________________
--- STEP 2: TESTING GETTERS & RESTOCK DEFAULTS ---
Blocks Unit Price: $15.00
Current Blocks Stock Level: 10
Stock after default restock (should still be 10): 10
Stock after explicit restock (should be 15): 15

--- STEP 3: TESTING SALES & DEFAULT QUANTITIES ---
RECEIPT LINE ITEMS:
Blocks               x1    : $     15.00
Returned cost for default sale: $15.00
Blocks               x4    : $     60.00
Returned cost for explicit sale: $60.00
_____________________________________________
{'Mystery Toy': 0, 'Blocks': 10}
_____________________________________________
--- STEP 4: TESTING MULTI-ITEM TRANSACTIONS ---
RECEIPT LINE ITEMS:
Blocks               x2    : $     30.00
Mystery Toy          x1    : $   9999.00
Grand Total Combined Sale: $10029.00
_____________________________________________
{'Mystery Toy': -1, 'Blocks': 8}
_____________________________________________
--- STEP 5: TESTING PRICE AND PRODUCT MAINTENANCE ---
After updatePrice (Should be 20.00): $20.00
After adjustPrice -5.00 (Should be 15.00): $15.00
After 20% applyDiscount (Should be 12.00): $12.00
After 10% applyMarkup (Should be 13.20): $13.20
Keys after renameProduct: ['Blocks', 'Puzzle']

--- STEP 6: TESTING REMOVAL & GLOBAL METRICS ---

=== Target OVERVIEW ===
Unique Products Carried: 1
Total Physical Items in Stock: 8
============================

--- STEP 7: TESTING TOTAL INVENTORY RESET ---
Inventory dictionary after reset (Should be empty): {}
Products dictionary after reset (Should be empty): {}
"""


## Tree test
print("\n\n\n############## Tree Test ####################")
tree = createTree()
print(tree[1][1][0])
print(tree[1][1][2][0])
print(tree[1][0])
print(tree[1][2][0])
print(tree[0])
print(tree[2][1][1][0])
print(tree[2][1][0])
print(tree[2][0])
print(tree[2][2][0])
print(tree[1][1][1])
print(tree[1][1][1])
print(tree[1][1][2][1])
print(tree[1][1][2][2])
print(tree[1][2][1])
print(tree[1][2][2])
print(tree[2][1][1][1])
print(tree[2][1][1][2])
print(tree[2][1][2])
print(tree[2][2][1])
print(tree[2][2][2])

### Tree Test Expected Output
"""
############## Tree Test ####################
1
2
3
4
5
6
7
8
9
None
None
None
None
None
None
None
None
None
None
None
"""
