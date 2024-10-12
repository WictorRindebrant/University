1DV607 

Jonatan Steger : js226fh@student.lnu.se 

Wictor Rindebrant : wr222bp@student.lnu.se

Simon Cahling : sc222uu@student.lnu.se

Object Oriented Analysis and design using UML

How to register a boat – We assume that the member is already registered in the boat club, and that berth objects are created for the boat club. We create a boat object and fill it with values chosen and then connect it to the member. Because we already assumed the member is already registered in the boat club this will mean that the boat will be registered through the member.

How to remove a boat – You got a member in the boat club, and you can there after going into the members boat list and then delete the boat you do not wish to have left in the boat club from that boat list. 

(Before you remove the boat you can check if the boat is assigned to a berth, because if so, you need to delete the boat from the berth slot. That way the slot will appear available)

How to change boat – You go through the member and select a boat through the members boat list and there after changing its attributes. 

How to assign berths – First we check if there are any berth slots are available and the boat, we register fits for the measurements of the berth slot. If we find a berth slot that fits the needs of the boat and is available, then we can link the boat with the berth slot and the berth to the boat. We link the boat to the berth because we want to be able to see where boats are parked and to see what berths are not available. 

How to manage calendar events – The secretary can create events through the calendar.

How to list calendar events / How to show calendar events – The member goes through an assumed existing calendar, through the calendar the member can choose either to view all upcoming events and/or signup to the event of the members choosing. You go through the calendar list with events. Calendar will be filled with event objects that will project all the calendar events.

![Domain model](https://gitlab.lnu.se/1dv607/student/wr222bp/a1_2_3/-/blob/main/The_jolly_pirate.png)