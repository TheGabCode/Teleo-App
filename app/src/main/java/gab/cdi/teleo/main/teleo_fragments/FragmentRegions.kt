package gab.cdi.teleo.main.teleo_fragments


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.Toast

import gab.cdi.teleo.R
import gab.cdi.teleo.main.adapters.AdapterRegion
import gab.cdi.teleo.main.adapters.AdapterRegionTwo
import gab.cdi.teleo.main.dummy.DummyData
import kotlinx.android.synthetic.main.fragment_regions_expandable_listview_header.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentRegions.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentRegions.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentRegions : Fragment(), ExpandableListView.OnGroupExpandListener{


    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null
    private lateinit var regionExpandableListView : ExpandableListView
    private var lastExpandedPosition : Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_regions, container, false)
        initUI(view)
        //populateRegions()
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    fun initUI(view : View){
        DummyData.regions.clear()
        DummyData().initRegions()
        //regionRecyclerView = view.findViewById(R.id.regionRecyclerView)
        regionExpandableListView = view.findViewById(R.id.regionExpandableListView)
        regionExpandableListView.setAdapter(AdapterRegionTwo(activity,DummyData.regions,DummyData.countries))
        regionExpandableListView.setOnGroupClickListener { parent, v, groupPosition, id ->
            if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition){
                parent.collapseGroup(lastExpandedPosition)
            }
            lastExpandedPosition = groupPosition
            if(parent.isGroupExpanded(groupPosition)){ //remove highlight
                parent.collapseGroup(groupPosition)
                v.setBackgroundColor(Color.parseColor("#00FF0000"))
            }
            else if(!parent.isGroupExpanded(groupPosition)){ //highlight
                parent.expandGroup(groupPosition)
                v.setBackgroundColor(Color.parseColor("#C4C4C4"))
            }
            true
        }
    }

    override fun onGroupExpand(groupPosition: Int) {
        Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show()
        if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition){
            regionExpandableListView.collapseGroup(lastExpandedPosition)
        }
        lastExpandedPosition = groupPosition
    }

//    private fun populateRegions(){
//        val dummy = DummyData()
//        DummyData.regions.clear()
//        dummy.initRegions()
//        regionRecyclerView.layoutManager = LinearLayoutManager(activity)
//        regionRecyclerView.adapter = AdapterRegion(DummyData.regions,activity)
//        regionRecyclerView.isNestedScrollingEnabled = false
//
//    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentRegions.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): FragmentRegions {
            val fragment = FragmentRegions()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
