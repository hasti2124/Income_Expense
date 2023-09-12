package com.example.incomeexpense.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.incomeexpense.ModelClass.TransModel
import com.example.incomeexpense.R
import com.example.incomeexpense.databinding.ProfileItemBinding

class TransListAdapter(update: (TransModel) -> Unit,delete : (Int)->Unit) : RecyclerView.Adapter<TransListAdapter.TransListHolder>() {

    var update = update
    var delete = delete
    var transList = ArrayList<TransModel>()
    lateinit var context : Context
    class TransListHolder(itemView: ProfileItemBinding) : ViewHolder(itemView.root){
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransListHolder {
        context = parent.context
        var binding = ProfileItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return TransListHolder(binding)
    }

    override fun getItemCount(): Int {
        return transList.size
    }

    override fun onBindViewHolder(holder: TransListHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.apply {
            transList.get(position).apply {
                txtAmount.text = amount.toString()
                txtCategory.text = category
                txtNote.text = note

                if(isExpense == 0){
                    txtAmount.setTextColor(Color.GREEN)
                    roundBack.setImageResource(R.drawable.income1)
                    round.setImageResource(R.drawable.shape5)
                }else{
                    round.setImageResource(R.drawable.shape6)
                    roundBack.setImageResource(R.drawable.expense)
                    txtAmount.setTextColor(Color.RED)
                }
            }
        }
        holder.itemView.setOnLongClickListener(object  : OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {

                var popupMenu = PopupMenu(context,holder.itemView)
                popupMenu.menuInflater.inflate(R.menu.trans_item,popupMenu.menu)

                popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
                    override fun onMenuItemClick(p0: MenuItem?): Boolean {

                        if (p0?.itemId == R.id.update){
                            update.invoke(transList.get(position))
                        }

                        if (p0?.itemId == R.id.delete){
                            delete.invoke(transList.get(position).id)
                        }

                        return true
                    }

                })

                popupMenu.show()

                return false
            }

        })
    }

    fun setTrans(transList: ArrayList<TransModel>) {

        this.transList = transList
    }

    fun updateData(trans: ArrayList<TransModel>) {
        transList = trans
        notifyDataSetChanged()
    }
}