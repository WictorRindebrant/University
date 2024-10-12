import login
import send
import readfile
import average
import printresult
import sys

def main():
  # Authentication to copyleaks:
  key = sys.argv[1]
  email = sys.argv[2]
  print("key: " + key)
  print("email: " + email)
  access_token = login.get_access_token(key, email)

  # File handling:
  alldata = readfile.get_data()

  if alldata:

    # Send to copyleaks:
    allresponse = []
    for i in range(len(alldata)):
      data = alldata[i]
      response = send.send_data(str(access_token['access_token']), data["filetext"], data["filemame"])
      allresponse.append(response)

    # Calc average Ai detected from all files:
    averageData = average.average_ai_detected(allresponse)

    # Print to report:
    printresult.print_txt("=================================== Ai Tool Report ===================================")
    printresult.print_txt("Average Ai-generated code found: " + averageData["aiAverage"] + "%")
    printresult.print_txt("Average Human generated code found: " + averageData["humanAverage"] + "%")
    for i in range(len(alldata)):
      data = alldata[i]
      printData = {
      "response": allresponse[i],
      "filepath": data['filepath'],
      "averageData": averageData
      }
      printresult.print_txt("--------------------------------------------------------------------------------------")
      printresult.write_dict_report(printData)
    
    printresult.print_txt("======================================================================================")

    averageAiDetect = int(averageData["aiAverage"])
    warningLimit = 80

    if (averageAiDetect > warningLimit):
      # print("Ai Detected above "+ str(warningLimit) +"% (" + str(averageAiDetect) + "%)")
      exit(1)
    else:
      # print("Ai Detected below "+ str(warningLimit) +"% (" + str(averageAiDetect) + "%)")
      exit(0)
  else:
    print("No files were detected or scanned")

main()
