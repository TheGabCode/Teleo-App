package gab.cdi.teleo.main.adapters

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import gab.cdi.teleo.R
import gab.cdi.teleo.main.adapters.AdapterProgram.Companion.TRENDING
import gab.cdi.teleo.main.dummy.DummyData.Companion.programs
import gab.cdi.teleo.main.models.Program


class AdapterRegion(val regions : ArrayList<Program>, val context : Context?) : RecyclerView.Adapter<AdapterRegion.ViewHolder>() {



    override fun getItemCount(): Int {
        return regions.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View?

        view = LayoutInflater.from(context).inflate(R.layout.model_item_layout_program, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {


    }
}