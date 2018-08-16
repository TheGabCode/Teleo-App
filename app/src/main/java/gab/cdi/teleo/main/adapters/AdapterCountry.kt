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
import gab.cdi.teleo.main.dummy.DummyData
import gab.cdi.teleo.main.dummy.DummyData.Companion.regions
import gab.cdi.teleo.main.models.Region
import gab.cdi.teleo.main.models.Region.Country
import kotlinx.android.synthetic.main.model_item_layout_country.view.*
import kotlinx.android.synthetic.main.model_item_layout_region.view.*


class AdapterCountry(val countries : ArrayList<Region.Country>, val context : Context?) : RecyclerView.Adapter<AdapterCountry.ViewHolder>() {
    override fun getItemCount(): Int {
        return countries.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.model_item_layout_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thisCountry = countries[position]
        holder.itemView.setOnClickListener {
            //update user regions settings in db

        }

        holder.countryName.text = thisCountry.countryName
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val countryName = view.countryName
    }
}