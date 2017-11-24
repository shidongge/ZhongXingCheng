package us.mifeng.zhongxingcheng.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/17.
 */

public class YouHuiQuanAdapter extends RecyclerView.Adapter<YouHuiQuanAdapter.MyViewHorder> {
    private List<String> list;
    private Context context;
    public YouHuiQuanAdapter(List<String> list,Context context){
        this.context=context;
        this.list=list;
    }

    @Override
    public MyViewHorder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_youhuiquan, null);
        return new MyViewHorder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHorder holder, int position) {
        holder.jiazhi.setText(list.get(position));
        holder.manjian.setText(list.get(position));
        holder.riqi.setText(list.get(position));
        holder.shiyong.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHorder extends RecyclerView.ViewHolder{
        private TextView jiazhi,manjian,riqi,shiyong;
        public MyViewHorder(View itemView) {
            super(itemView);
            jiazhi = (TextView) itemView.findViewById(R.id.item_youhuiquan_jiazhi);
            manjian = (TextView) itemView.findViewById(R.id.item_youhuiquan_manjian);
            riqi = (TextView) itemView.findViewById(R.id.item_youhuiquan_riqi);
            shiyong = (TextView) itemView.findViewById(R.id.item_youhuiquan_shiyong);
        }
    }
}
