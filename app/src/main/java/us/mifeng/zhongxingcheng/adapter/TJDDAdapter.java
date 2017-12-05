package us.mifeng.zhongxingcheng.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.bean.Home_ShangPinBean;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/11/17.
 */

public class TJDDAdapter extends BaseAdapter {
    private Context context;
    private List<Home_ShangPinBean> list;
    private int integer = 1;
    private static final String TAG = "TJDDAdapter";
    private String s;


    public TJDDAdapter(List<Home_ShangPinBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list.size() != 0) {
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
        final MyViewHorder vh ;
        if (convertView == null) {
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.item_dingdan, null);
            vh.dianming = (TextView) convertView.findViewById(R.id.item_tjdd_dianpu);
            vh.jieshao = (TextView) convertView.findViewById(R.id.item_tjdd_jieshao);
            vh.jiage = (TextView) convertView.findViewById(R.id.item_tjdd_jiage);
            vh.yuanjia = (TextView) convertView.findViewById(R.id.item_tjdd_yuanjia);
            vh.number = (TextView) convertView.findViewById(R.id.item_tjdd_number);
            vh.shuliang = (TextView) convertView.findViewById(R.id.item_tjdd_shuliang);
            vh.zongjishuliang = (TextView) convertView.findViewById(R.id.item_tjdd_zongjishuliang);
            vh.zongjia = (TextView) convertView.findViewById(R.id.item_tjdd_zongjia);
            vh.logo = (ImageView) convertView.findViewById(R.id.item_tjdd_logo);
            vh.img = (ImageView) convertView.findViewById(R.id.item_tjdd_img);
            vh.jia = (Button) convertView.findViewById(R.id.item_tjdd_jia);
            vh.jian = (Button) convertView.findViewById(R.id.item_tjdd_jian);
            convertView.setTag(vh);
        } else {
            vh = (MyViewHorder) convertView.getTag();
        }

        vh.number.setText(integer + "");
        vh.shuliang.setText(integer + "");
        vh.zongjishuliang.setText(integer + "");
        String trim = vh.jiage.getText().toString().trim();
        final Integer i = Integer.valueOf(trim);
        vh.zongjia.setText(i*integer+"");
        vh.yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        vh.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integer = integer + 1;
                s = String.valueOf(integer);
                vh.shuliang.setText(integer + "");
                vh.zongjia.setText(i*integer + "");
                vh.zongjishuliang.setText(integer+"");
                vh.number.setText(integer+"");
            }
        });

        vh.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (integer == 1) {
                    ToSi.show(context, "不能再减了");
                } else if (integer > 1) {
                    integer = integer - 1;
                    s = String.valueOf(integer);
                    vh.shuliang.setText(integer + "");
                    vh.zongjia.setText(i*integer + "");
                    vh.number.setText(integer+"");
                    vh.zongjishuliang.setText(integer+"");
                    Log.e(TAG, "onClick: -----------" + integer);

                }
            }
        });

        return convertView;
    }

    class MyViewHorder {
        TextView dianming, jieshao, jiage, yuanjia, number, zongjishuliang, zongjia, shuliang;
        ImageView logo, img;
        Button jia, jian;
    }
}
