package us.mifeng.zhongxingcheng.dianpu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/11/29.
 */

public class DianPuAdapter extends RecyclerView.Adapter<DianPuAdapter.MyViewHorder> implements View.OnClickListener {
    private Context context;
    private List<String> list;

    public DianPuAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    private OnItemClickListener mOnItemClickListener = null;


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener!=null){
            mOnItemClickListener.onItemClick(v,(int)v.getTag() );
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }



    @Override
    public MyViewHorder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item,null );
        view.setOnClickListener(this);
        return new MyViewHorder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHorder holder, int position) {
        holder.tv.setText(list.get(position));
        //判断是否设置了监听器
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHorder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHorder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_info);
        }
    }
}
