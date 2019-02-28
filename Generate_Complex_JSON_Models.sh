#!/usr/bin/env bash

woods=("oak" "birch" "spruce" "jungle" "acacia" "dark_oak" "stone" "stone_unfired")

modid=exnihilocreatio

textures_dir=src/main/resources/assets/${modid}/textures/
models_dir=src/main/resources/assets/${modid}/models/
states_dir=src/main/resources/assets/${modid}/blockstates/

for w in "${woods[@]}"; do
  for model in "barrel" "crucible" "sieve"; do
    if [[ "$w" != "stone_unfired" ]] || [[ "$model" == "crucible" ]]; then
      if [[ "$w" != "stone" ]] || [[ "$model" != "sieve" ]]; then
        texture="minecraft:block/${w}_planks"
        if [[ "$w" == "stone" ]]; then
          texture="minecraft:block/stone"
        fi
        if [[ "$model" == "crucible" ]]; then
          texture="minecraft:block/${w}_log"
          if [[ "$w" == "stone" ]]; then
            texture="${modid}:block/crucible_stone"
          fi
          if [[ "$w" == "stone_unfired" ]]; then
            texture="${modid}:block/crucible_stone_unfired"
          fi
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
        if [[ "$model" == "sieve" ]]; then
        cat > ${states_dir}/${model}_${w}.json <<EOL
{
  "multipart": [
    {"apply": {"model": "exnihilocreatio:block/sieve_${w}"}},
    { "when": {"mesh": "string"},
      "apply": {"model":"exnihilocreatio:item/mesh_string"}
    },
    { "when": {"mesh": "flint"},
      "apply": {"model":"exnihilocreatio:item/mesh_flint"}
    },
    { "when": {"mesh": "iron"},
      "apply": {"model":"exnihilocreatio:item/mesh_iron"}
    },
    { "when": {"mesh": "diamond"},
      "apply": {"model":"exnihilocreatio:item/mesh_diamond"}
    }
  ]
}
EOL
        else
        cat > ${states_dir}/${model}_${w}.json <<EOL
{
  "variants": {"": [{"model": "${modid}:block/${model}_${w}"}]}
}
EOL
        fi
        # Generate the item model
        touch ${models_dir}/item/${model}_${w}.json
        cat > ${models_dir}/item/${model}_${w}.json <<EOL
{
  "parent": "${modid}:block/${model}_${w}"
}
EOL
      fi
    fi
  done
done