package am.a_t.furnituremarket.presentation

import am.a_t.furnituremarket.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openFragment(fragment: Fragment, addToBackStack: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerParent, fragment, fragment.javaClass.simpleName)
            if (addToBackStack) {
                addToBackStack(fragment.javaClass.simpleName)
            }
            commit()
        }
    }

}