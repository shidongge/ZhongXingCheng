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
import us.mifeng.zhongxingcheng.bean.GWGGBean;

/**
 * Created by shido on 2017/11/3.
 */

public class GWGGAdapter extends BaseAdapter {
    private Context context;
    private List<GWGGBean.DataBean> list;
    public GWGGAdapter(Context context, List<GWGGBean.DataBean> list){
        this.context=context;
        this.list=list;
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
        MyViewHorder vh ;
        if (convertView==null){
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.item_gwgg,null);
            vh.neirong  = (TextView) convertView.findViewById(R.id.item_gwgg_neirong);
            vh.gonggao = (TextView) convertView.findViewById(R.id.item_gwgg_gonggao);
            vh.yuedu = (TextView) convertView.findViewById(R.id.item_gwgg_yuedu);
            vh.guanfang = (TextView) convertView.findViewById(R.id.item_gwgg_guanfang);
            vh.img = (ImageView) convertView.findViewById(R.id.item_gwgg_img);

            convertView.setTag(vh);
        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
        String thumbnail = list.get(position).getThumbnail();
        if ("".equals(thumbnail)){
            vh.img.setImageResource(R.mipmap.tx3);
        }else {

            Glide.with(context).load(list.get(position).getThumbnail()).into(vh.img);
        }
        vh.gonggao.setText(list.get(position).getTitle());
        vh.yuedu.setText(list.get(position).getRead()+"人阅读");
        vh.guanfang.setText(list.get(position).getPublisher());
        vh.neirong.setText(list.get(position).getTitle());
        return convertView;
    }
    class MyViewHorder{
        TextView neirong,guanfang,gonggao,yuedu;
        ImageView img;
    }
}
