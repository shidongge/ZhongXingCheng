package us.mifeng.zhongxingcheng.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.bean.XWBean;

/**
 * Created by shido on 2017/11/3.
 */

public class XWAdapter extends BaseAdapter {
    private Context context;
    private List<XWBean> list;
    public XWAdapter(Context context,List<XWBean> list){
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
        vh.neirong.setText(list.get(position).getNeirong());

        return convertView;
    }
    class MyViewHorder{
        TextView neirong,guanfang,gonggao,yuedu;
        ImageView img;
    }
}
