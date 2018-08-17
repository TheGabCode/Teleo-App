package gab.cdi.teleo.main.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import gab.cdi.teleo.R
import gab.cdi.teleo.main.models.Region

/**
 * Created by Default on 17/08/2018.
 */
class AdapterRegionTwo() : BaseExpandableListAdapter() {
    private lateinit var regionsArrayList : ArrayList<Region> //extract region name string from Region objects
    private lateinit var countriesListHashMap : HashMap<String,ArrayList<Region.Country>>//map regionId to ArrayList of countries in that region, then extract country name string from each Region.Country object
    private var context : Context? = null
    private var selectHashMap : HashMap<Int,Boolean> = HashMap()
    private var resetSelectHashMap : HashMap<Int,Boolean> = HashMap()
    constructor(context: Context?, regionsArrayList: ArrayList<Region>, countriesListHashMap: HashMap<String, ArrayList<Region.Country>>) : this() {
        this.regionsArrayList = regionsArrayList
        this.context = context
        this.countriesListHashMap = countriesListHashMap
        for(i in 0..regionsArrayList.size){
            selectHashMap.put(i,false)

        }
    }
    override fun getGroup(groupPosition: Int): Region {
        return regionsArrayList[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val headerTitle : String = this.getGroup(groupPosition).regionName
        val layoutInflater : LayoutInflater = this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.fragment_regions_expandable_listview_header,null)
        val regionNameTextView : TextView = view.findViewById(R.id.headerTextView)
        regionNameTextView.text = headerTitle

        if(isExpanded){
            view.setBackgroundColor(Color.parseColor("#C4C4C4")) //highlight
            regionNameTextView.setTextColor(Color.parseColor("#000000"))
            resetSelectHashMap.putAll(resetSelectHashMap)
            selectHashMap.put(groupPosition,true)
        }
        else{
            view.setBackgroundColor(Color.parseColor("#00FF0000")) //remove highlight
            regionNameTextView.setTextColor(Color.parseColor("#ffffff"))
            selectHashMap.put(groupPosition,false)
        }
        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
       return countriesListHashMap[regionsArrayList[groupPosition].regionId]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Region.Country {
        return countriesListHashMap[regionsArrayList[groupPosition].regionId]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val itemTitle : String = this.getChild(groupPosition,childPosition).countryName
        val layoutInflater : LayoutInflater = this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.fragment_regions_expnadable_listview_item,null)
        val countryNameTextView : TextView = view.findViewById(R.id.itemTextView)
        countryNameTextView.text = itemTitle
        if(selectHashMap[groupPosition] == true){ //highlight
            countryNameTextView.setTextColor(Color.parseColor("#000000"))
            view.setBackgroundColor(Color.parseColor("#C4C4C4"))
        }
        else{ //highlightn't
            view.setBackgroundColor(Color.parseColor("#00FF0000")) //remove highlight
            countryNameTextView.setTextColor(Color.parseColor("#ffffff"))
        }
        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return regionsArrayList.size
    }

}