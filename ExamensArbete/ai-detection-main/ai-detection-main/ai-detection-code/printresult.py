import os

def write_dict_report(data):
  filePath = data["filepath"]
  response = data["response"]

  human = str(round(response['summary']['human'] * 100))
  ai = str(round(response['summary']['ai'] * 100))

  print("File name: " + os.path.basename(filePath))
  print("File path: " + filePath)
  print("AI percantage: " + ai + "%")
  print("Human percantage: " + human + "%")

def print_txt(text):
  print(text)
