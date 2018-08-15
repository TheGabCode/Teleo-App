package gab.cdi.teleo.main.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.android.volley.VolleyError
import gab.cdi.teleo.R
import gab.cdi.teleo.main.https.API
import gab.cdi.teleo.main.https.ApiRequest
import gab.cdi.teleo.main.session.Session
import gab.cdi.teleo.main.teleo_fragments.FragmentHome
import gab.cdi.teleo.main.teleo_fragments.FragmentTrendingNow
import gab.cdi.teleo.main.teleo_fragments.FragmentWhatsOn
import gab.cdi.teloe.main.teleo_fragments.FragmentTeleo
import kotlinx.android.synthetic.main.activity_home_teleo_navigation.*
import kotlinx.android.synthetic.main.activity_home_app_bar_teleo_navigation.*

class TeleoNavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, FragmentTeleo {
    private lateinit var mSession : Session
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_teleo_navigation)
        setSupportActionBar(toolbar)
        mSession = Session(this)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        displaySelectedView(R.id.nav_home)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.teleo_navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onClick(v: View?) {
        val id = v?.id
        displaySelectedView(id)
    }

    fun displaySelectedView(id : Int?){
        var fragment : Fragment? = null
        var tag : String? = null
        var mFragment = supportFragmentManager.findFragmentById(R.id.teleoNavigationContent)
        when(id){
            R.id.logout -> {
                logout()
            }

            R.id.nav_home -> {
                if(mFragment is FragmentHome){
                    drawer_layout.closeDrawer(GravityCompat.START)
                    return
                }
                fragment = FragmentHome()
                tag = "fragmentHome"
            }

            R.id.nav_whats_on -> {
                if(mFragment is FragmentWhatsOn){
                    drawer_layout.closeDrawer(GravityCompat.START)
                    return
                }
                fragment = FragmentWhatsOn()
                tag = "fragmentWhatsOn"
            }

            R.id.nav_trending -> {
                if(mFragment is FragmentTrendingNow){
                    drawer_layout.closeDrawer(GravityCompat.START)
                    return
                }
                fragment = FragmentTrendingNow()
                tag = "fragmentTrendingNow"
            }
        }

        if(fragment != null){
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.teleoNavigationContent,fragment,tag)
            ft.commit()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun logout(){
        val header = HashMap<String,String>()
        val params = HashMap<String,String>()
        header.put("x-access-token",mSession.token()!!)
        Log.d("Tag",mSession.token())
        mSession.deauthorize()
        val signInInent = Intent(this@TeleoNavigationActivity,Registration::class.java)
        startActivity(signInInent)
        this@TeleoNavigationActivity.finish()
//        ApiRequest.post(this, API.SIGN_OUT,header,params,
//                object : ApiRequest.URLCallback{
//                    override fun didURLResponse(response: String) {
//                        Log.d("ResponseLOGOUT ",response)
//                        this@TeleoNavigationActivity.finish()
//                        val signInInent = Intent(this@TeleoNavigationActivity,Registration::class.java)
//                        startActivity(signInInent)
//                    }
//
//                },
//                object : ApiRequest.ErrorCallback{
//                    override fun didURLError(error: VolleyError) {
//
//                    }
//
//                })

    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
