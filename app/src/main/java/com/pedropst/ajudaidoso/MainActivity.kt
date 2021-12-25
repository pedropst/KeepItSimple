package com.pedropst.ajudaidoso

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Space

class MainActivity : AppCompatActivity() {
    val CALL_PERMISSION_CODE = 1
    var lastRowId = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callButton = findViewById<Button>(R.id.button10)
        val smsButton = findViewById<Button>(R.id.button11)
        val toolsButton = findViewById<Button>(R.id.button20)
        val whatsButton = findViewById<Button>(R.id.button21)
        val settingsButton = findViewById<Button>(R.id.button30)
        val emergencyButton = findViewById<Button>(R.id.button31)


        val btnCount = loadBtnCount().toInt()

        addingButton(btnCount)


        toolsButton.setOnClickListener{

        }

        callButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            if(callIntent != null)
            {
                startActivity(callIntent)
            }
        }

        emergencyButton.setOnClickListener{
            val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
            val number = sharedPref.getString("emergNum", null).toString()

            val callIntent = Intent(Intent.ACTION_CALL)

            callIntent.data = Uri.parse("tel:" + number)
            startActivity(callIntent)
        }

        whatsButton.setOnClickListener{
            val whatsIntent = packageManager.getLaunchIntentForPackage("com.whatsapp")
            if(whatsIntent != null)
            {
                startActivity(whatsIntent)
            }
        }

        smsButton.setOnClickListener{
            val smsIntent = Intent(Intent.ACTION_MAIN)
            smsIntent.addCategory(Intent.CATEGORY_APP_MESSAGING)
            if(smsIntent != null)
            {
                startActivity(smsIntent)
            }
        }

        settingsButton.setOnClickListener {
                val settingsIntent = Intent(this, Settings::class.java)
                startActivity(settingsIntent)
        }
    }

    private fun addingButton(btnCount : Int){
        if(btnCount > 6)
        {
            var odd = false
            if(btnCount % 2 == 0){
                lastRowId = btnCount / 2
                odd = false
            } else {
                lastRowId = btnCount / 2 + 1
                odd = true
            }

            for(i in 4..lastRowId)
            {
                val layoutID = resources.getIdentifier("row$i", "id", packageName)
                val newRow = findViewById<LinearLayout>(layoutID)

                newRow.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
                newRow.visibility = View.VISIBLE
                if(odd)
                {
                    var btnID = resources.getIdentifier("button$lastRowId" + "0", "id", packageName)
                    var lastButton = findViewById<Button>(btnID)
                    lastButton.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0f
                    )

                    val spaceID = resources.getIdentifier("spaceRow$lastRowId", "id", packageName)
                    val lastSpace = findViewById<Space>(spaceID)
                    lastSpace.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        0f
                    )

                    btnID = resources.getIdentifier("button$lastRowId" + "1", "id", packageName)
                    lastButton = findViewById<Button>(btnID)
                    lastButton.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun createButtonDynamically() {
        // creating the button
        val newButton = Button(this)
        val newLinearLayout = LinearLayout(this)

        newLinearLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        newLinearLayout.orientation = LinearLayout.HORIZONTAL
//      newLinearLayout.id = "@+id/teste1".toInt()

        // setting layout_width and layout_height using layout parameters
        newButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        newButton.text = "Dynamic Button"
        newButton.setBackgroundColor(Color.GREEN)
        // add Button to LinearLayout

        val mainLayout = findViewById<LinearLayout>(R.id.teste)
        mainLayout.addView(newLinearLayout)
        newLinearLayout.addView(newButton)
    }


    private fun loadBtnCount() : String
    {
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        return if(sharedPref.getString("btnCount", "6") == null) {
            ""

        } else {
            sharedPref.getString("btnCount", "6").toString()
        }

    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        fun innerCheck(name: String){
//            if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)
//            {
//                Toast.makeText(applicationContext, "$name permissão recusada", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(applicationContext, "$name permissão aceita", Toast.LENGTH_SHORT).show()
//            }
//
//            when(requestCode){
//                CALL_PERMISSION_CODE -> innerCheck("dial")
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }
//
//    private fun checkForPermissions(permission: String, name: String, requestCode: Int){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            when{
//                ContextCompat.checkSelfPermission(applicationContext, permission) == PackageManager.PERMISSION_GRANTED ->{
//                    Toast.makeText(applicationContext, "$name permission granted", Toast.LENGTH_SHORT).show()
//                }
//                shouldShowRequestPermissionRationale(permission) -> showDialog(permission, name, requestCode)
//
//                else -> ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
//            }
//        }
//    }
//
//    private fun showDialog(permission: String, name: String, requestCode: Int){
//        val builder = AlertDialog.Builder(this)
//        builder.apply {
//            setMessage("Permissão para acessar $name é necessária para esse aplicativo")
//            setTitle("Permissão requisitada")
//            setPositiveButton("OK"){ dialog, which ->
//                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
//            }
//        }
//
//        val dialog = builder.create()
//        dialog.show()
//    }
}