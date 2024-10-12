import requests

# Getting an authentication token from copyleaks.
def get_access_token(key, email):
    url = "https://id.copyleaks.com/v3/account/login/api"

    payload = {
        "key": key,
        "email": email
    }
    headers = {
        "Content-Type": "application/json",
        "Accept": "application/json"
    }

    response = requests.post(url, json=payload, headers=headers)

    return response.json()