from numpy import number
from sqlalchemy import false, true
import pwoDatabase

# Calls the file that creates a databse if it does not already excist.
cursor = pwoDatabase.cursor
cnx = pwoDatabase.cnx

# This is the main menu where the user can decide what function to use.
def mainMenu(cursor):
    choise = -1
    while choise != 0:
        print("")
        print("+" + (50 * "-") + ">Main Menu<" + (50 * "-") + "+")
        print("| Enter an integer to use a function:                                                                           |")                                                                        
        print("| [1] -> Lists the name of the pwo and the person who ordered the pwo with 200mg coffein or more per dose       |")
        print("| [2] -> List of all pwo's were you can see the price per gram, ordered from the cheapest to the most expensive |")
        print("| [3] -> Lists the calculated total value of all the persons orders                                             |")
        print("| [4] -> Creates a new table with all the pwo's attribues in gram instead of in dose                            |")
        print("| [5] -> Creates a new table with all pwo that are in stock                                                     |")
        print("+" + (50 * "-") + ">0 to Exit<" + (50 * "-") + "+")

        choise = int(input("What function do you want to use? "))
        while choise < 0 or choise > 5:
            choise = int(input("Their is no function for " + str(choise) + ". Enter a new function: "))

        if choise == 1:
           moreCoffein(cursor)

        if choise == 2:
           mostValue(cursor)
        
        if choise == 3:
           totalValue(cursor)

        if choise == 4:
           doseToGram(cursor)

        if choise == 5:
            showAllInStock(cursor)


# This function will print out the name of the one that made the order followed by the pwo name and koffein
# if the koffein on that pwo is above 199 mg koffein. It will take informatin from the pwo_order and pwo table
def moreCoffein(cursor):
    query = "SELECT Pwo.Pwo_name, pwo_order.name, Koffein_per_dose_in_mg " \
            "FROM pwo " \
            "JOIN pwo_order " \
            "ON Pwo.Pwo_name = pwo_order.pwo_name " \
            "WHERE Koffein_per_dose_in_mg > 199 "
    cursor.execute(query)

    print("")
    for (pwo_name, name, koffein) in cursor:
        print(name + " ordered " + pwo_name + " that contains " + str(koffein) + " mg koffein per dose")
    print("")

    puase = input("Press a Enter to get back to main menu: ")


# This function will print out the pwo's name followed by the price of that pwo devided by the size of that
# pwo. When dividing the price of the pwo with the size of it, we will get out the price in gram for all of the
# different pwo's. In the end we order it by the price in grams with the lowest price first.
# In this function we are taking infromation from the pwo and pwo_reseller table.
def mostValue(cursor):
    query = "SELECT pwo_reseller.pwo_name, MIN(price_in_kr / pwo.size_in_grams) " \
            "AS Result " \
            "FROM pwo " \
            "JOIN pwo_reseller " \
            "ON pwo_reseller.pwo_name = pwo.pwo_name LIKE '%UTF8' " \
            "GROUP BY pwo_reseller.pwo_name " \
            "ORDER BY Result"
    cursor.execute(query)

    print("")
    counter = 0
    for (name, reslut) in cursor:
        counter+= 1
        print(str(counter) + ". " + name + " cost: " + str(reslut) + " kr per gram")
    print("")

    puase = input("Press a Enter to get back to main menu: ")


# In this function we are calculating the total value of a customers orders in total. We are doing this by
# taking the pwo's price times the quantity a customer have ordered then adding all the different pwo's a
# customer have ordered so that we got a total sum for what a customer have ordered.
# In the end we order those total values by lowest first.
# We are taking information from the pwo_reseller and pwo_order table in this function.
def totalValue(cursor):
    query = "SELECT pwo_order.name, pwo_order.lastname, SUM(pwo_reseller.price_in_kr * pwo_order.quantity) as totValue " \
            "FROM pwo_reseller " \
            "JOIN pwo_order " \
            "ON pwo_reseller.pwo_name = pwo_order.pwo_name " \
            "AND pwo_reseller.reseller_name = pwo_order.re_seller " \
            "GROUP BY pwo_order.name, pwo_order.lastname " \
            "ORDER BY totValue"
            
    cursor.execute(query)

    print("")
    for (name, lastname, tot) in cursor:
        print("-> " + name + " " + lastname + "s order value is: " + str(tot) + " kr")
    print("")

    puase = input("Press a Enter to get back to main menu: ")


# In this function we are creating a new table where we are taking all the attributes from the pwo
# table divided by the dose in grams for those pwo's so that we are getting out all the attributes
# in grams instrad of in doese. We are only using information from the pwo table here.
# If the table already excist it just return the user to the main menu.
def doseToGram(cursor):
    doesExcist = tableExcist(cursor, 'pwo_value_per_gram')
    if doesExcist == false:
        query = "CREATE VIEW pwo_value_per_gram AS " \
                "SELECT pwo_name, " \
                "Koffein_per_dose_in_mg / Dose_in_grams, " \
                "Beta_alanin_per_dose_in_mg / Dose_in_grams, " \
                "L_citrulinmalat_per_dose_in_mg / Dose_in_grams," \
                "Trikreatinmalat_per_dose_in_mg / Dose_in_grams, " \
                "Beetroot_extract_per_dose_in_mg / Dose_in_grams, " \
                "AAKG_per_dose_in_mg / Dose_in_grams, " \
                "L_teanin_per_dose_in_mg / Dose_in_grams," \
                "Citrulline_malate_per_dose_in_mg / Dose_in_grams " \
                "FROM pwo"

        cursor.execute(query)
        print()
        print("Table View Created for pwo_value_per_gram")
        print()
    else:
        print()
        print("Table with the 'pwo_value_per_gram' DOES ALREADY EXCIST")
        print()

    puase = input("Press a Enter to get back to main menu: ")


# In this function we are creating a new table where are are taking the information from
# the pwo_reseller table with the reseller_name, pwo_name and in_stock colums and only the
# rows where the in_stock value is 'yes'. This way we getting a table with all the pwo's
# that are in stock. If the table already excist it just return the user to the main menu.
def showAllInStock(cursor):
    doesExcist = tableExcist(cursor, 'pwo_in_stock')
    if doesExcist == false:
        query = "CREATE VIEW pwo_in_stock AS " \
                "SELECT reseller_name, pwo_name, in_stock " \
                "FROM pwo_reseller " \
                "WHERE in_stock = 'yes'"

        cursor.execute(query)
        print()
        print("Table View Created for pwo_in_stock")
        print()
    else:
        print()
        print("Table with the 'name pwo_in_stock' DOES ALREADY EXCIST")
        print()

    puase = input("Press a Enter to get back to main menu: ")


# This function checks if a table excist with the name that the user sends in to the
# function. Returns true if it excists and false if it does'nt
def tableExcist(cursor, name):
    cursor.execute("SHOW TABLES")
    excist = false
    for (f) in cursor:
        for i in f:
            if i == name:
                excist = true
    return excist


# Main menu function for the created database
mainMenu(cursor)

cursor.close()
cnx.close()
