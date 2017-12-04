package us.mifeng.zhongxingcheng.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.mifeng.zhongxingcheng.app.MyApplicaiton;

/**
 * Created by shido on 2017/12/1.
 */

public abstract class LJZBaseFragment extends Fragment {
    private boolean isViewPrepared;
    private boolean hasFetchData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initList();
        View v = initView();
        setOnclick();
        return v;
    }

    //初始化操作
    public abstract View initView();

    //查找控件的方法
    public View f(View v, int id) {
        return v.findViewById(id);
    }

    //初始化数据源的方法
    public abstract void initList();

    //设置监听的方法
    public abstract void setOnclick();

    //获取application的方法
    public MyApplicaiton getApplication() {
        return (MyApplicaiton) getActivity().getApplication();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared=true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewPrepared=false;
        hasFetchData=false;
    }
    //判断是否需要加载数据的方法
    private void DataPrepared(){
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if(getUserVisibleHint()&&!hasFetchData&&isViewPrepared){
            hasFetchData=true;
            Start();
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            DataPrepared();

        }else {

        }
    }

    protected abstract void Start();
}
