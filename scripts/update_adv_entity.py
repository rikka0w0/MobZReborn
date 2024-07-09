import os
import json
import argparse

def convert_to_short_form(full_mapping):
    # Convert full key names to shortened form
    return {"mobz:" + key.split('.')[-1]: "mobz:" + value.split('.')[-1] for key, value in full_mapping.items()}

directory = sys.argv[1]
# Load the key mapping
with open('entity_name_mapping.json', 'r', encoding='utf-8') as file:
    full_mapping = json.load(file)
key_mapping = convert_to_short_form(full_mapping)

adv_description_mapping = {}
adv_name_mapping = {}
adv_icon_mapping = {}

# Navigate through each file in the specified directory
for filename in os.listdir(directory):
    if filename.endswith(".json"):
        file_path = os.path.join(directory, filename)
        
        # Open and load the JSON file
        with open(file_path, 'r', encoding='utf-8') as file:
            data = json.load(file)
        
        # Check the 'criteria' key in the JSON data
        if 'criteria' in data:
            criteria = data['criteria']
            if len(criteria) > 1:
                print(f"Warning: '{filename}' has multiple JSON objects in 'criteria'.")
            
            # Process each THE_ONLY_JSON_OBJECT in 'criteria'
            for key, value in criteria.items():
                if 'conditions' in value and 'entity' in value['conditions']:
                    entity = value['conditions']['entity']
                    if 'type' in entity:
                        old_type = entity['type']
                        # Update the entity type using the key mapping
                        new_type = key_mapping.get(old_type, old_type)  # Default to old type if no mapping found
                        entity['type'] = new_type
                        print(f"In file '{filename}'")
                        print(f"  {key}: {old_type} -> {new_type}")
                        mob_name = new_type.split(':')[-1]
                        new_title = "entity.mobz." + mob_name
                        print("  display.title.translate = " + new_title)
                        data['display']['title']['translate'] = new_title
                        # Icon renaming
                        old_icon = data['display']['icon']['item']
                        new_icon = f"mobz:head_{mob_name}"
                        adv_icon_mapping[old_icon] = new_icon
                        data['display']['icon']['item'] = new_icon
                        # Update adv_description_mapping based on display.description.translate
                        if 'description' in data['display'] and 'translate' in data['display']['description']:
                            translate_key = data['display']['description']['translate']
                            new_desc = f"mobz.advancements.{mob_name}.description"
                            adv_description_mapping[translate_key] = new_desc
                            data['display']['description']['translate'] = new_desc
                        # Advancement mapping, old -> new
                        adv_name_mapping[filename[:-5] if filename.endswith(".json") else filename] = mob_name
        # Save the updated JSON data back to file
        with open(file_path, 'w', encoding='utf-8') as file:
            json.dump(data, file, indent=4, ensure_ascii=False)
with open("adv_description_mapping.json", 'w', encoding='utf-8') as file:
    json.dump(adv_description_mapping, file, indent=4, ensure_ascii=False)
with open("adv_name_mapping.json", 'w', encoding='utf-8') as file:
    json.dump(adv_name_mapping, file, indent=4, ensure_ascii=False)
with open("adv_icon_mapping.json", 'w', encoding='utf-8') as file:
    json.dump(adv_icon_mapping, file, indent=4, ensure_ascii=False)

