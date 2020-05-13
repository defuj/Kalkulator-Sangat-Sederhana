package id.co.bigtek.defuj17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var hasil = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        functions()
    }

    private fun functions() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Masukan Nama")
        val inputNama = EditText(this)
        inputNama.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(inputNama)
        builder.setPositiveButton("Oke") { dialog, _ ->
            if(inputNama.text.isNotEmpty()){
                txtNama!!.text = inputNama.text
                dialog!!.dismiss()
                showDialog("Selamat Datang","${txtNama.text}",SweetAlertDialog.SUCCESS_TYPE)
            }
        }
        val alert = builder.create()
        alert.setCancelable(false)
        alert.show()

        btnKali!!.setOnClickListener {
            buttonFunction("kali")
        }

        btnBagi!!.setOnClickListener {
            buttonFunction("bagi")
        }

        btnTambah!!.setOnClickListener {
            buttonFunction("tambah")
        }

        btnKurang!!.setOnClickListener {
            buttonFunction("kurang")
        }

        btnExit!!.setOnClickListener {
            val dialog = SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
            dialog.titleText = "Perhatian"
            dialog.contentText = "Anda ingin keluar dari aplikasi?"
            dialog.confirmText = "Ya"
            dialog.cancelText = "Tidak"
            dialog.setConfirmClickListener {
                dialog.dismissWithAnimation()
                finish()
            }
            dialog.setOnDismissListener {
                dialog.dismissWithAnimation()
            }
            dialog.show()
        }

        btnAbout!!.setOnClickListener {
            showDialog("About","Aplikasi kalkulator sederhana",SweetAlertDialog.NORMAL_TYPE)
        }
    }

    private fun buttonFunction(action : String){
        if(nilaiPertama!!.text.isNotEmpty()){
            if(nilaiKedua!!.text.isNotEmpty()){
                val nilai1 : Int = nilaiPertama!!.text.toString().toInt()
                val nilai2 : Int = nilaiKedua!!.text.toString().toInt()

                hasil = when (action) {
                    "kali" -> {
                        nilai1*nilai2
                    }
                    "bagi" -> {
                        nilai1/nilai2
                    }
                    "tambah" -> {
                        nilai1+nilai2
                    }
                    else -> {
                        nilai1-nilai2
                    }
                }

                txtHasil!!.text = "$hasil"
            }else{
                showDialog("Perhatian","Nilai kedua masih kosong",SweetAlertDialog.WARNING_TYPE)
            }
        }else{
            showDialog("Perhatian","Nilai pertama masih kosong",SweetAlertDialog.WARNING_TYPE)
        }
    }

    private fun showDialog(title : String, content : String, type : Int){
        val dialog = SweetAlertDialog(this,type)
        dialog.titleText = title
        dialog.contentText = content
        dialog.show()
    }
}
