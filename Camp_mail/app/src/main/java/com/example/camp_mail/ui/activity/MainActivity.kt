package com.example.camp_mail.ui.activity

import android.os.Bundle
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

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private var currentSelectedView = "MAIL"
    private lateinit var binding: ActivityMainBinding
    private lateinit var fmManager: FragmentManager
    private val userInfoViewModel: UserInfoViewModel by viewModels()
    private val mailViewModel: MailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu) // 홈버튼 이미지 변경
        // 네비게이션 드로어 생성

        // 네비게이션 드로어 내에있는 화면의 이벤트를 처리하기 위해 생성
        binding.navView.setNavigationItemSelectedListener(this)
        setFrag("MAIL") // default 화면
        initBottomNavigation()


        apply {
            userInfoViewModel.setUserNickName(intent.getStringExtra("userNickName").toString())
            userInfoViewModel.setUserEmail(intent.getStringExtra("userEmail").toString())
        }

    }
    private fun initBottomNavigation() {
        binding.bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomNav_showMail -> {
                    Log.d("botton Nav ", "mail selected")
                    currentSelectedView = "MAIL"
                    setFrag("MAIL")
                    true
                }
                R.id.bottomNav_showSetting -> {
                    Log.d("botton Nav ", "setting selected")
                    currentSelectedView = "SETTING"
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

            }
            "SETTING" -> {
                transaction.replace(binding.hostFrag.id, SettingFragment())
            }

        }
        transaction.commit()
        transaction.isAddToBackStackAllowed
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
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
        if (currentSelectedView == "MAIL" && mailViewModel.curState == "Primary") {
            ActivityCompat.finishAffinity(this) //해당 앱의 루트 액티비티를 종료시킨다. (API  16미만은 ActivityCompat.finishAffinity())
        }
        if (currentSelectedView == "SETTING") {
            setFrag("MAIL")
            mailViewModel.curState = "Primary"
            binding.bottomNavBar.selectedItemId = R.id.bottomNav_showMail
        } else if (currentSelectedView == "MAIL" && mailViewModel.curState != "Primary") {
            mailViewModel.changeMailListSet("Primary")
        }

    }

}
