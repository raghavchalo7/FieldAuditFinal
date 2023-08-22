package com.chalo.fieldauditapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chalo.fieldauditapp.model.AuditReportRequestItem

class CustomAdapter(private val mList: List<AuditReportRequestItem>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views

    var onItemClick : ((AuditReportRequestItem)->Unit)?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_route_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemViewsModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemViewsModel.image)
        holder.routetextView.text=ItemViewsModel.tripNumber

        // sets the text to the textview from our itemHolder class
        holder.stoptextView.text = ItemViewsModel.auditEndBusStopId.toString()

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(ItemViewsModel)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val routetextView: TextView = itemView.findViewById(R.id.routeTV)
        val stoptextView: TextView = itemView.findViewById(R.id.stopTV)
    }
}