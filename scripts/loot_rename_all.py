import json
import sys
import os
import shutil

with open('entity_name_mapping.json', 'r', encoding='utf-8') as file:
    key_mapping = json.load(file)

loot_path = sys.argv[1]

# Create a new folder named output in item_model_path
output_path = os.path.join(loot_path, 'output')
os.makedirs(output_path, exist_ok=True)

for old_key in key_mapping:
    old_entity_name = old_key.split(".")[2]
    old_path = os.path.join(loot_path, old_entity_name + ".json")
    if not os.path.exists(old_path):
        print(f"Unknown file {old_path}")

# Iterate over all files in the specified directory
for filename in os.listdir(loot_path):
    if filename.endswith(".json"):
        # Remove '.json' to extract the entity name part
        old_entity_name = filename[:-5]
        old_key = f"entity.mobz.{old_entity_name}"
        
        # Check if the entity name exists in the mapping
        if old_key in key_mapping:
            new_key = key_mapping[old_key]
            # Extract the new entity name
            new_entity_name = new_key.split(".")[2]
            new_filename = f"{new_entity_name}.json"

            # Construct old and new file paths
            old_filepath = os.path.join(loot_path, filename)
            new_filepath = os.path.join(output_path, new_filename)

            # Rename the file and print the change
            shutil.move(old_filepath, new_filepath)
            print(f"Renamed '{old_filepath}' to '{new_filepath}'")
        else:
            print(f"Unknown {filename}")

# Move everything in the output folder back to item_model_path
for filename in os.listdir(output_path):
    src_path = os.path.join(output_path, filename)
    dest_path = os.path.join(loot_path, filename)
    shutil.move(src_path, dest_path)

# Delete the output folder
os.rmdir(output_path)