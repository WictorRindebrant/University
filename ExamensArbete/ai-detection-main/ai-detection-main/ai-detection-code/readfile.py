import os

def get_data():
  files = get_python_files(os.getcwd())

  allFileData = []
  for file in files:
    data = {
    "filetext": read_python_file(file),
    "filemame": os.path.basename(file),
    "filepath": file
    }
    allFileData.append(data)

  return allFileData

def get_python_files(directory_path):
  code_files = []

  for root, dirs, files in os.walk(directory_path):
     for file in files:
        if file.endswith(".py"):
           if not os.path.basename(root) == "ai-detection-code": # Ignores the ai_detection folder to be scanned.
            code_files.append(os.path.join(root, file))

  return code_files

def read_python_file(file_path):
  with open(file_path, 'r') as file:
      content = file.read()
  return content