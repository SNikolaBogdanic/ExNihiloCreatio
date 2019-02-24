#!/usr/bin/env bash

modid=exnihilocreatio

textures_dir=src/main/resources/assets/${modid}/textures/
models_dir=src/main/resources/assets/${modid}/models/
states_dir=src/main/resources/assets/${modid}/blockstates/

# Generate Seed/Crook/Hammer/Doll/Pebble/Silkworm Item Models
for str in "seed" "crook" "hammer" "doll" "pebble" "silkworm"; do
  for i in "$textures_dir"item/${str}*.png; do
    asset=`basename $i .png`
    touch ${models_dir}item/${asset}.json
    cat > ${models_dir}item/${asset}.json <<EOL
{
  "parent": "item/generated",
  "textures": {"layer0": "${modid}:item/${asset}"}
}
EOL
  done
done

# Simple Cube Models
for str in "dust" "silt" "crushed_*"; do
  for i in "$textures_dir"block/${str}.png; do
    asset=`basename $i .png`
    touch ${models_dir}block/${asset}.json
    cat > ${models_dir}block/${asset}.json <<EOL
{
  "parent": "block/cube_all",
  "textures": {"all": "${modid}:block/${asset}"}
}
EOL
    touch ${models_dir}item/${asset}.json
    cat > ${models_dir}item/${asset}.json <<EOL
{
  "parent": "${modid}:block/${asset}"
}
EOL
    touch ${states_dir}${asset}.json
    cat > ${states_dir}${asset}.json <<EOL
{
  "variants": {
    "": [{"model": "${modid}:block/${asset}"}]
  }
}
EOL
  done
done
