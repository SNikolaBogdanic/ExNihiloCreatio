#!/usr/bin/env bash

woods=("oak" "birch" "spruce" "jungle" "acacia" "dark_oak" "stone")

modid=exnihilocreatio

textures_dir=src/main/resources/assets/${modid}/textures/
models_dir=src/main/resources/assets/${modid}/models/
states_dir=src/main/resources/assets/${modid}/blockstates/

for w in "${woods[@]}"; do
  for model in "barrel" "sieve"; do
    if [[ "$w" != "stone" ]] || [[ "$model" != "sieve" ]]; then
      texture="minecraft:block/${w}_planks"
      if [[ "$w" == "stone" ]]; then
        texture="minecraft:block/stone"
      fi
      # Generate the block model
      touch ${models_dir}/block/${model}_${w}.json
      cat > ${models_dir}/block/${model}_${w}.json <<EOL
{
  "parent": "${modid}:block/${model}",
  "textures": {"all": "${texture}"}
}
EOL
      # Generate the blockstate
      touch ${states_dir}/${model}_${w}.json
      cat > ${states_dir}/${model}_${w}.json <<EOL
{
  "variants": {"": [{"model": "${modid}:block/${model}_${w}"}]}
}
EOL
      # Generate the item model
      touch ${models_dir}/item/${model}_${w}.json
      cat > ${models_dir}/item/${model}_${w}.json <<EOL
{
  "parent": "${modid}:block/${model}_${w}"
}
EOL
    fi
  done
done