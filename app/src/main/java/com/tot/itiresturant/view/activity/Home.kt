package com.tot.itiresturant.view.activity

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import com.tot.itiresturant.R
import com.tot.itiresturant.model.Order
import com.tot.itiresturant.recyclerDecoration.GridItemDecoration
import com.tot.itiresturant.utils.ApplicationLanguageHelper
import com.tot.itiresturant.view.adapter.HomeAdapter
import com.tot.itiresturant.viewmodel.OrederViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*


class Home : AppCompatActivity() {
lateinit var orderViewModel: OrederViewModel
lateinit var homeAdapter: HomeAdapter
lateinit var toolbarMenu: Toolbar
lateinit var navigationView: NavigationView
   private val AR_lang = "ar"
   private val En_lang ="en"
   private var lang_Selected =""
    private val SELECTED_LANGUAGE = "language"
    private val LANGUAGE_IS_SELECTED ="SELECTED"
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@Home)
        intiComponent()
    }

    private fun intiComponent() {
        orderViewModel= ViewModelProviders.of(this).get(OrederViewModel::class.java)
        home_RecyclerView.layoutManager=GridLayoutManager(this,2)
        home_RecyclerView.addItemDecoration(GridItemDecoration(10, 2))
        homeAdapter= HomeAdapter(this,this)
        home_RecyclerView.adapter=homeAdapter
        orderViewModel.setHome(this)
        orderViewModel.getAllMenuItems().observe(this, Observer {
            homeAdapter.setData(it as ArrayList<Order>)
            homeAdapter.notifyDataSetChanged()
        })
        navigationView=navigation_home
        setToolBar()
        setListener()
    }


    private fun setToolBar() {
        toolbarMenu=toolbar
        setSupportActionBar(toolbarMenu)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_foreground)
        toolbarMenu.title="Menu"
    }
    private fun setListener() {
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_Item_Profile -> { }
                R.id.menu_item_myOrder->{}
                R.id.menu_item_setting->{showLangDialog()}
                R.id.menu_item_signout->{}
            }
            true

        }
    }

    /**
     * change Language
     */
    private fun showLangDialog() {
        val langValues= arrayOf(getString(R.string.arabic), getString(R.string.english))

        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(getString(R.string.choose_language))
                .setSingleChoiceItems(langValues,-1,{dialog,which->
                    if (which ==0){
                        lang_Selected = "ar"
                    } else if(which == 1){
                        lang_Selected = "en"
                    }
                })
            setPositiveButton(getString(R.string.ok), DialogInterface.OnClickListener { dialog, i -> changeApplicationLanguage(lang_Selected) })
            setNegativeButton(getString(R.string.cancel), null)

        }
        val alertDialog = builder.create()

    }
    private fun changeApplicationLanguage(language:String){
        val sharedPreferencesEditor = sharedPreferences.edit()
        when (language) {
            "en" -> sharedPreferencesEditor?.putString(SELECTED_LANGUAGE, En_lang)
            "ar" -> sharedPreferencesEditor?.putString(SELECTED_LANGUAGE, AR_lang)

        }
        sharedPreferencesEditor.putBoolean(LANGUAGE_IS_SELECTED, true)
        sharedPreferencesEditor?.apply()
        recreate()
    }
    override fun attachBaseContext(newBase: Context?) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(newBase)
        val lang = sharedPreferences.getString(SELECTED_LANGUAGE, "en")
        super.attachBaseContext(ApplicationLanguageHelper.wrap(newBase!!, lang!!))
    }

}
