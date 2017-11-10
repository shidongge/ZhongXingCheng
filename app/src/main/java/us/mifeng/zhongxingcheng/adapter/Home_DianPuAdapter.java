package us.mifeng.zhongxingcheng.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.bean.Home_ShangPuBean;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/11/2.
 */

public class Home_DianPuAdapter extends BaseAdapter {
    private List<Home_ShangPuBean> list;
    private Context context;
    public  Home_DianPuAdapter(List<Home_ShangPuBean> list,Context context){
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHorder viewHorder = null;
        if (viewHorder==null){
            viewHorder = new MyViewHorder();
            convertView = View.inflate(context,R.layout.home_gv,null);
            viewHorder.shopname = (TextView) convertView.findViewById(R.id.home_gv_shopname);
            viewHorder.img = (ImageView) convertView.findViewById(R.id.home_gv_img);
            viewHorder.price = (TextView) convertView.findViewById(R.id.home_gv_price);
            convertView.setTag(viewHorder);
        }else {
            viewHorder = (MyViewHorder) convertView.getTag();
        }
        Glide.with(context).load(WangZhi.DIANPU+list.get(position).getImgTop()).into(viewHorder.img);
        viewHorder.price.setText(list.get(position).getImgIcon());
        viewHorder.shopname.setText(list.get(position).getShopName());
        return convertView;
    }
    class MyViewHorder{
        ImageView img;
        TextView price,shopname;
    }
}