import requests
# "sandbox": "true"

# Sends the data to copyleaks (ai detection tool).
def send_data(access_token, text, filename):

  url = "https://api.copyleaks.com/v2/writer-detector/source-code/scanId/check"

  # print(text)
  # print(filename)

  payload = {
      "text": text,
      "filename": filename,
      "sandbox": "true"
  }
  headers = {
      'Authorization': "Bearer " + access_token,
      "Content-Type": "application/json",
      "Accept": "application/json"
  }

  response = requests.post(url, json=payload, headers=headers)
  
  return response.json()
