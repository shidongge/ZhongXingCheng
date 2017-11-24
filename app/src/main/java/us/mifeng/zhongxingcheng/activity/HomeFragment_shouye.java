package us.mifeng.zhongxingcheng.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;


/**
 * Created by shido on 2017/6/29.
 */

public class HomeFragment_shouye extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item,container,false);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        Bundle args = getArguments();
        String title = args.getString("arg");
        textView.setText(title);
        return view;
    }
}
