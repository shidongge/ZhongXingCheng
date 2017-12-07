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
import us.mifeng.zhongxingcheng.bean.XWBean;

/**
 * Created by shido on 2017/11/3.
 */

public class XWAdapter extends BaseAdapter {
    private Context context;
    private List<XWBean.DataBean
            .InfoBean> list;
    public XWAdapter(Context context,List<XWBean.DataBean.InfoBean> list){
        this.context=context;
        this.list=list;
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
        MyViewHorder vh ;
        if (convertView==null){
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.xw_item,null);
            vh.neirong  = (TextView) convertView.findViewById(R.id.item_faxian_neirong);
            vh.gonggao = (TextView) convertView.findViewById(R.id.item_faxian_gonggao);
            vh.yuedu = (TextView) convertView.findViewById(R.id.item_faxian_yuedu);
            vh.guanfang = (TextView) convertView.findViewById(R.id.item_faxian_guanfang);
            vh.img = (ImageView) convertView.findViewById(R.id.item_faxian_img);

            convertView.setTag(vh);
        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getThumb()).into(vh.img);
        vh.gonggao.setText(list.get(position).getTitle());
        vh.yuedu.setText(list.get(position).getUpdateTime());
        vh.guanfang.setText(list.get(position).getCommentNum());
        vh.neirong.setText(list.get(position).getTitle());
        return convertView;
    }
    class MyViewHorder{
        TextView neirong,guanfang,gonggao,yuedu;
        ImageView img;
    }
}
