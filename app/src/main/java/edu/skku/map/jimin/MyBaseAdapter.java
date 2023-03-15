package edu.skku.map.jimin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyBaseAdapter extends BaseAdapter {
    Context c;
    Bitmap items[];

    private int a;

    MyBaseAdapter(Context c,Bitmap arr[],int index){
        this.c=c;
        items=arr;
        a=index;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                LayoutInflater inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView=inflater.inflate(R.layout.grid_layout,null);
            }
            ImageView imageView= convertView.findViewById(R.id.imageView2);

            imageView.setImageBitmap(items[position]);
            if(position==a)imageView.setColorFilter(Color.WHITE);

            return convertView;
    }
}
