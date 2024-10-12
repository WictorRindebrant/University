import os

def get_data():
  files = get_python_files(os.getcwd())

  allFileData = []
  for file in files:
    data = {
    "filetext": read_python_file(file),
    "filename": os.path.basename(file),
    "filepath": file
    }
    allFileData.append(data)

  return allFileData

def get_python_files(directory_path):
  code_files = []
  ignored_files_file = "ai-detection-code/ignored_files.txt" #ignores all files in this txt file.
  with open(ignored_files_file, 'r') as f:
    ignored_files = f.read().splitlines()

  for root, dirs, files in os.walk(directory_path):
     for file in files:
        if file.endswith((".rb", ".js", ".ts", ".py", ".cpp", ".c", ".java", ".cs", ".php")): #Supports these code language
           if not file in ignored_files:
            code_files.append(os.path.join(root, file))

  return code_files

def read_python_file(file_path):
  with open(file_path, 'r') as file:
      content = file.read()
  return content