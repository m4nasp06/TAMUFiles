class InvalidProtonNumberError(Exception):
    pass


class InvalidNeutronNumberError(Exception):
    pass


class UndiscoveredElementError(Exception):
    pass


class UndefinedReactionError(Exception):
    pass


class Element:
    def __init__(self, name, protons, neutrons):
        if protons < 1:
            raise InvalidProtonNumberError("Proton number cannot be negative")
        if protons > 118:
            raise UndiscoveredElementError(
                "Proton number is higher than the maximum element discovered so far"
            )
        for n in neutrons:
            if n < 0:
                raise InvalidNeutronNumberError("Number of neutrons can not be negative")

        self.name = name
        self.protons = protons
        self.neutrons = neutrons.copy()

    def getIsotopes(self):
        return [f"{self.name}-{(self.protons + n)}" for n in self.neutrons]


class Group1Element(Element):
    reactions = {'water': 'hydroxide', 'oxygen': 'oxide'}

    def __init__(self, name, protons, neutrons):
        super().__init__(name, protons, neutrons)

    def react(self, reactant):
        if reactant not in Group1Element.reactions:
            raise UndefinedReactionError(f"Reaction with {reactant} is not defined")
        return f"{self.name} {Group1Element.reactions[reactant]}"
