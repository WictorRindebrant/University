import mysql.connector
import csv
from mysql.connector import errorcode


cnx = mysql.connector.connect(user='root', password='root', unix_socket='/Applications/MAMP/tmp/mysql/mysql.sock',)

DB_NAME = 'optimized_purchase'

cursor = cnx.cursor()


# Creating a database.
def create_database(cursor, DB_NAME):
    try:
        cursor.execute("CREATE DATABASE {} DEFAULT CHARACTER SET 'utf8'".format(DB_NAME))
    except mysql.connector.Error as err:
        print("Faild to create database {}".format(err))
        exit(1)


# Creating the pwo table.
def create_table_pwo(cursor):
    create_pwo = "CREATE TABLE `pwo` (" \
                 "  `Pwo_id` int(11) AUTO_INCREMENT," \
                 "  `Pwo_name` varchar(40)," \
                 "  `Koffein_per_dose_in_mg` SMALLINT," \
                 "  `Beta_alanin_per_dose_in_mg` SMALLINT," \
                 "  `L_citrulinmalat_per_dose_in_mg` SMALLINT," \
                 "  `Trikreatinmalat_per_dose_in_mg` SMALLINT," \
                 "  `Beetroot_extract_per_dose_in_mg` SMALLINT," \
                 "  `AAKG_per_dose_in_mg` SMALLINT," \
                 "  `L_teanin_per_dose_in_mg` SMALLINT," \
                 "  `Citrulline_malate_per_dose_in_mg` SMALLINT," \
                 "  `Dose_in_grams` SMALLINT," \
                 "  `Size_in_grams` SMALLINT," \
                 "  PRIMARY KEY (`Pwo_id`)" \
                 ") ENGINE=InnoDB"

    try:
        print("Creating table pwo: ")
        cursor.execute(create_pwo)
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists.")
        else:
            print(err.msg)
    else:
        print("Table pwo Successfully created!")


# Creating the pwo_order table.
def create_table_pwo_order(cursor):
    create_pwo_order = "CREATE TABLE `pwo_order` (" \
                 "  `Name_id` int(11) AUTO_INCREMENT," \
                 "  `Name` varchar(30)," \
                 "  `Lastname` varchar(30)," \
                 "  `Customer_id` SMALLINT," \
                 "  `Phone_number` INT," \
                 "  `Adress` varchar(50)," \
                 "  `Shipping_time_in_days` SMALLINT," \
                 "  `Pwo_name` varchar(40)," \
                 "  `Quantity` SMALLINT," \
                 "  `Re_seller` varchar(40)," \
                 "  `Country` varchar(40)," \
                 "  `Community` varchar(40)," \
                 "  PRIMARY KEY (`Name_id`)" \
                 ") ENGINE=InnoDB"

    try:
        print("Creating table pwo_order: ")
        cursor.execute(create_pwo_order)
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists.")
        else:
            print(err.msg)
    else:
        print("Table pwo_order Successfully created!")


# Creating the pwo_reseller table.
def create_table_pwo_reseller(cursor):
    create_pwo_reseller = "CREATE TABLE `pwo_reseller` (" \
                 "  `Reseller_id` int(11) AUTO_INCREMENT," \
                 "  `Reseller_name` varchar(30)," \
                 "  `Pwo_name` varchar(40)," \
                 "  `Price_in_kr` SMALLINT," \
                 "  `In_stock` varchar(10)," \
                 "  `Shipping_price_in_kr` SMALLINT," \
                 "  PRIMARY KEY (`Reseller_id`)" \
                 ") ENGINE=InnoDB"

    try:
        print("Creating table pwo_reseller: ")
        cursor.execute(create_pwo_reseller)
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists.")
        else:
            print(err.msg)
    else:
        print("Table pwo_reseller Successfully created!")


# Inserting all the values from the pwo.csv file to the pwo table.
def insert_into_pwo(cursor):
    file1 = csv.reader(open('pwo.csv'))
    # file1 = csv.reader(open('/Users/simpz/Desktop/Databasteknologi/pwo/database_project_pwo/pwo.csv'))
    next(file1)
    for row in file1:
        new_row = []
        for word in row:
            if word == 'NA' or word == 'N/A' or word == 'NULL':
                word = None
            new_row.append(word)
        insert_sql = ("INSERT INTO pwo(Pwo_name, Koffein_per_dose_in_mg, Beta_alanin_per_dose_in_mg, L_citrulinmalat_per_dose_in_mg,"
                      "Trikreatinmalat_per_dose_in_mg, Beetroot_extract_per_dose_in_mg, AAKG_per_dose_in_mg, L_teanin_per_dose_in_mg,"
                      "Citrulline_malate_per_dose_in_mg, Dose_in_grams, Size_in_grams)"
                      "VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)")
        # https://www.projectpro.io/recipes/connect-mysql-python-and-import-csv-file-into-mysql-and-create-table

        try:
            print("SQL query INSERT INTO pwo(Pwo_name, Koffein_per_dose_in_mg, Beta_alanin_per_dose_in_mg, L_citrulinmalat_per_dose_in_mg,"
                  "Trikreatinmalat_per_dose_in_mg, Beetroot_extract_per_dose_in_mg, AAKG_per_dose_in_mg, L_teanin_per_dose_in_mg,"
                  "Citrulline_malate_per_dose_in_mg, Dose_in_grams, Size_in_grams)VALUE", tuple(new_row))
            cursor.execute(insert_sql, tuple(new_row))
        except mysql.connector.Error as err:
            print(err.msg)
        else:
            cnx.commit()
            print("SQL query 'pwo' Successfully created!")


# Inserting all the values from the pwo_order.csv file to the pwo_order table.
def insert_into_pwo_order(cursor):
    file1 = csv.reader(open('pwo_order.csv'))
    # file1 = csv.reader(open('/Users/simpz/Desktop/Databasteknologi/pwo/database_project_pwo/pwo_order.csv'))
    next(file1)
    for row in file1:
        new_row = []
        for word in row:
            if word == 'NA' or word == 'N/A' or word == 'NULL':
                word = None
            new_row.append(word)
        insert_sql = ("INSERT INTO pwo_order(Name, Lastname, Customer_id, Phone_number, Adress, Shipping_time_in_days, Pwo_name, Quantity, Re_seller, Country, Community)"
                      "VALUES(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)")
        # https://www.projectpro.io/recipes/connect-mysql-python-and-import-csv-file-into-mysql-and-create-table

        try:
            print("SQL query INSERT INTO pwo_order(Name, Lastname, Customer_id, Phone_number, Adress, Shipping_time_in_days, Pwo_name, Quantity, Re_seller, Country, Community)VALUE", tuple(new_row))
            cursor.execute(insert_sql, tuple(new_row))
        except mysql.connector.Error as err:
            print(err.msg)
        else:
            cnx.commit()
            print("SQL query 'pwo_order' Successfully created!")


# Inserting all the values from the pwo_reseller.csv file to the pwo_reseller table.
def insert_into_pwo_reseller(cursor):
    file1 = csv.reader(open('pwo_reseller.csv'))
    # file1 = csv.reader(open('/Users/simpz/Desktop/Databasteknologi/pwo/database_project_pwo/pwo_reseller.csv'))
    next(file1)
    for row in file1:
        new_row = []
        for word in row:
            if word == 'NA' or word == 'N/A' or word == 'NULL':
                word = None
            new_row.append(word)
        insert_sql = ("INSERT INTO pwo_reseller(Reseller_name, Pwo_name, Price_in_kr, In_stock, Shipping_price_in_kr)"
                      "VALUES(%s, %s, %s, %s, %s)")
        # https://www.projectpro.io/recipes/connect-mysql-python-and-import-csv-file-into-mysql-and-create-table

        try:
            print("SQL query INSERT INTO pwo_reseller(Reseller_name, Pwo_name, Price_in_kr, In_stock, Shipping_price_in_kr)VALUE", tuple(new_row))
            cursor.execute(insert_sql, tuple(new_row))
        except mysql.connector.Error as err:
            print(err.msg)
        else:
            cnx.commit()
            print("SQL query 'pwo_reseller' Successfully created!")


# Checks if a database excist with the DB_NAME value. If it does not excist the creates one
# and if it does excist then it does not do anything.
try:
    cursor.execute("USE {}".format(DB_NAME))
except mysql.connector.Error as err:
    print("Database {} does not exist".format(DB_NAME))
    if err.errno == errorcode.ER_BAD_DB_ERROR:
        create_database(cursor, DB_NAME)
        print("Database {} created succesfully.".format(DB_NAME))
        cnx.database = DB_NAME
        create_table_pwo(cursor)
        create_table_pwo_reseller(cursor)
        create_table_pwo_order(cursor)
        insert_into_pwo(cursor)
        insert_into_pwo_reseller(cursor)
        insert_into_pwo_order(cursor)

    else:
        print(err)
