package exnihilocreatio.utils

import net.minecraft.util.math.shapes.IBooleanFunction
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.util.math.shapes.VoxelShapes

object VoxelShapeHelper {
    fun union(vararg shapes: VoxelShape): VoxelShape {
        var output = VoxelShapes.empty()
        for(shape in shapes){
            output = VoxelShapes.combine(output, shape, IBooleanFunction.OR)
        }
        return output.simplify()
    }

    fun intersection(vararg shapes: VoxelShape): VoxelShape {
        var output = VoxelShapes.fullCube()
        for(shape in shapes){
            output = VoxelShapes.combine(output, shape, IBooleanFunction.AND)
        }
        return output.simplify()
    }
}