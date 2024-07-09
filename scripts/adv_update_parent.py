import json
import sys
import os

# Load the mapping from the JSON file
with open('adv_name_mapping.json', 'r', encoding='utf-8') as file:
    adv_name_mapping = json.load(file)

# Directory path to search for JSON files, taken from command line argument
advancement_path = sys.argv[1]

# Iterate over each file in the specified directory
for filename in os.listdir(advancement_path):
    # Check if the file is a JSON file
    if filename.endswith('.json'):
        full_path = os.path.join(advancement_path, filename)
        
        # Open and load the JSON file
        with open(full_path, 'r', encoding='utf-8') as file:
            try:
                data = json.load(file)
                # Check if 'parent' field exists in the JSON data
                if 'parent' in data:
                    original_parent = data['parent']
                    # Strip the prefix "mobz:mob/" if it exists
                    stripped_parent = original_parent.replace("mobz:mob/", "")
                    
                    # Update the 'parent' field if it exists in the mapping
                    if stripped_parent in adv_name_mapping:
                        new_parent = f"mobz:mob/{adv_name_mapping[stripped_parent]}"
                        data['parent'] = new_parent
                        print(f"Updating '{filename}': '{original_parent}' to '{new_parent}'")

                        # Write the updated data back to the file
                        with open(full_path, 'w', encoding='utf-8') as outfile:
                            json.dump(data, outfile, indent=4)
                    else:
                        print(f"No mapping found for '{stripped_parent}' in '{filename}'")
            except json.JSONDecodeError:
                print(f"Error decoding JSON in file {filename}")

