package com.example.mytask.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mytask.R
import com.example.mytask.data.models.RestCountriesModel
import com.example.mytask.databinding.RestCountryListItemBinding

class RestCountryAdapter(private val context: Context, private var list:ArrayList<RestCountriesModel>) : RecyclerView.Adapter<RestCountryAdapter.RestCountryViewHolder>() {
    private lateinit var adapteBinding: RestCountryListItemBinding

    inner class RestCountryViewHolder(val adapterItemBinding: RestCountryListItemBinding) :RecyclerView.ViewHolder(adapterItemBinding.root) {
        fun bindData(dataListItem: RestCountriesModel, holder: RestCountryViewHolder) {
            dataListItem.let {
                adapterItemBinding.countryDataReposItem = it
            }
            Glide.with(context).load(dataListItem.flags?.png.orEmpty()).placeholder(R.drawable.ic_launcher_foreground).into(adapterItemBinding.countryFlagAvatarIv)
        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<RestCountriesModel>() {
        override fun areItemsTheSame(oldItem: RestCountriesModel, newItem: RestCountriesModel): Boolean {
              return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: RestCountriesModel, newItem: RestCountriesModel): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestCountryAdapter.RestCountryViewHolder {
        adapteBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.rest_country_list_item,parent,false)
        return RestCountryViewHolder(adapteBinding)
    }

    override fun onBindViewHolder(holder: RestCountryViewHolder, position: Int) {
        //val dataListItem = list[holder.adapterPosition]
        val dataListItem = differ.currentList[holder.adapterPosition]
        holder.bindData(dataListItem,holder)


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun updateList(updateRepoList: List<RestCountriesModel>){
        this.list = updateRepoList as ArrayList<RestCountriesModel>
        differ.submitList(updateRepoList)
    }


    private var selectedItemListener : ((pos: Int) -> Unit)? = null
    fun selectedItemListenerOnClick(listener : (pos: Int) -> Unit){
        this.selectedItemListener = listener
    }
}