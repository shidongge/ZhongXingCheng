package us.mifeng.zhongxingcheng.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.activity.DianPu;
import us.mifeng.zhongxingcheng.bean.Home_ShangPinBean;

/**
 * Created by shido on 2017/11/16.
 */

/**
 * 上新品牌的适配器
 */
public class SXPPAdapter extends BaseAdapter implements View.OnClickListener {
    private List<Home_ShangPinBean> list;
    private Context context;
    public SXPPAdapter(List<Home_ShangPinBean> list, Context context){
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        if (list.size()!=0){
            return list.size();
        }
        return 0;
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
        MyViewHorder vh =null;
        if (vh==null){
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.item_sxpp,null);
            vh.logo = (ImageView) convertView.findViewById(R.id.item_sxpp_logo);
            vh.img1 = (ImageView) convertView.findViewById(R.id.item_sxpp_img1);
            vh.img2 = (ImageView) convertView.findViewById(R.id.item_sxpp_img2);
            vh.img3 = (ImageView) convertView.findViewById(R.id.item_sxpp_img3);
            vh.name = (TextView) convertView.findViewById(R.id.item_sxpp_name);
            vh.number = (TextView) convertView.findViewById(R.id.item_sxpp_number);
            vh.jindian = (TextView) convertView.findViewById(R.id.item_sxpp_jindian);
            convertView.setTag(vh);
        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
//        Glide.with(context).load(WangZhi.DIANPU+list.get(position).getImgTop()).into(vh.logo);
//        Glide.with(context).load(WangZhi.DIANPU+list.get(position).getImgTop()).into(vh.img1);
//        Glide.with(context).load(WangZhi.DIANPU+list.get(position).getImgTop()).into(vh.img2);
//        Glide.with(context).load(WangZhi.DIANPU+list.get(position).getImgTop()).into(vh.img3);
//        vh.number.setText(list.get(position).getDesc());
//        vh.name.setText(list.get(position).getDesc());

        vh.jindian.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_sxpp_jindian:
                context.startActivity(new Intent(context, DianPu.class));
                break;
        }
    }

    class MyViewHorder{
        ImageView logo,img1,img2,img3;
        TextView name,number,jindian;
    }
}
