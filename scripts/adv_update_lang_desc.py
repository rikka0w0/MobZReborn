import json
import sys

# Path to the second JSON file taken from command-line arguments
lang_json_path = sys.argv[1]

# Open and read the JSON file with the description mappings
with open('adv_description_mapping.json', 'r', encoding='utf-8') as file:
    adv_description_mapping = json.load(file)

# Open and read the second JSON file specified by lang_json_path
with open(lang_json_path, 'r', encoding='utf-8') as file:
    lang_json_data = json.load(file)

# Examine each key in the top level of lang_json_data
for key in lang_json_data:
    if key in adv_description_mapping:
        # Print the key and the corresponding value from adv_description_mapping
        # print(f"\"{key}\": \"{adv_description_mapping[key]}\"")
        print(f"    \"{adv_description_mapping[key]}\": \"{lang_json_data[key]}\",")
