def average_ai_detected(data):
  humanSum = 0
  aiSum = 0

  for resp in data:
    human = resp['summary']['human']
    ai = resp['summary']['ai']
    humanSum += human
    aiSum += ai
  
  humanAverage = str(round(humanSum / len(data) * 100))
  aiAverage = str(round(aiSum / len(data) * 100))

  averageData = {
    "humanAverage": humanAverage,
    "aiAverage": aiAverage
    }

  return averageData