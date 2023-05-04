package com.example.theapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class CustomAdapter(var context: Context, var data:ArrayList<Product>):BaseAdapter() {
    private class ViewHolder(row:View?){
        var mTxtName:TextView
        var mTxtPhoneNumber:TextView
        var mTxtPrice:TextView
         var mTxtLocation:TextView
        var mImgPhoto:ImageView
        init {
            this.mTxtName = row?.findViewById(R.id.mTvName) as TextView
            this.mTxtPhoneNumber = row?.findViewById(R.id.mTvPhoneNumber) as TextView
            this.mTxtLocation = row?.findViewById(R.id.mTvLocation) as TextView
            this.mTxtPrice = row?.findViewById(R.id.mTvPrice) as TextView
            this.mImgPhoto = row?.findViewById(R.id.mImgPhoto) as ImageView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.item_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:Product = getItem(position) as Product
        viewHolder.mTxtName.text = item.productname
        viewHolder.mTxtPhoneNumber.text = item.phoneNumber
        viewHolder.mTxtLocation.text = item.productlocation
        viewHolder.mTxtPrice.text = item.productprice
        Glide.with(context).load(item.image).into(viewHolder.mImgPhoto)
        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}