package com.example.my2

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val falha : MutableList<String> = mutableListOf("رو بر رهش نهادم و بر من گذر نکرد",
                                                            "یا رب این شمع دل افروز ز کاشانه کیست",
                                                            "به کوی میکده یا رب سحر چه مشغله بود",
                                                            "در این زمانه رفیقی که خالی از خلل است",
                                                            "رفتم به باغ صبحدمی تا چنم گلی")

    private val fal_meaning : MutableList<String> = mutableListOf(
        "اگر قرار بود هر کسی به سادگی به مقصود برسد، دنیا گلستان می شد. هر شکست مقدمه ی پیروزی است. اظهار عجز و ناتوانی را رها کن و دوباره به سعی و تلاش بپرداز تا به موفقیت برسی.",
        "آرزوی رسیدن به مقصود را داری و رسیدن به آن را سخت و دشوار می دانی. نا امید نباش. با کمی صبر و تحمل به مقصود خواهی رسید.",
        "خودت را با دیگری مقایسه نکن و گله از بخت خود نداشته باش. تمام مشغله و کارهای درهم تو سر و سامان پیدا می کند، مانعی پیش پای تو است که با فکر و تدبیر خودت برداشته می شود. درد تو درمان پذیر است، کمی حوصله داشته باش.",
        "زندگی را سخت نگیر و قانع باش. دوست یکرنگ و صادق کم گیر می آید. به خود و لطف خدا اعتماد کن. شادی و سرور دنیا گذران است و دائمی نیست. از زندگی خود لذت ببر و با قناعت زندگی کن.",
        "هرگز بدون رنج و زحمت به جایی نخواهی رسید. بنابراین بهتر است که کار و کوشش کنی تا به مقصود برسی. زندگی همیشه بر وفق مراد نمی چرخد. باید صبر و تحمل کرد و رنج و زحمت ها را به شادی و سرور تبدیل کرد.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generate_fal()
        about()
    }

    private fun generate_fal() {
        val fal_textView: TextView = findViewById(R.id.fal_textView) as TextView
        val fal_meaning_textView: TextView = findViewById(R.id.fal_meaning_textView) as TextView
        val rnd_button: Button = findViewById(R.id.rnd_button) as Button
        rnd_button.setOnClickListener {
            val rand : Int = (0 until 4).random()
            fal_textView.text = falha[rand]
            fal_meaning_textView.text = fal_meaning[rand]
        }
    }

    private fun about() {
        val about_button: Button = findViewById(R.id.about_button) as Button
        about_button.setOnClickListener {
            openDialog()
        }
    }

    public fun openDialog() {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.dialogTitle)
        //set message for alert dialog
        builder.setMessage(R.string.dialogMessage)
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
}