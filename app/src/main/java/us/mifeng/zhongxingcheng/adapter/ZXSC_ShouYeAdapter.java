package us.mifeng.zhongxingcheng.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.bean.Home_ShangPinBean;
import us.mifeng.zhongxingcheng.dianpu.DianPuActivity;
import us.mifeng.zhongxingcheng.utils.ToSi;
import us.mifeng.zhongxingcheng.wpxq_ceshi.WPXQ_CeSi;


/**
 * Created by shido on 2017/11/16.
 */

/**
 * 中星商城首页的listview的适配器
 */
public class ZXSC_ShouYeAdapter extends BaseAdapter  {
    private List<Home_ShangPinBean> list;
    private Context context;
    public ZXSC_ShouYeAdapter(List<Home_ShangPinBean> list, Context context){
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        if (list.size() !=0){
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
        MyViewHorder vh ;
        if (convertView==null){
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.item_zxsc_shouye,null);
            vh.img = (ImageView) convertView.findViewById(R.id.item_shouye_shang_img);
            vh.oneimg = (ImageView) convertView.findViewById(R.id.item_shouye_xia_yi_ll_img);
            vh.twoimg = (ImageView) convertView.findViewById(R.id.item_shouye_xia_er_ll_img);
            vh.threeimg = (ImageView) convertView.findViewById(R.id.item_shouye_xia_san_ll_img);
            vh.onetext = (TextView) convertView.findViewById(R.id.item_shouye_xia_yi_ll_text);
            vh.twotext = (TextView) convertView.findViewById(R.id.item_shouye_xia_er_ll_text);
            vh.threetext = (TextView) convertView.findViewById(R.id.item_shouye_xia_san_ll_text);
            vh.one = (LinearLayout) convertView.findViewById(R.id.item_shouye_xia_yi_ll);
            vh.two = (LinearLayout) convertView.findViewById(R.id.item_shouye_xia_er_ll);
            vh.three = (LinearLayout) convertView.findViewById(R.id.item_shouye_xia_san_ll);
            convertView.setTag(vh);

        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
//        Glide.with(context).load(WangZhi.DIANPU+list.get(position).getImgTop()).into(vh.img);
//        Glide.with(context).load(WangZhi.DIANPU+list.get(position).getImgTop()).into(vh.oneimg);
//        Glide.with(context).load(WangZhi.DIANPU+list.get(position).getImgTop()).into(vh.twoimg);
//        Glide.with(context).load(WangZhi.DIANPU+list.get(position).getImgTop()).into(vh.threeimg);
//        vh.onetext.setText(list.get(position).getDesc());
//        vh.twotext.setText(list.get(position).getDesc());
//        vh.threetext.setText(list.get(position).getDesc());
        vh.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToSi.show(context,"点击是一号");
            }
        });
        vh.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, WPXQ_CeSi.class));
                ToSi.show(context,"点击是二号");
            }
        });
        vh.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context, MainActivity.class));
                ToSi.show(context,"点击是三号");
            }
        });
        vh.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DianPuActivity.class));
//                context.startActivity(new Intent(context, MainActivity.class));
                ToSi.show(context,"点击是大的");
            }
        });
        return convertView;
    }



    class MyViewHorder{
        TextView onetext,twotext,threetext;
        ImageView img,oneimg,twoimg,threeimg;
        LinearLayout one,two,three;
    }
}
