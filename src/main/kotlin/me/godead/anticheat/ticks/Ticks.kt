package me.godead.anticheat.ticks

class Ticks(private var creation: Long) {

    fun setTicks(time: Long) =  run { creation = time }

    fun getTicks() = (System.currentTimeMillis() - creation).toInt() / 100

}