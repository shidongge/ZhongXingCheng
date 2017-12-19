package us.mifeng.zhongxingcheng.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.bean.SXPPBean;
import us.mifeng.zhongxingcheng.utils.WangZhi;

/**
 * Created by shido on 2017/11/16.
 */

/**
 * 上新品牌的适配器
 */
public class SXPPAdapter extends BaseAdapter {
    private List<SXPPBean.DataBean> list;
    private List<SXPPBean.DataBean.GoodsInfoBean> goodsInfoList;
    private Context context;
    private static final String TAG = "SXPPAdapter";

    public SXPPAdapter(List<SXPPBean.DataBean.GoodsInfoBean> goodsInfoList, List<SXPPBean.DataBean> list, Context context) {
        this.list = list;
        this.goodsInfoList = goodsInfoList;
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHorder vh = null;
        if (vh == null) {
            vh = new MyViewHorder();
            convertView = View.inflate(context, R.layout.item_sxpp, null);
            vh.logo = (ImageView) convertView.findViewById(R.id.item_sxpp_logo);
            vh.img1 = (ImageView) convertView.findViewById(R.id.item_sxpp_img1);
            vh.img2 = (ImageView) convertView.findViewById(R.id.item_sxpp_img2);
            vh.img3 = (ImageView) convertView.findViewById(R.id.item_sxpp_img3);
            vh.name = (TextView) convertView.findViewById(R.id.item_sxpp_name);
            vh.number = (TextView) convertView.findViewById(R.id.item_sxpp_number);
            vh.jindian = (TextView) convertView.findViewById(R.id.item_sxpp_jindian);
            vh.audeType1 = (TextView) convertView.findViewById(R.id.item_sxpp_audeType1);
            vh.audeType2 = (TextView) convertView.findViewById(R.id.item_sxpp_audeType2);
            vh.audeType3 = (TextView) convertView.findViewById(R.id.item_sxpp_audeType3);
            vh.text1 = (TextView) convertView.findViewById(R.id.item_sxpp_text1);
            vh.text2 = (TextView) convertView.findViewById(R.id.item_sxpp_text2);
            vh.text3 = (TextView) convertView.findViewById(R.id.item_sxpp_text3);
            convertView.setTag(vh);
        } else {
            vh = (MyViewHorder) convertView.getTag();
        }
        String audeType = list.get(position).getAudeType();
        if ("1".equals(audeType)) {
            vh.audeType1.setText("诚信卖家");
        }
        if ("2".equals(audeType)) {
            vh.audeType2.setText("企业认证");
        }
        if ("3".equals(audeType)) {
            vh.audeType3.setText("金牌卖家");
        }
        vh.number.setText(list.get(position).getGoodsCount());
        vh.name.setText(list.get(position).getShopName());
        Glide.with(context).load(WangZhi.DIANPU+list.get(position).getImgIcon()).into(vh.logo);
        final List<SXPPBean.DataBean.GoodsInfoBean> goodsInfo = list.get(position).getGoodsInfo();
        if (goodsInfo != null) {
            for (int i = 0; i < goodsInfo.size(); i++) {
                final SXPPBean.DataBean.GoodsInfoBean goodsInfoBean = goodsInfo.get(i);
                if (i == 0) {
                    Glide.with(context).load(list.get(position).getGoodsInfo().get(0).getImgCart()).into(vh.img1);
                    vh.text1.setText(goodsInfoBean.getGoodsMoney());
                    vh.text1.setBackgroundColor(Color.parseColor("#DBDBDB"));
                    vh.img1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String id = goodsInfoBean.getId();
                            Log.e(TAG, "onClick: 1号"+id );
                        }
                    });
                }
                if (i == 1) {
                    Glide.with(context).load(goodsInfoBean.getImgCart()).into(vh.img2);
                    vh.text2.setText(goodsInfoBean.getGoodsMoney());
                    vh.text2.setBackgroundColor(Color.parseColor("#DBDBDB"));
                    vh.img2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String id = goodsInfoBean.getId();
                            Log.e(TAG, "onClick: 2号"+id );
                        }
                    });
                }
                if (i == 2) {
                    Glide.with(context).load(goodsInfoBean.getImgCart()).into(vh.img3);
                    vh.text3.setText(goodsInfoBean.getGoodsMoney());
                    vh.text3.setBackgroundColor(Color.parseColor("#DBDBDB"));
                    vh.img3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String id = goodsInfoBean.getId();
                            Log.e(TAG, "onClick: 3号"+id );
                        }
                    });
                }
            }
        }else {
            Log.e(TAG, "getView: "+goodsInfo );
        }
        vh.jindian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = list.get(position).getId();
                Log.e(TAG, "onClick: " + id);
            }
        });






        return convertView;
    }


    class MyViewHorder {
        ImageView logo, img1, img2, img3;
        TextView name, number, jindian, audeType1, audeType2, audeType3, text1, text2, text3;
    }
}
