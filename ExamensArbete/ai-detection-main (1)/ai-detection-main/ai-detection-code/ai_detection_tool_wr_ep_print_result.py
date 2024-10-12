import os

# Prints a specific dict information.
def write_dict_report(data):
  print("File path: " + data["filepath"])
  print("File name: " + data["filename"])
  print("AI percantage: " + str(data["ai"]) + "%")
  print("Human percantage: " + str(data["human"]) + "%")

# Prints out a string.
def print_txt(text):
  print(text)
