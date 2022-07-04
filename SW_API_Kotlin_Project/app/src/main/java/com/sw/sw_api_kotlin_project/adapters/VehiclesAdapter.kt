package com.sw.sw_api_kotlin_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.model.vehicles.Vehicles

class VehiclesAdapter(private val vehicleList: List<Vehicles>) :
    RecyclerView.Adapter<VehiclesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val vehicleName = view.findViewById<TextView>(R.id.vehicle_name_text)

        fun bind(vehicles: Vehicles) {
            vehicleName.text = vehicles.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_vehicles, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vehicleList[position])
    }

    override fun getItemCount() = vehicleList.size
}