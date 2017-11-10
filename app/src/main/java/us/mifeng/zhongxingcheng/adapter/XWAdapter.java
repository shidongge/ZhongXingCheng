package us.mifeng.zhongxingcheng.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        MyViewHorder vh = null;
        if (vh==null){
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.xw_item,null);
            vh.neirong  = (TextView) convertView.findViewById(R.id.xw_item_neirong);
            vh.time = (TextView) convertView.findViewById(R.id.xw_item_time);
            convertView.setTag(vh);
        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
        vh.neirong.setText(list.get(position).getNeirong());
        vh.time.setText(list.get(position).getTime());
        return convertView;
    }
    class MyViewHorder{
        TextView neirong,time;
    }
}
