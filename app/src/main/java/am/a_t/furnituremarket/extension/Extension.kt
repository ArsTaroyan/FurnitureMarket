package am.a_t.furnituremarket.extension

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.google.gson.Gson

fun AppCompatTextView.spannableText(starIndex: Int, endIndex: Int, color: Int) {
    SpannableString(this.text).apply {
        setSpan(
            ForegroundColorSpan(color),
            starIndex,
            endIndex,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        this@spannableText.text = this
    }
}

fun <T> T.convertGsonToString(): String = Gson().toJson(this)

inline fun <reified T> String.convertStringToGson(): T = Gson().fromJson(this, T::class.java)

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}