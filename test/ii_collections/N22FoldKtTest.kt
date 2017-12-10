package ii_collections

import ii_collections.data.*
import org.junit.Assert.assertEquals
import org.junit.Test


class N22FoldKtTest {
    @Test fun testGetProductsOrderedByAllCustomers() {
        val testShop = shop("test shop for 'fold'",
                customer(lucas, Canberra,
                        order(idea),
                        order(webStorm)
                ),
                customer(reka, Budapest,
                        order(idea),
                        order(youTrack),
                        order(webStorm)
                ),
                customer(bajram, Ankara,
                        order(idea),
                        order(appCode),
                        order(webStorm)
                ),
                customer(riku, Tokyo,
                        order(idea),
                        order(dotCover),
                        order(webStorm)
                )

        )
        assertEquals(setOf(idea, webStorm), testShop.getSetOfProductsOrderedByEveryCustomer())
    }
}
