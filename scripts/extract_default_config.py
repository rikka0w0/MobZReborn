import json

# Load JSON mapping from file
with open('./adv_name_mapping.json', 'r') as file:
    original_mapping = json.load(file)
    modified_mapping = {key.replace('entity.mobz.', ''): value.replace('entity.mobz.', '')
                        for key, value in original_mapping.items()}

# List of suffixes to check against
suffixes = ['Spawn', 'Life', 'Attack', 'SpawnRate']

def split_suffix(s):
    for suffix in suffixes:
        if s.endswith(suffix):
            return s[:-len(suffix)], suffix
    return s, None

result = {}

# Process the config file according to the rules
with open('./Config.txt', 'r', encoding='utf-8') as file:
    for line in file:
        stripped_line = line.strip()
        if stripped_line.startswith('@') or '=' not in stripped_line:
            continue  # Skip annotations and lines without assignments

        # Split the line around '=' to get the variable name and its value
        var_before_value, var_value = [part.strip() for part in stripped_line.split('=')]
        var_value = var_value.replace(';', '')
        # Extract the suffix (assuming the format is always 'name_suffix')
        var_before_value_parts = var_before_value.split()
        if len(var_before_value_parts) < 2:
            continue  # Skip if the format is not as expected

        var_type = var_before_value_parts[1]
        var_fullname = var_before_value_parts[2]

        # Check if the suffix is one of the expected ones
        var_name, suffix = split_suffix(var_fullname)
        if suffix:
            if not var_name in result:
                result[var_name] = {}
            result[var_name][suffix] = var_value
            #print(f"{var_type} {var_name} {suffix} = {var_value}")
        else:
            if not var_name in result:
                result[var_fullname] = {}
            result[var_fullname]['value'] = var_value
            result[var_fullname]['type'] = var_type
            #print(f"Unrecognized suffix in {var_fullname}")

#print(result)

for key in result.keys():
    settingGroup = result[key]
    if "value" in settingGroup:
        print(f"    public {settingGroup['type']} {key} = {settingGroup['value']};")
    else:
        print(f"    @ConfigEntry.Gui.CollapsibleObject")
        life = settingGroup['Life'] if 'Life' in settingGroup else 114514
        attack = settingGroup['Attack'] if 'Attack' in settingGroup else 114514
        spawnRate = settingGroup['SpawnRate'] if 'SpawnRate' in settingGroup else 114514
        spawn = settingGroup['Spawn'] if 'Spawn' in settingGroup else 114514
        print(f"    public Mob {key} = new Mob({spawn}, {spawnRate}, {life}, {attack});")
