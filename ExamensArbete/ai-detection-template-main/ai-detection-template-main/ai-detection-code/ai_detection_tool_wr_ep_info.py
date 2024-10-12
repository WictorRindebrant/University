import os

# Takes the useful information from copyleaks response.
def gather_info(responseData, fileData):
  gatheredData = []

  fileIndex = 0
  for response in responseData:
    fileInfo = fileData[fileIndex]
    data = {
      "ai": round(response['summary']['ai'] * 100),
      "human": round(response['summary']['human'] * 100),
      "words": response['scannedDocument']['totalWords'],
      "filepath": fileInfo["filepath"],
      "filename": fileInfo["filename"]
    }
    gatheredData.append(data)
    fileIndex += 1
  
  return gatheredData

# Sorts the list with the highest ai value as the first one.
def sort_by_ai(data):
  sortedData = sorted(data, key=lambda x: x['ai'], reverse=True)
  return sortedData