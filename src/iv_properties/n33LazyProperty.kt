package iv_properties

import util.TODO
import kotlin.reflect.KProperty

class LazyProperty(val initializer: () -> Int) {
    var initialized = false
    val lazy: Int = 0
        get() {
            if (!initialized) {
                field = initializer()
                initialized = true
            }
            return field
        }

    operator fun getValue(lazyPropertyUsingDelegates: LazyPropertyUsingDelegates, property: KProperty<*>): Int {
        return lazy
    }
}

fun todoTask33(): Nothing = TODO(
    """
        Task 33.
        Add a custom getter to make the 'lazy' val really lazy.
        It should be initialized by the invocation of 'initializer()'
        at the moment of the first access.
        You can add as many additional properties as you need.
        Do not use delegated properties yet!
    """,
    references = { LazyProperty({ 42 }).lazy }
)
