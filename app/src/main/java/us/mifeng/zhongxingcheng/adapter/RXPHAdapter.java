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
import us.mifeng.zhongxingcheng.bean.RXPHBean;

/**
 * Created by shido on 2017/12/18.
 */

public class RXPHAdapter extends BaseAdapter {
    private List<RXPHBean.DataBean> list;
    private Context context;
    public RXPHAdapter(List<RXPHBean.DataBean> list,Context context){
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
            convertView = View.inflate(context, R.layout.item_rxph,null);
            vh.goodsMoney = (TextView) convertView.findViewById(R.id.rxph_goodsMoney);
            vh.baoyou = (TextView) convertView.findViewById(R.id.rxph_baoyou);
            vh.imgUrl = (ImageView) convertView.findViewById(R.id.rxph_img);
            vh.goodsName = (TextView) convertView.findViewById(R.id.rxph_goodsName);
            vh.sellCount = (TextView) convertView.findViewById(R.id.rxph_sellCount);
            vh.shortDesc = (TextView) convertView.findViewById(R.id.rxph_shortDesc);
            convertView.setTag(vh);
        }else {
            vh = (MyViewHorder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getImgUrl()).into(vh.imgUrl);
        vh.shortDesc.setText(list.get(position).getShortDesc());
        vh.goodsName.setText(list.get(position).getGoodsName());
        vh.sellCount.setText(list.get(position).getSellCount()+"人付款");
        vh.goodsMoney.setText(list.get(position).getGoodsMoney());
        String freight = list.get(position).getFreight();
        if ("0".equals(freight)){
            vh.baoyou.setVisibility(View.VISIBLE);
        }

        return convertView;
    }


    class MyViewHorder{
        TextView goodsName,shortDesc,goodsMoney,sellCount,baoyou;
        ImageView imgUrl;
    }
}
