package com.karimov03.todoapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.karimov03.todoapp.R
import com.karimov03.todoapp.databinding.SpinnerItemBinding

class SpinnerAdapter(val item:List<String>):BaseAdapter() {
    override fun getCount(): Int {
        return item.size
    }

    override fun getItem(position: Int): Any {
        return item[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val spinnerItemBinding=SpinnerItemBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
        spinnerItemBinding.tvSp.text=item[position]
        if (position==0){
            spinnerItemBinding.tvImage.setImageResource(R.drawable.ic_flag1)
        }
        if (position==1){
            spinnerItemBinding.tvImage.setImageResource(R.drawable.ic_flag2)
        }
        if (position==2){
            spinnerItemBinding.tvImage.setImageResource(R.drawable.ic_flag3)
        }
        if (position==3){
            spinnerItemBinding.tvImage.setImageResource(R.drawable.ic_flag4)
        }
        return spinnerItemBinding.root
    }
}