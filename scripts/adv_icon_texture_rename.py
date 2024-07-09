import json
import sys
import os
import shutil

# Load the mapping from the JSON file
with open('adv_icon_mapping.json', 'r', encoding='utf-8') as file:
    adv_icon_mapping = json.load(file)

# Get the path where the item models are stored, from command line argument
item_model_path = sys.argv[1]
item_texture_path = sys.argv[2]

# Create a new folder named output in item_model_path
output_path = os.path.join(item_texture_path, 'output')
os.makedirs(output_path, exist_ok=True)

# Collect all keys from the adv_icon_mapping dictionary to search against
icon_keys = set(adv_icon_mapping.keys())

# Iterate over each file in the specified directory
for filename in os.listdir(item_model_path):
    # Check if the file is a JSON file
    if filename.endswith('.json'):
        # Remove the '.json' extension to get the base filename
        base_name = filename[:-5]
        old_key = f"mobz:{base_name}"
        # Check if the base filename is in the keys of adv_icon_mapping
        if old_key in icon_keys:
            full_path = os.path.join(item_model_path, filename)
            with open(full_path, 'r', encoding='utf-8') as file:
                data = json.load(file)
                # Access the textures.layer0 if it exists and check texture count
                try:
                    textures = data['textures']
                    if 'layer0' in textures:
                        png_old_name = textures['layer0'].split('/')[-1] + ".png"
                        png_new_name = adv_icon_mapping[old_key].split(':')[-1] + ".png"
                        print(f"{filename} - Layer0 texture: {png_old_name} [{png_new_name}]")
                        # Move and rename the PNG file
                        src_png = os.path.join(item_texture_path, png_old_name)
                        dest_png = os.path.join(output_path, png_new_name)
                        shutil.move(src_png, dest_png)
                    else:
                        print(f"Warning: 'layer0' not found in {filename}")
                    
                    if len(textures) > 1:
                        print(f"Warning: {filename} contains more than one texture element.")
                except KeyError:
                    print(f"Warning: No 'textures' field found in {filename}")

# Move everything in the output folder back to item_model_path
for filename in os.listdir(output_path):
    src_path = os.path.join(output_path, filename)
    dest_path = os.path.join(item_texture_path, filename)
    shutil.move(src_path, dest_path)

# Delete the output folder
os.rmdir(output_path)
print("All files moved back and output folder removed.")
