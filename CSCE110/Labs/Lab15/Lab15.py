from Lab15_provided import find_first_valid_element, scale_and_clamp_collection


def find_first_valid_element_test():
    test_cases = [
        ([5, 6, 7], 5, 100, False, 0),  # lower bound inclusive
        ([10, 11, 12], 1, 10, False, 0),  # upper bound inclusive
        ([3, 4, 5], 2, 6, True, 1),  # must_be_even skips odd
        ([2.2, "z", 5], 1, 10, False, 2),  # non-int skipped
        ([2, 4, 6], 1, 10, False, 0),  # first match, not last
        ([1, 3, 5], 10, 20, False, -1),  # no match
        ([9, 9, 9, 4], 1, 10, True, 3),  # exact index check
    ]

    passed = 0
    for data, lower_bound, upper_bound, must_be_even, expected in test_cases:
        result = find_first_valid_element(data, lower_bound, upper_bound, must_be_even)
        if result == expected:
            passed += 1
            print(
                f"PASSED: data={data}, lower_bound={lower_bound}, "
                f"upper_bound={upper_bound}, must_be_even={must_be_even} | "
                f"expected={expected}, got={result}"
            )
        else:
            print(
                f"FAILED: data={data}, lower_bound={lower_bound}, "
                f"upper_bound={upper_bound}, must_be_even={must_be_even} | "
                f"expected={expected}, got={result}"
            )

    print(f"find_first_valid_element_test: {passed}/{len(test_cases)} passed")


def scale_and_clamp_collection_test():
    test_cases = [
        ([2, 3], 2, 0, 100, [4, 6]),  # scaled, in place
        ([2, 3], -10, -5, 100, [-5, -5]),  # clamp low
        ([5], 10, 0, 40, [40]),  # clamp high
        ([True, False, 2], 5, 0, 100, [True, False, 10]),  # bools unchanged
        ([1, "z", None, 3], 5, 0, 100, [5, "z", None, 15]),  # skip non-numeric
        ([1.5], 2, 0, 100, [3.0]),  # floats
    ]

    passed = 0
    for items, multiplier, lower_limit, upper_limit, expected in test_cases:
        items_copy = items.copy()
        original_id = id(items_copy)
        return_value = scale_and_clamp_collection(
            items_copy, multiplier, lower_limit, upper_limit
        )
        same_object = id(items_copy) == original_id
        if items_copy == expected and return_value is None and same_object:
            passed += 1
            print(
                f"PASSED: items={items}, multiplier={multiplier}, "
                f"lower_limit={lower_limit}, upper_limit={upper_limit} | "
                f"expected={expected}, got={items_copy}"
            )
        else:
            print(
                f"FAILED: items={items}, multiplier={multiplier}, "
                f"lower_limit={lower_limit}, upper_limit={upper_limit} | "
                f"expected={expected}, got={items_copy}, return_value={return_value}"
            )

    print(f"scale_and_clamp_collection_test: {passed}/{len(test_cases)} passed")
