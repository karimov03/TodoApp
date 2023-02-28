package com.karimov03.todoapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.Toast
import com.karimov03.todoapp.databinding.ChildItemBinding
import com.karimov03.todoapp.databinding.ParentItemBinding

class ExampleAdapter (val otasi: List<String>, val map: HashMap<String,ArrayList<String>>):
    BaseExpandableListAdapter(){
    override fun getGroupCount(): Int {
        return otasi.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        try {
            return map[otasi[groupPosition]]!!.size
        }
        catch (e:Exception){
            return 0
        }
    }

    override fun getGroup(groupPosition: Int): Any {
        return otasi[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return otasi[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val otasibinding=
            ParentItemBinding.inflate(LayoutInflater.from(parent!!.context),parent,false)
        otasibinding.tvName.text=otasi[groupPosition]
        return otasibinding.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val bolasiBinding=
            ChildItemBinding.inflate(LayoutInflater.from(parent!!.context),parent,false)
        bolasiBinding.tvName.text=map[otasi[groupPosition]]!!.get(childPosition)
        return bolasiBinding.root

    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
