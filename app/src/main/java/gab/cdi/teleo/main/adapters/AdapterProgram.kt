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
import gab.cdi.teleo.main.models.Program


class AdapterProgram(val programs : ArrayList<Program>, val context : Context?, programPurpose: Int) : RecyclerView.Adapter<AdapterProgram.ViewHolder>() {
    var programPurposeAdapter = programPurpose

    companion object {
        val WHATS_ON : Int = 0
        val TRENDING : Int = 1
        val SHOWS : Int = 2
        val STREAM : Int = 3
    }


    override fun getItemCount(): Int {
        return programs.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View?

        view = LayoutInflater.from(context).inflate(R.layout.model_item_layout_program, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thisProgram = programs[position]
        when(programPurposeAdapter){
            TRENDING -> {
                holder.programNumber.text = thisProgram.programNumber
                holder.programNumber.visibility = View.VISIBLE

            }
//            SHOWS -> {
//                holder.programName.visibility = View.GONE
//                holder.programDescription.visibility = View.GONE
//                holder.itemView.setOnClickListener {
//                    val fragment = ShowDrilldown.newInstance(thisProgram.programId!!,"")
//                    if(context is AppCompatActivity){
//                        val ft = context.supportFragmentManager.beginTransaction()
//                        ft.replace(R.id.content_home,fragment)
//                        ft.commit()
//                    }
//                }
//            }
//            STREAM -> {
//                holder.streamPlayButton.visibility = View.VISIBLE
//                holder.streamPlayButton.setOnClickListener {
//                    if(context is Home){
//                        context.displaySelectedScreen(R.id.mandelei_tv_stream)
//                    }
//                }
//            }

        }
        holder.programName.text = thisProgram.programTitle
        holder.programDescription.text = thisProgram.programDescription
        holder.programNumber.text = thisProgram.programNumber
//        val thumbnailReference  = storageReference.reference.child("programs/${thisProgram.programThumbnailUrl}")
        Log.d("Tag ",thisProgram.programThumbnailUrl )
//        if(context != null){
//            GlideApp.with(context)
//                    .load(R.drawable.fragment_home_online)
//                    .into(holder.programThumbnail)
//        }
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var programName : TextView = view.findViewById(R.id.programTitle)
        var programDescription : TextView = view.findViewById(R.id.programDescription)
        var programThumbnail : ImageView = view.findViewById(R.id.programThumbnail)
        var programNumber : TextView = view.findViewById(R.id.programNumber)
        //var streamPlayButton : ImageButton = view.findViewById(R.id.streamPlayButton)

    }
}