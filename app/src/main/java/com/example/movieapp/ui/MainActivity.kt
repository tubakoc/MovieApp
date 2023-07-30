package com.example.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.movieapp.R
import com.example.movieapp.common.viewBinding
import com.example.movieapp.databinding.ActivityMainBinding
import android.app.AlertDialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.databinding.AddAlertDialogDesignBinding
import com.example.movieapp.common.showDatePicker
import com.example.movieapp.common.showTimePicker
import com.example.movieapp.data.model.Film
import com.example.movieapp.data.source.Database
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        with(binding)
        {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)

            navHostFragment.navController.addOnDestinationChangedListener { controller, destination, _ ->
                if (destination.id == R.id.filmDetayFragment) {
                    bottomNavigationView.visibility = View.GONE
                    floatingActionButton.visibility = View.GONE
                } else {
                    bottomNavigationView.visibility = View.VISIBLE
                    floatingActionButton.visibility = View.VISIBLE
                }
            }
            floatingActionButton.setOnClickListener {
                showAddDialog()
            }
        }
    }


    private fun showAddDialog(){
        val builder = AlertDialog.Builder(this@MainActivity)
        val binding = AddAlertDialogDesignBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        val dialog = builder.create()

        val saveTypeList = listOf("Watched Movie", "Movie to Watch")

        var selectedSaveType = ""

        var selectedDate = ""

        val saveTypeAdapter = ArrayAdapter(
            applicationContext,
            androidx.transition.R.layout.support_simple_spinner_dropdown_item,
            saveTypeList
        )

        with(binding)
        {
            val calendar = Calendar.getInstance()

            actSaveType.setAdapter(saveTypeAdapter)

            actSaveType.setOnItemClickListener { _, _, position, _ ->
                selectedSaveType = saveTypeList[position]
            }

            switchDate.setOnCheckedChangeListener { _, ischecked ->
                if (ischecked)
                {
                    showDatePicker(calendar) { year, month, day ->
                        showTimePicker(calendar) { hour, minute ->
                            selectedDate = "$day.$month.$year - $hour:$minute"
                            tvDate.text = "$day.$month.$year - $hour:$minute"
                            tvDate.visibility = View.VISIBLE
                        }
                    }
                }

            }
            btnAdd.setOnClickListener {
                val filmAd = etFilmAd.text.toString()
                val filmDsc = etDesc.text.toString()
                val yonetmenAd = etYonetmen.text.toString()

                if(filmAd.isNotEmpty() && filmDsc.isNotEmpty() && yonetmenAd.isNotEmpty() && selectedDate.isNotEmpty())
                {
                    if(selectedSaveType == saveTypeList[0])
                    {
                        Database.izlenenFilmEkle(
                            filmAd,
                            yonetmenAd,
                            filmDsc,
                            isWatched = true,
                            selectedDate
                        )
                    }      else if(selectedSaveType == saveTypeList[1])
                    {
                        Database.izlenecekFilmEkle(
                            filmAd,
                            yonetmenAd,
                            filmDsc,
                            isWatched = false,
                            selectedDate
                        )
                    }
                    dialog.dismiss()

                 /*   Database.filmEkle(filmAd,yonetmenAd,filmDsc,false,selectedDate)
                    dialog.dismiss()*/

                }
                else
                {
                    Toast.makeText(this@MainActivity,"Please fill in all fields.",Toast.LENGTH_LONG).show()
                }
            }

            btnCancel.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}