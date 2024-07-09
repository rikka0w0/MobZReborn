import json
import sys
import os
import shutil

# Load the mapping from the JSON file
with open('adv_name_mapping.json', 'r', encoding='utf-8') as file:
    adv_name_mapping = json.load(file)

# Directory path to search for JSON files, taken from command line argument
advancement_path = sys.argv[1]
output_path = os.path.join(advancement_path, 'output')

# Create the output folder if it doesn't already exist
os.makedirs(output_path, exist_ok=True)

# Iterate over each file in the specified directory
for filename in os.listdir(advancement_path):
    # Check if the file is a JSON file
    if filename.endswith('.json'):
        base_name = filename[:-5]  # Remove '.json' extension
        # Check if there's a mapping for this file
        if base_name in adv_name_mapping:
            new_name = adv_name_mapping[base_name] + '.json'
            # Construct the source and destination paths
            src_path = os.path.join(advancement_path, filename)
            dest_path = os.path.join(output_path, new_name)
            # Move and rename the file
            shutil.move(src_path, dest_path)
            print(f"Moved '{filename}' to '{new_name}'")

# Move all files from output folder back to the advancement_path
for filename in os.listdir(output_path):
    src_path = os.path.join(output_path, filename)
    dest_path = os.path.join(advancement_path, filename)
    shutil.move(src_path, dest_path)

# Remove the output folder
os.rmdir(output_path)
print("All files moved back and output folder removed.")
