package us.mifeng.zhongxingcheng.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.bean.LXRBean;


/**
 * Created by shido on 2017/10/27.
 */

public class LXRAdapter extends BaseAdapter {
    private Context context;
    private List<LXRBean> list;
    public LXRAdapter(Context context, List<LXRBean> list){
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
        ViewHorder vh ;
        if (convertView==null){
            vh = new ViewHorder();
            convertView = View.inflate(context, R.layout.lianxir_item,null);
            vh.mobile = (TextView) convertView.findViewById(R.id.lxr_item_mobile);
            vh.vipLevel = (TextView) convertView.findViewById(R.id.lxr_item_viplevel);
            convertView.setTag(vh);
        }else {
            vh = (ViewHorder) convertView.getTag();
        }
        vh.vipLevel.setText(list.get(position).getViplevel());
        vh.mobile.setText(list.get(position).getMobile());
        return convertView;
    }
    class ViewHorder{
        TextView vipLevel,mobile;
    }
}
