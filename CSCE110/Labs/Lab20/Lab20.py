class Vector:
    def __init__(self, elements):
        self.elements = elements

    def __add__(self, other):
        return Vector([a + b for a, b in zip(self.elements, other.elements)])

    def __sub__(self, other):
        return Vector([a - b for a, b in zip(self.elements, other.elements)])

    def __mul__(self, other):
        return Vector([a * b for a, b in zip(self.elements, other.elements)])

    def __truediv__(self, other):
        res = []
        for a, b in zip(self.elements, other.elements):
            # spec: divide by zero gives None for that element, not an error
            res.append(a / b if b != 0 else None)
        return Vector(res)

    def __eq__(self, other):
        return self.elements == other.elements

    def __str__(self):
        return str(self.elements)


class Employee:
    def __init__(self, name, id_num, department, title):
        self.name = name
        self.id_num = id_num
        self.department = department
        self.title = title

    def __str__(self):
        return (
            f"{'Name'.ljust(10)} : {self.name}\n"
            f"{'ID'.ljust(10)} : {self.id_num}\n"
            f"{'Department'.ljust(10)} : {self.department}\n"
            f"{'Job Title'.ljust(10)} : {self.title}"
        )


class Employee_Directory:
    def __init__(self, company):
        self.company = company
        self.employees = {}

    def add_employee(self, e):
        self.employees[e.id_num] = e

    def __str__(self):
        parts = [f"Company : {self.company}"]
        for eid, e in self.employees.items():
            parts.append(f"\nID : {eid}\n{e}")
        return "\n".join(parts)
