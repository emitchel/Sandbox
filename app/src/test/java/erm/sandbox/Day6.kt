package erm.sandbox

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.lang.Integer.max
import java.lang.Math.abs

class Day6 {

    @Test
    fun challenge6pt1() {
        val inputToUse = input
        setIdsForInputs(inputToUse)
        //setting bounds
        val maxBottomRightPoint = determineMaxBottomRightPoint(inputToUse)
        //setting up the current list
        val map = MutableList(maxBottomRightPoint.y + 1) {
            MutableList(maxBottomRightPoint.x + 1) {
                "[]"//Ideally there should be none of these []s left once we're done filling it out
            }
        }

        println("Map")
        for (y in 0 until map.size) {
            for (x in 0 until map[0].size) {
                val currentPoint = inputToUse.find { it == Point(x, y) } ?: Point(x, y)

                if (inputToUse.contains(currentPoint)) {
                    map[y][x] = currentPoint.id
                } else {
                    var listOfPointsAndDistances = arrayListOf<PointAndDistanceFromMe>()
                    inputToUse.forEach {
                        //determine the distance from each point
                        listOfPointsAndDistances.add(PointAndDistanceFromMe(it, distanceBetween(currentPoint, it)))
                    }

                    listOfPointsAndDistances.sortBy { it.distance }
                    if (listOfPointsAndDistances[0].distance == listOfPointsAndDistances[1].distance) {
                        //we have at least two matching distances
                        map[y][x] = "."
                    } else {
                        map[y][x] = listOfPointsAndDistances[0].point.closestId
                    }
                }
            }
        }

        println("Map set")
        var listOfBoundedPoints = arrayListOf<Point>()
        for (i in 0 until inputToUse.size) {
            val point = inputToUse[i]

            var boundedAbove = false
            //test from top to point
            for (y in 0 until point.y) {
                if (map[y][point.x] != point.closestId) {
                    //bounded in this direction
                    boundedAbove = true
                    break
                }
            }

            if (!boundedAbove) continue

            var boundedBelow = false
            //test from point to bottom
            for (y in point.y + 1..maxBottomRightPoint.y) {
                if (map[y][point.x] != point.closestId) {
                    //it's bounded in this direction
                    boundedBelow = true
                    break
                }
            }
            if (!boundedBelow) continue

            var boundedToLeft = false
            //test from left to point
            for (x in 0 until point.x) {
                if (map[point.y][x] != point.closestId) {
                    //it's bounded in this direction
                    boundedToLeft = true
                    break
                }
            }
            if (!boundedToLeft) continue

            var boundedToRight = false
            //test from point to right
            for (x in point.x + 1..maxBottomRightPoint.x) {
                if (map[point.y][x] != point.closestId) {
                    boundedToRight = true
                    break
                }
            }
            if (!boundedToRight) continue

            if (boundedAbove && boundedBelow && boundedToLeft && boundedToRight) {
                listOfBoundedPoints.add(point)
            }
        }

        var listOfBoundedPointAndSize = listOfBoundedPoints.map { BoundedPointAndSize(it, 1) }
        listOfBoundedPointAndSize.forEach {
            for (y in 0 until map.size) {
                for (x in 0 until map[0].size) {
                    if (map[y][x] == it.point.closestId) {
                        it.size++
                    }
                }
            }
        }

        println("Specified the boundaries and sizes")
        assertEquals(5358, listOfBoundedPointAndSize.maxBy { it.size }!!.size)
    }

    private fun distanceBetween(p1: Point, p2: Point): Int = abs(p1.x - p2.x) + abs(p1.y - p2.y)

    private fun determineMaxBottomRightPoint(listOfPoints: ArrayList<Point>): Point {
        var point = Point(0, 0)
        listOfPoints.forEach {
            point.x = max(point.x, it.x)
            point.y = max(point.y, it.y)
        }
        return point
    }

    private fun setIdsForInputs(listOfPoints: ArrayList<Point>) {
        listOfPoints.forEachIndexed { index, point ->
            point.id = "[$index]"
            point.closestId = "_${index}_"
        }
    }

    class PointAndDistanceFromMe(val point: Point, val distance: Int)

    class BoundedPointAndSize(val point: Point, var size: Int)

    class Point(var x: Int, var y: Int) {
        var id: String
        var closestId: String

        init {
            id = "$x,$y"
            closestId = "CLOSEST"
        }

        override fun equals(other: Any?): Boolean {
            if (other !is Point) return false
            return (other.x == x && other.y == y)
        }
    }

    val testInput = arrayListOf(
        Point(1, 1),
        Point(1, 6),
        Point(8, 3),
        Point(3, 4),
        Point(5, 5),
        Point(8, 9)
    )

    val input = arrayListOf(
        Point(61, 90),
        Point(199, 205),
        Point(170, 60),
        Point(235, 312),
        Point(121, 290),
        Point(62, 191),
        Point(289, 130),
        Point(131, 188),
        Point(259, 82),
        Point(177, 97),
        Point(205, 47),
        Point(302, 247),
        Point(94, 355),
        Point(340, 75),
        Point(315, 128),
        Point(337, 351),
        Point(73, 244),
        Point(273, 103),
        Point(306, 239),
        Point(261, 198),
        Point(355, 94),
        Point(322, 69),
        Point(308, 333),
        Point(123, 63),
        Point(218, 44),
        Point(278, 288),
        Point(172, 202),
        Point(286, 172),
        Point(141, 193),
        Point(72, 316),
        Point(84, 121),
        Point(106, 46),
        Point(349, 77),
        Point(358, 66),
        Point(309, 234),
        Point(289, 268),
        Point(173, 154),
        Point(338, 57),
        Point(316, 95),
        Point(300, 279),
        Point(95, 285),
        Point(68, 201),
        Point(77, 117),
        Point(313, 297),
        Point(259, 97),
        Point(270, 318),
        Point(338, 149),
        Point(273, 120),
        Point(229, 262),
        Point(270, 136)
    )
}