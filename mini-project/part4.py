import os
import matplotlib.pyplot as plt
import BstMap as bst

# Path to output of eng_news_100K-sentences.txt
base_path = os.getcwd() + "\\"
path = base_path + "output_1918499_words.txt"

words = []
# Open output file and store content in list
with open(path, "r", encoding="utf-8") as file:
    for word in file:
        words.append(word.strip())


occurrences = bst.BstMap()
for word in words:
    # Add words from list to the dictionary with value 0 if not already present
    if occurrences.get(len(word)) is None:
        occurrences.put(len(word), 0)
    # Increase value of word by 1 for every word
    value = occurrences.get(len(word)) + 1
    occurrences.put(len(word), value)
# Return dictionary as list with items
occurrences = occurrences.as_list()

# Sort list after key and split up into list with only keys and only values
occurrences = sorted(occurrences)
occ_keys = [n[0] for n in occurrences]
occ_values = [n[1] for n in occurrences]

# List with all the position of the x-axis
x_pos = list(range(1, len(occurrences) + 1))
# Add data, labels and title to plot
plt.bar(x_pos, occ_values)
plt.xticks(x_pos, occ_keys)
plt.title("Word length vs word count")
plt.xlabel("Word length")
plt.ylabel("Count")
plt.show()
