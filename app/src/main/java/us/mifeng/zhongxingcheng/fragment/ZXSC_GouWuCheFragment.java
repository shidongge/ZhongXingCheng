package us.mifeng.zhongxingcheng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.adapter.ShoppingCartAdapter;
import us.mifeng.zhongxingcheng.bean.ShoppingCartBean;
import us.mifeng.zhongxingcheng.utils.ToSi;


/**
 * Created by shido on 2017/11/27.
 */

public class ZXSC_GouWuCheFragment extends Fragment implements View.OnClickListener, ShoppingCartAdapter.CheckInterface, ShoppingCartAdapter.ModifyCountInterface {
    private static final String TAG = "ZXSC_GouWuCheFragment";
    private boolean isAllCheck;
    ListView list_shopping_cart;
    private List<ShoppingCartBean> list = new ArrayList<>();
    private ShoppingCartAdapter shoppingCartAdapter;
    Button btnBack;
    //全选
    CheckBox ckAll;
    //总额
    TextView tvShowPrice;
    //结算
    TextView tvSettlement;
    //编辑
    TextView btnEdit;//tv_edit
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private boolean mSelect;
    private boolean flag = false;
    private DecimalFormat df;
    private LinearLayout xiayou, xiawu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_zxsc_gouwuchefragment, null);
        xiayou = (LinearLayout) inflate.findViewById(R.id.gwc_xiayou);
        xiawu = (LinearLayout) inflate.findViewById(R.id.gwc_xiawu);
        btnEdit = (TextView) inflate.findViewById(R.id.bt_header_right);
//        if (list.size()==0){
//            xiawu.setVisibility(View.VISIBLE);
//            xiayou.setVisibility(View.GONE);
//            btnEdit.setVisibility(View.GONE);
//        }else {
//            xiayou.setVisibility(View.VISIBLE);
//            xiawu.setVisibility(View.GONE);
//            btnEdit.setVisibility(View.VISIBLE);
//        }

        btnBack = (Button) inflate.findViewById(R.id.btn_back);
        ckAll = (CheckBox) inflate.findViewById(R.id.ck_all);
        tvShowPrice = (TextView) inflate.findViewById(R.id.tv_show_price);
        tvSettlement = (TextView) inflate.findViewById(R.id.tv_settlement);

        list_shopping_cart = (ListView) inflate.findViewById(R.id.list_shopping_cart);
        list_shopping_cart.setClickable(false);
        ImageView back = (ImageView) inflate.findViewById(R.id.zxsc_gouwuche_back);
        ckAll.setOnClickListener(this);
        back.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        tvSettlement.setOnClickListener(this);
        initList();
        df = new DecimalFormat("#.00");
        return inflate;
    }

    protected void initList() {
        for (int i = 0; i < 2; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
            shoppingCartBean.setShoppingName("上档次的T桖");
            shoppingCartBean.setDressSize(20);
            shoppingCartBean.setId(i);
            shoppingCartBean.setPrice(30.6);
            shoppingCartBean.setCount(1);
            shoppingCartBean.setImageUrl("https://img.alicdn.com/bao/uploaded/i2/TB1YfERKVXXXXanaFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg");
            list.add(shoppingCartBean);
        }
        for (int i = 0; i < 2; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
            shoppingCartBean.setShoppingName("瑞士正品夜光男女士手表情侣精钢带男表防水石英学生非天王星机械");
            shoppingCartBean.setAttribute("黑白色");
            shoppingCartBean.setPrice(89);
            shoppingCartBean.setId(i + 2);
            shoppingCartBean.setCount(3);
            shoppingCartBean.setImageUrl("https://gd1.alicdn.com/imgextra/i1/2160089910/TB2M_NSbB0kpuFjSsppXXcGTXXa_!!2160089910.jpg");
            list.add(shoppingCartBean);
        }
        shoppingCartAdapter = new ShoppingCartAdapter(getActivity(), list);
        shoppingCartAdapter.setCheckInterface(this);
        shoppingCartAdapter.setModifyCountInterface(this);
        if (shoppingCartAdapter == null) {
            shoppingCartAdapter = new ShoppingCartAdapter(getActivity(), list);
        }
        list_shopping_cart.setAdapter(shoppingCartAdapter);
        shoppingCartAdapter.setShoppingCartBeanList(list);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全选按钮
            case R.id.ck_all:
                if (list.size() != 0) {
                    if (ckAll.isChecked()) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setChoosed(true);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setChoosed(false);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
                break;
            case R.id.bt_header_right:
                flag = !flag;
                if (flag) {
                    btnEdit.setText("完成");
                    shoppingCartAdapter.isShow(false);
                } else {
                    btnEdit.setText("编辑");
                    shoppingCartAdapter.isShow(true);
                }
                break;
            case R.id.tv_settlement: //结算
                lementOnder();
                break;


            case R.id.zxsc_gouwuche_back:
                getActivity().finish();
                break;
            default:
                break;
        }
    }


    /**
     * 结算订单、支付
     */
    private void lementOnder() {
        //选中的需要提交的商品清单
        for (ShoppingCartBean bean : list) {
            boolean choosed = bean.isChoosed();
            if (choosed) {
                String shoppingName = bean.getShoppingName();
                int count = bean.getCount();
                double price = bean.getPrice();
                int size = bean.getDressSize();
                String attribute = bean.getAttribute();
                int id = bean.getId();
            }
        }

        ToSi.show(getActivity(), "总价：" + totalPrice);

        //跳转到支付界面
    }


    @Override
    public void checkGroup(int position, boolean isChecked) {
        list.get(position).setChoosed(isChecked);
        if (isAllCheck())
            ckAll.setChecked(true);
        else
            ckAll.setChecked(false);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {

        for (ShoppingCartBean group : list) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < list.size(); i++) {
            ShoppingCartBean shoppingCartBean = list.get(i);
            if (shoppingCartBean.isChoosed()) {
                totalCount++;
                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
            }
        }
        tvShowPrice.setText("合计:" + df.format(totalPrice));
        tvSettlement.setText("结算(" + df.format(totalPrice) + ")");
    }

    /**
     * 增加
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = list.get(position);
        int currentCount = shoppingCartBean.getCount();
        currentCount++;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 删减
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = list.get(position);
        int currentCount = shoppingCartBean.getCount();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 删除
     *
     * @param position
     */
    @Override
    public void childDelete(int position) {
        list.remove(position);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }
}
