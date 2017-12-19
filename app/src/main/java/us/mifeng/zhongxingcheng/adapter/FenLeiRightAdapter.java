package us.mifeng.zhongxingcheng.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.bean.FenLieBean;

/**
 * Created by shido on 2017/12/19.
 */

public class FenLeiRightAdapter extends BaseAdapter {
    private List<FenLieBean.DataBean.ChildListBean> list;
    private Context context;
    public FenLeiRightAdapter(Context context, List<FenLieBean.DataBean.ChildListBean> list){
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
            convertView = View.inflate(context, R.layout.item_fenleiright,null);
            vh.name = (TextView) convertView.findViewById(R.id.grid_right_mTv);
            vh.img = (ImageView) convertView.findViewById(R.id.grid_right_img);
            convertView.setTag(vh);
        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
        vh.img.setImageResource(R.mipmap.tx3);
        vh.name.setText(list.get(position).getName());
        return convertView;
    }
    class MyViewHorder{
        TextView name;
        ImageView img;
    }
}
