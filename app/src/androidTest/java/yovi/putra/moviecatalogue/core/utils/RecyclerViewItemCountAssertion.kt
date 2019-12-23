package yovi.putra.moviecatalogue.core.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import junit.framework.TestCase.assertNotNull
import org.hamcrest.CoreMatchers.`is`

class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        noViewFoundException?.let{
            throw noViewFoundException
        } ?: run {
            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertNotNull(adapter)
            assertThat(adapter!!.itemCount, `is`(expectedCount))
        }
    }
}