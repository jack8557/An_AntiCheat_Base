package me.godead.anticheat.users

import me.godead.anticheat.extensions.xType
import me.godead.anticheat.utils.XMaterial
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.util.Vector
import java.util.function.Predicate
import kotlin.math.*


// Taken from https://github.com/ElevatedDev/Frequency/blob/master/src/main/java/xyz/elevated/frequency/data/BoundingBox.java
data class BoundingBox(val min: Vector, val max: Vector) {

    var minX = min.x
    var minY = min.y
    var minZ = min.z
    var maxX = max.x
    var maxY = max.y
    var maxZ = max.z

    fun add(minX: Double, minY: Double, minZ: Double, maxX: Double, maxY: Double, maxZ: Double): BoundingBox {
        this.minX += minX
        this.minY += minY
        this.minZ += minZ
        this.maxX += maxX
        this.maxY += maxY
        this.maxZ += maxZ
        return this
    }

    constructor(position: Location) : this(position.x, position.y, position.z)

    constructor(x: Double, y: Double, z: Double) : this(Vector(x,y,z), Vector(x,y,z)) {
        timestamp = System.currentTimeMillis()
    }

    constructor(X: Double, Y: Double, Z: Double, X1: Double, Y1: Double, Z1: Double) : this(Vector(X,Y,Z), Vector(X1,Y1,Z1)) {
        timestamp = System.currentTimeMillis()
    }


    var timestamp: Long = 0

    var center = Vector(centerX, centerY, centerZ)

    fun distance(location: Location): Double {
        return sqrt(
            min(
                (location.x - minX).pow(2.0),
                (location.x - maxX).pow(2.0)
            ) + min(
                (location.z - minZ).pow(2.0),
                (location.z - maxZ).pow(2.0)
            )
        )
    }

    fun distanceS(location: Location): Double {
        return location.distance(center.toLocation(location.world!!))
    }

    fun distance(x: Double, z: Double): Double {
        val dx = min((x - minX).pow(2.0), (x - maxX).pow(2.0))
        val dz = min((z - minZ).pow(2.0), (z - maxZ).pow(2.0))
        return sqrt(dx + dz)
    }

    fun distance(box: BoundingBox): Double {
        val dx =
            min((box.minX - minX).pow(2.0), (box.maxX - maxX).pow(2.0))
        val dz =
            min((box.minZ - minZ).pow(2.0), (box.maxZ - maxZ).pow(2.0))
        return sqrt(dx + dz)
    }

    fun angle(world: World, box: BoundingBox): Float {
        val homeVector = Vector(minX, minY, minZ)
        val outVector = Vector(box.minX, box.minY, box.minZ)
        return outVector.subtract(homeVector).setY(0).angle(outVector.toLocation(world).direction.setY(0))
    }

    fun getDirection(world: World?): Vector {
        return Location(world, minX, minY, minZ).direction
    }

    fun add(box: BoundingBox): BoundingBox {
        minX += box.minX
        minY += box.minY
        minZ += box.minZ
        maxX += box.maxX
        maxY += box.maxY
        maxZ += box.maxZ
        return this
    }

    fun move(x: Double, y: Double, z: Double): BoundingBox {
        minX += x
        minY += y
        minZ += z
        maxX += x
        maxY += y
        maxZ += z
        return this
    }

    fun expand(x: Double, y: Double, z: Double): BoundingBox {
        minX -= x
        minY -= y
        minZ -= z
        maxX += x
        maxY += y
        maxZ += z
        return this
    }

    fun expandMax(x: Double, y: Double, z: Double): BoundingBox {
        maxX += x
        maxY += y
        maxZ += z
        return this
    }

    fun checkBlocks(world: World, predicate: Predicate<XMaterial>): Boolean {
        val n = floor(minX).toInt()
        val n2 = ceil(maxX).toInt()
        val n3 = floor(minY).toInt()
        val n4 = ceil(maxY).toInt()
        val n5 = floor(minZ).toInt()
        val n6 = ceil(maxZ).toInt()
        val list: ArrayList<Block> = ArrayList()
        list.add(world.getBlockAt(n, n3, n5))
        for (i in n until n2) {
            for (j in n3 until n4) {
                for (k in n5 until n6) {
                    list.add(world.getBlockAt(i, j, k))
                }
            }
        }
        return list.stream()
            .allMatch { block: Block -> predicate.test(block.xType()) }
    }

    fun checkBlocksAny(world: World, predicate: Predicate<XMaterial>): Boolean {
        val n = floor(minX).toInt()
        val n2 = ceil(maxX).toInt()
        val n3 = floor(minY).toInt()
        val n4 = ceil(maxY).toInt()
        val n5 = floor(minZ).toInt()
        val n6 = ceil(maxZ).toInt()
        val list: ArrayList<Block> = ArrayList()
        list.add(world.getBlockAt(n, n3, n5))
        for (i in n until n2) {
            for (j in n3 until n4) {
                for (k in n5 until n6) {
                    list.add(world.getBlockAt(i, j, k))
                }
            }
        }
        return list.stream().anyMatch { block: Block -> predicate.test(block.xType()) }
    }



    val centerX: Double
        get() = (minX + maxX) / 2.0

    val centerY: Double
        get() = (minY + maxY) / 2.0

    val centerZ: Double
        get() = (minZ + maxZ) / 2.0

    init {
        if (minX < maxX) {
            this.minX = minX
            this.maxX = maxX
        } else {
            this.minX = maxX
            this.maxX = minX
        }
        if (minY < maxY) {
            this.minY = minY
            this.maxY = maxY
        } else {
            this.minY = maxY
            this.maxY = minY
        }
        if (minZ < maxZ) {
            this.minZ = minZ
            this.maxZ = maxZ
        } else {
            this.minZ = maxZ
            this.maxZ = minZ
        }
    }
}