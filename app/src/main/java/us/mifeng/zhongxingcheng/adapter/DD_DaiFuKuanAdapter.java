package us.mifeng.zhongxingcheng.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/11/27.
 */

public class DD_DaiFuKuanAdapter extends BaseAdapter implements View.OnClickListener {
    private List<String> list;
    private Context context;
    public DD_DaiFuKuanAdapter(List<String> list, Context context){
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
        MyViewHorder vh;
        if (convertView==null){
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.item_daifukuan,null);
            vh.name = (TextView) convertView.findViewById(R.id.item_dfk_name);
            vh.wupin = (TextView) convertView.findViewById(R.id.item_dfk_wupin);
            vh.jieshao = (TextView) convertView.findViewById(R.id.item_dfk_jieshao);
            vh.jiage = (TextView) convertView.findViewById(R.id.item_dfk_jiage);
            vh.zongjia = (TextView) convertView.findViewById(R.id.item_dfk_zongjia);
            vh.zongliang = (TextView) convertView.findViewById(R.id.item_dfk_zongliang);
            vh.shuliang = (TextView) convertView.findViewById(R.id.item_dfk_shuliang);
            vh.queren = (TextView) convertView.findViewById(R.id.item_dfk_queren);
            vh.quxiao = (TextView) convertView.findViewById(R.id.item_dfk_quxiao);
            convertView.setTag(vh);
        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
        vh.queren.setOnClickListener(this);
        vh.quxiao.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_dfk_queren:
                ToSi.show(context,"点击的是确认收货");
                break;
            case R.id.item_dfk_quxiao:
                ToSi.show(context,"点击的是取消");
                break;
        }
    }

    class MyViewHorder{
        private TextView name,wupin,jieshao,jiage,shuliang,zongliang,zongjia,quxiao,queren;

    }
}
