package gab.cdi.teleo.main.teleo_fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import gab.cdi.teleo.R
import gab.cdi.teleo.main.adapters.AdapterProgram
import gab.cdi.teleo.main.models.Program
import kotlinx.android.synthetic.main.fragment_whats_on.*
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentWhatsOn.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentWhatsOn.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentWhatsOn : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    private var programs : ArrayList<Program> = ArrayList()
    private var programRecyclerView : RecyclerView? = null
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

        val view =  inflater.inflate(R.layout.fragment_whats_on, container, false)
        initUI(view)
        populateProgramRecyclerView()
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    private fun initUI(view : View?){
        programRecyclerView = view?.findViewById(R.id.programsRecyclerView)
    }

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

    private fun populateProgramRecyclerView(){
        programs.clear()
        val jsonProgramOne  = JSONObject()
        val jsonProgramTwo  = JSONObject()
        val jsonProgramThree  = JSONObject()

        jsonProgramOne.put("programId","1")
        jsonProgramOne.put("programTitle","Gab's Elem Life")
        jsonProgramOne.put("programDescription","Joseph's quiet elementary school life in Don Bosco SXL.")
        jsonProgramOne.put("programNumber","3")

        jsonProgramTwo.put("programId","2")
        jsonProgramTwo.put("programTitle","Gab's HS Life")
        jsonProgramTwo.put("programDescription","Gab's crazy PGMNHS adventure with new found friends for life.")
        jsonProgramTwo.put("programNumber","2")

        jsonProgramThree.put("programId","3")
        jsonProgramThree.put("programTitle","Gab's Pre-Life Chapter")
        jsonProgramThree.put("programDescription","Gab's lesson-filled college life in UPLB")
        jsonProgramThree.put("programNumber","1")

        programs.add(Program(jsonProgramOne))
        programs.add(Program(jsonProgramTwo))
        programs.add(Program(jsonProgramThree))

        programRecyclerView?.layoutManager = LinearLayoutManager(activity)
        programRecyclerView?.adapter = AdapterProgram(programs,activity,AdapterProgram.WHATS_ON)
        programRecyclerView?.isNestedScrollingEnabled = false



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
         * @return A new instance of fragment FragmentWhatsOn.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): FragmentWhatsOn {
            val fragment = FragmentWhatsOn()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
