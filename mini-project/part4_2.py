import os
import matplotlib.pyplot as plt
import HashSet as hset

# Path to output of eng_news_100K-sentences.txt
base_path = os.getcwd() + "\\"
path = base_path + "output_1918499_words.txt"

words = []
# Open output file and store content in list
with open(path, "r", encoding="utf-8") as file:
    for word in file:
        words.append(word.strip())

# Initialize the set
set = hset.HashSet()
set.init()

# Add words from list to set
set_size = []
for word in words:
    set.add(word)
    # Add size of set after every word is added to a list
    set_size.append(set.get_size())

# List with all the position of the x-axis
x_pos = list(range(1, len(set_size) + 1))
# Add data, labels and title to plot
plt.plot(x_pos, set_size)
plt.title("Amount of unique words after adding words")
plt.xlabel("Words being added")
plt.ylabel("Unique word count")
plt.show()
