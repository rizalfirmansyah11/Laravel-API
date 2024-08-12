package com.rizal.laravelapi.activity.tambahData

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.internal.bind.ArrayTypeAdapter
import com.rizal.laravelapi.R
import com.rizal.laravelapi.config.NetworkConfig
import com.rizal.laravelapi.databinding.ActivityTambahMahasiswaBinding
import com.rizal.laravelapi.model.SubmitMahasiswa
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class TambahMahasiswaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahMahasiswaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityTambahMahasiswaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val items = listOf("Islam", "Kristen", "Hindu", "Budha" )
        val adapter = ArrayAdapter(this, R.layout.list_agama, items)
        binding.dropdownField.setAdapter(adapter)

        binding.saveButton.setOnClickListener{
            saveData()
        }
        }

    private fun saveData() {
        val agama = binding.dropdownField.text.toString()
        val rblaki = binding.rbLaki
        val rbperempuan = binding.rbPerempuan

        val gender = if (rblaki.isChecked){
            rblaki.text.toString()
        }else{
            rbperempuan.text.toString()
        }
        val namalengkap = binding.editTextName.text.toString()
        val nim = binding.editTextNim.text.toString()
        val alamat = binding.editTextAlamat.text.toString()
        val usia = binding.editTextUsia.text.toString()

        val retofit = NetworkConfig().getServices()
        if (namalengkap.isNotEmpty() && nim.isNotEmpty() && alamat.isNotEmpty() && gender.isNotEmpty() && agama.isNotEmpty() && usia.isNotEmpty()) {
            retofit.postMahasiswa(namalengkap, nim, alamat, gender, agama, usia)
                .enqueue(object : retrofit2.Callback<SubmitMahasiswa> {
                    override fun onResponse(
                        call: Call<SubmitMahasiswa>,
                        response: Response<SubmitMahasiswa>
                    ) {
                        if (response.isSuccessful) {
//                            val hasil = response.body()
                            Toast.makeText(applicationContext, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                            namalengkap.isEmpty()
                            nim.isEmpty()
                            alamat.isEmpty()
                            gender.isEmpty()
                            agama.isEmpty()
                            usia.isEmpty()
                        }

                    }

                    override fun onFailure(call: Call<SubmitMahasiswa>, t: Throwable) {
                        Toast.makeText(
                            applicationContext, "Data Gagal Disimpan: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                })
        }else{
            Toast.makeText(applicationContext, "Field tidak boleh kosong",
            Toast.LENGTH_SHORT).show()
        }
    }
}
