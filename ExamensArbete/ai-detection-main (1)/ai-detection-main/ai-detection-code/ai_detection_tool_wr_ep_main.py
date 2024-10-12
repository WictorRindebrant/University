import ai_detection_tool_wr_ep_login as login
import ai_detection_tool_wr_ep_send as send
import ai_detection_tool_wr_ep_read_file as read_file
import ai_detection_tool_wr_ep_print_result as print_result
import ai_detection_tool_wr_ep_info as info
import sys

def main():
  # Authentication to copyleaks:
  key = sys.argv[1]
  email = sys.argv[2]

  print("key: " + key)
  print("email: " + email)
  access_token = login.get_access_token(key, email)

  # File handling:
  alldata = read_file.get_data()

  if alldata:
    # Send to copyleaks:
    allresponse = []
    for i in range(len(alldata)):
      data = alldata[i]
      response = send.send_data(str(access_token['access_token']), data["filetext"], data["filename"])
      allresponse.append(response)

    # Gather all the nessesary information from the response:
    gatheredData = info.gather_info(allresponse, alldata)

    # Sorts the data dict so that the one with the highest ai detection is at the start of the list.
    sortedData = info.sort_by_ai(gatheredData)

    # Print to report:
    print_result.print_txt("=================================== Ai Tool Report ===================================")
    print_result.print_txt("            * The file with the highest amount of Ai-generated code: " + str(sortedData[0]["ai"]) + "% *")
    for sortedD in sortedData:
      print_result.print_txt("--------------------------------------------------------------------------------------")
      print_result.write_dict_report(sortedD)
    
    print_result.print_txt("======================================================================================")

    # For the pipeline to gives warning or pass.
    highestAiDetect = sortedData[0]["ai"]
    warningLimit = 80

    if (highestAiDetect > warningLimit):
      exit(1)
    else:
      exit(0)
  else:
    print("No files were detected or scanned")

main()
