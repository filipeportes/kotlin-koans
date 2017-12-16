package iii_conventions

import java.time.LocalDate

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    private val localDate = LocalDate.of(year, month + 1, dayOfMonth)
    operator override fun compareTo(other: MyDate): Int {
        return localDate.compareTo(other.localDate)
    }

    fun nextDay() = this + TimeInterval.DAY

    operator fun plus(interval: TimeInterval) = addTimeIntervals(interval, 1)

    operator fun plus(r: RepeatedTimeInterval) = addTimeIntervals(r.ti, r.n)

    fun addTimeIntervals(timeInterval: TimeInterval, number: Long): MyDate {
        val add = when (timeInterval) {
            TimeInterval.DAY -> localDate.plusDays(number)
            TimeInterval.WEEK -> localDate.plusWeeks(number)
            TimeInterval.YEAR -> localDate.plusYears(number)
        }
        return MyDate(add.year, add.monthValue - 1, add.dayOfMonth)
    }

    fun toMillis(): Long = localDate.toEpochDay()
}

operator fun MyDate.rangeTo(other: MyDate) = DateRange(this, other)

class RepeatedTimeInterval(val ti: TimeInterval, val n: Long)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(i: Long): RepeatedTimeInterval {
        return RepeatedTimeInterval(this, i)
    }
}

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(date: MyDate): Boolean {
        return start <= date && endInclusive >= date
    }

    operator fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var next = start
            var first = true
            override fun hasNext(): Boolean {
                return next < endInclusive
            }

            override fun next(): MyDate {
                if (!first) {
                    next = next.nextDay()
                } else {
                    first = false
                }
                return next
            }

        }
    }
}
