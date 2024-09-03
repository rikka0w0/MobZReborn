import os
import json

# Get the path to the current directory
current_directory = os.getcwd()

# Iterate through all files in the current directory
for filename in os.listdir(current_directory):
    if filename.endswith('.json'):
        file_path = os.path.join(current_directory, filename)
        
        # Open the JSON file and load its content
        with open(file_path, 'r', encoding='utf-8') as file:
            data = json.load(file)
        
        # Sort the JSON data by keys
        sorted_data = json.dumps(data, indent=4, sort_keys=True)
        
        # Save the sorted JSON data back to the file
        with open(file_path, 'w', encoding='utf-8') as file:
            file.write(sorted_data)
        
        print(f"Sorted {filename}")
