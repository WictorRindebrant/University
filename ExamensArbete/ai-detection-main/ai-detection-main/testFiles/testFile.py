class Boat:
    def __init__(self, name, color, length):
        self.name = name
        self.color = color
        self.length = length

class BoatClub:
    def __init__(self):
        self.members = []

    def register_member(self, name, age, membership_type, boats):
        member = {
            "name": name,
            "age": age,
            "membership_type": membership_type,
            "boats": boats
        }
        self.members.append(member)

    def display_members(self):
        for idx, member in enumerate(self.members, start=1):
            print(f"Member {idx}:")
            print(f"Name: {member['name']}")
            print(f"Age: {member['age']}")
            print(f"Membership Type: {member['membership_type']}")
            print("Boats:")
            for boat in member['boats']:
                print(f"\tName: {boat.name}, Color: {boat.color}, Length: {boat.length}")
            print()

# Example usage:
if __name__ == "__main__":
    club = BoatClub()

    # Creating some boats
    boat1 = Boat("Boat1", "Blue", 10)
    boat2 = Boat("Boat2", "Red", 8)

    # Registering members
    club.register_member("John Doe", 30, "Gold", [boat1])
    club.register_member("Jane Smith", 25, "Silver", [boat2])

    # Displaying members
    club.display_members()
