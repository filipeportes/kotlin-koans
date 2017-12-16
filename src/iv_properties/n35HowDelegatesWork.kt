package iv_properties

import iii_conventions.MyDate
import util.TODO
import java.time.LocalDate
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun todoTask35(): Nothing = TODO(
    """
        Task 35.
        A delegate expression must have special 'get' and 'set' methods.
        You can see their signatures below as members of the 'ReadWriteProperty' interface.

        Implement the members of the class 'EffectiveDate' so it can be delegated to.
        Store only the time in milliseconds in 'timeInMillis' property.
        Use the extension functions 'MyDate.toMillis' and 'Long.toDate'.
    """,
    references = { date: MyDate -> date.toMillis().toDate()}
)

class D {
    var date by EffectiveDate()
    // The property date$delegate of type EffectiveDate is created;
    // the generated 'get' and 'set' accessors for 'date' are delegated to it.
    // You can look at the bytecode (by calling "Show Kotlin Bytecode" action in IntelliJ IDEA) for details.
}

class EffectiveDate<R> : ReadWriteProperty<R, MyDate> {
    var timeInMillis: Long = 0

    operator override fun getValue(thisRef: R, property: KProperty<*>): MyDate {
        return timeInMillis.toDate()
    }

    operator override fun setValue(thisRef: R, property: KProperty<*>, value: MyDate) {
        timeInMillis = value.toMillis()
    }
}

fun Long.toDate(): MyDate {
    val d = LocalDate.ofEpochDay(this)
    return MyDate(d.year, d.monthValue - 1, d.dayOfMonth)
}
