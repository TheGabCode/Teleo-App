package gab.cdi.teleo.main.adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import gab.cdi.teleo.R
import gab.cdi.teleo.main.dummy.DummyData
import gab.cdi.teleo.main.models.Program
import gab.cdi.teleo.main.models.Region
import kotlinx.android.synthetic.main.model_item_layout_region.view.*


class AdapterRegion(val regions : ArrayList<Region>, val context : Context?) : RecyclerView.Adapter<AdapterRegion.ViewHolder>() {

    private lateinit var selectedRegionHashMap : HashMap<Int,Boolean>
    private lateinit var resetSelectedRegionHashMap : HashMap<Int, Boolean>

    override fun getItemCount(): Int {
        return regions.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.model_item_layout_region, parent, false)
        selectedRegionHashMap = HashMap()
        resetSelectedRegionHashMap = HashMap()
        for(i in 0..regions.size){
            selectedRegionHashMap.put(i,false)
            resetSelectedRegionHashMap.put(i,false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thisRegion = regions[position]
        //highlight selected region, populate recycler view, fetch countries, using selected item's regionId
        //ApiRequest.get()
        holder.itemView.setOnClickListener {
            if(selectedRegionHashMap[position] == true){ //remove highlight
                holder.itemView.setBackgroundColor(Color.parseColor("#00FF0000"))
                holder.regionName.setTextColor(Color.parseColor("#ffffff"))
                holder.regionCountryRecyclerView.visibility = View.GONE
                selectedRegionHashMap.put(position,false)
            }
            else{ //false, not yet selected, highlight it
                selectedRegionHashMap.putAll(resetSelectedRegionHashMap)
                for(i in 0..regions.size){
                    if(i != position){
                        
                    }
                }
                holder.itemView.setBackgroundColor(Color.parseColor("#C4C4C4"))
                holder.regionName.setTextColor(Color.parseColor("#000000"))
                holder.regionCountryRecyclerView.visibility = View.VISIBLE
                val regionId = thisRegion.regionId
                val regionCountries : ArrayList<Region.Country>? = DummyData.countries[regionId]
                holder.regionCountryRecyclerView.adapter = AdapterCountry(regionCountries!!,context)
                holder.regionCountryRecyclerView.layoutManager = LinearLayoutManager(context)
                Log.d("Tag",holder.regionCountryRecyclerView.adapter.itemCount.toString())
                selectedRegionHashMap.put(position,true)
            }
        }
        holder.regionName.text = thisRegion.regionName
    }

    private fun toggleHighlight(position : Int, holder : ViewHolder){
        val thisRegion = regions[position]
        if(selectedRegionHashMap[position] == false){ //put other items into false - remove highlight, and highlight just this
           selectedRegionHashMap.putAll(resetSelectedRegionHashMap) //put all false for removing highlight
            for(i in 0..regions.size){ //remove others highlight
                if(i != position){
                    holder.itemView.setBackgroundColor(Color.parseColor("#00FF0000"))
                    holder.regionName.setTextColor(Color.parseColor("#ffffff"))
                    holder.regionCountryRecyclerView.visibility = View.GONE
                }
            }
            holder.itemView.setBackgroundColor(Color.parseColor("#C4C4C4"))
            holder.regionName.setTextColor(Color.parseColor("#000000"))
            holder.regionCountryRecyclerView.visibility = View.VISIBLE
            val regionId = thisRegion.regionId
            val regionCountries : ArrayList<Region.Country>? = DummyData.countries[regionId]
            holder.regionCountryRecyclerView.adapter = AdapterCountry(regionCountries!!,context)
            holder.regionCountryRecyclerView.layoutManager = LinearLayoutManager(context)
            Log.d("Tag",holder.regionCountryRecyclerView.adapter.itemCount.toString())
            selectedRegionHashMap.put(position,true) //indicate it is highlighted
        }
        else{ //if highligted, just remove highlight
            holder.itemView.setBackgroundColor(Color.parseColor("#00FF0000"))
            holder.regionName.setTextColor(Color.parseColor("#ffffff"))
            holder.regionCountryRecyclerView.visibility = View.GONE
            selectedRegionHashMap.put(position,false)
        }
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var regionName =  view.regionName
        var regionCountryRecyclerView = view.regionCountriesRecyclerView
    }
}