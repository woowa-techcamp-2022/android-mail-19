package com.example.camp_mail.ui.activity

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import com.example.camp_mail.R
import com.example.camp_mail.databinding.ActivityMainBinding
import com.example.camp_mail.model.MailViewModel
import com.example.camp_mail.model.UserInfoViewModel
import com.example.camp_mail.ui.fragments.MailFragment
import com.example.camp_mail.ui.fragments.SettingFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var currentSelectedView = ""
    private lateinit var binding: ActivityMainBinding
    private lateinit var fmManager: FragmentManager
    private val userInfoViewModel: UserInfoViewModel by viewModels()
    private val mailViewModel: MailViewModel by viewModels()
    private var widthdpi : Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        apply {
            userInfoViewModel.setUserNickName(intent.getStringExtra("userNickName").toString())
            userInfoViewModel.setUserEmail(intent.getStringExtra("userEmail").toString())
        }

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        checkDisplaySize()

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu) // 홈버튼 이미지 변경
        // 네비게이션 드로어 생성

        binding.navView.setNavigationItemSelectedListener(this)

        // 네비게이션 드로어 내에있는 화면의 이벤트를 처리하기 위해 생성

        if(savedInstanceState != null){
            currentSelectedView = savedInstanceState.getString("currentSelectedView").toString()
        }
        else{
            currentSelectedView = "MAIL"
        }

        checkNavigationFocused()
        setFrag(currentSelectedView) // default 화면
        Log.d("cur screen 2", currentSelectedView)

    }

    private fun checkDisplaySize() {
        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val density = resources.displayMetrics.density
        widthdpi = outMetrics.widthPixels / density
        Log.d("widthhdpi ", widthdpi.toString())
        if(widthdpi >=600 ){
            initNavRail()
        }
        else{
            initBottomNavigation()
        }
    }
    private fun checkNavigationFocused(){
        Log.d("cur state", currentSelectedView)
        if(widthdpi < 600 ){
            if(currentSelectedView == "MAIL"){
                binding.bottomNavBar!!.selectedItemId = R.id.bottomNav_showMail
            }
            else{
                binding.bottomNavBar!!.selectedItemId = R.id.bottomNav_showSetting
            }
        }
        else{
            if(currentSelectedView == "MAIL"){
                binding.navRail!!.selectedItemId = R.id.bottomNav_showMail
            }
            else{
                binding.navRail!!.selectedItemId = R.id.bottomNav_showSetting
            }
        }
    }

    private fun initNavRail() {
        binding.navRail!!.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomNav_showMail -> {
                    Log.d("rail Nav ", "mail selected")
                    setFrag("MAIL")
                    true
                }
                R.id.bottomNav_showSetting -> {
                    Log.d("rail Nav ", "setting selected")
                    setFrag("SETTING")
                    true
                }
                else -> true
            }
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNavBar!!.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomNav_showMail -> {
                    Log.d("bottom Nav ", "mail selected")
                    setFrag("MAIL")
                    true
                }
                R.id.bottomNav_showSetting -> {
                    Log.d("bottom Nav ", "setting selected")
                    setFrag("SETTING")
                    true
                }
                else -> true
            }
        }
    }

    private fun setFrag(page: String) {
        fmManager = supportFragmentManager
        val transaction = fmManager.beginTransaction()
        when (page) {
            "MAIL" -> {
                transaction.replace(binding.hostFrag.id, MailFragment())
                mailViewModel.changeMailListSet(mailViewModel.curState)

                currentSelectedView = "MAIL"

            }
            "SETTING" -> {
                transaction.replace(binding.hostFrag.id, SettingFragment())
                currentSelectedView = "SETTING"
            }

        }
        transaction.commit()
        transaction.isAddToBackStackAllowed
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("onNavigationItected", "clicked")
        when (item.itemId) {
            R.id.menu_primary -> {
                if (mailViewModel.curState != "Primary") {
                    mailViewModel.changeMailListSet("Primary")
                }
            }
            R.id.menu_social -> {
                if (mailViewModel.curState != "Social") {
                    mailViewModel.changeMailListSet("Social")
                }
            }
            R.id.menu_promotion -> {
                if (mailViewModel.curState != "Promotions") {
                    mailViewModel.changeMailListSet("Promotions")
                }
            }
        }
        Log.d("mail List ", mailViewModel.mailList.value.toString())
        binding.drawerLayout.closeDrawer(binding.navView)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        Log.d("cur scrren", currentSelectedView)
        if (currentSelectedView == "MAIL" && mailViewModel.curState == "Primary") {
            ActivityCompat.finishAffinity(this) //해당 앱의 루트 액티비티를 종료시킨다. (API  16미만은 ActivityCompat.finishAffinity())
        }
        if (currentSelectedView == "SETTING") {
            setFrag("MAIL")
            mailViewModel.curState = "Primary"
            if(widthdpi >= 600){
                binding.navRail!!.selectedItemId = R.id.bottomNav_showMail
            }
            else{
                binding.bottomNavBar!!.selectedItemId = R.id.bottomNav_showMail
            }
        } else if (currentSelectedView == "MAIL" && mailViewModel.curState != "Primary") {
            mailViewModel.changeMailListSet("Primary")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("currentSelectedView",currentSelectedView)
    }
}
