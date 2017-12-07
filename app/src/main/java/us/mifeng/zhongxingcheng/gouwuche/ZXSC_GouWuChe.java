package us.mifeng.zhongxingcheng.gouwuche;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.base.BaseFragment;
import us.mifeng.zhongxingcheng.utils.DQBDAS;
import us.mifeng.zhongxingcheng.utils.ToSi;

/**
 * Created by shido on 2017/11/27.
 */

public class ZXSC_GouWuChe extends BaseFragment implements View.OnClickListener, OnCartListener {
    private static final boolean DEBUG = true;
    private ExpandableListView mCartExpandableListView;
    private TextView mAllCostView;
    private TextView mSettlementButton;
    private CheckBox mAllCheckView;
    private LinearLayout mLayoutBottom;
    private String fileName = "sas.json";
    private CartAdapter mCartAdapter;
    private ArrayList<CartEntity> mCartEntityList = new ArrayList<CartEntity>();
    private boolean isAllCheck;
    private float allCost;
    private static final String TAG = "ZXSC_GouWuCheFragment";
    private CartEntity cartEntity;
    private ArrayList<CartEntity> cartEntityList;

    @Override
    protected View initView() {
        View inflate = View.inflate(getActivity(), R.layout.fragment_zxsc_gouwuche, null);
        ImageView back = (ImageView) inflate.findViewById(R.id.zxsc_gouwuche_back);

        mAllCostView = (TextView) inflate.findViewById(R.id.all_cost);
        mSettlementButton = (TextView) inflate.findViewById(R.id.settlement);
        mCartExpandableListView = (ExpandableListView) inflate.findViewById(R.id.expandableListView);
        mAllCheckView = (CheckBox) inflate.findViewById(R.id.all_check);
        mLayoutBottom = (LinearLayout) inflate.findViewById(R.id.llayout_bottom);

        back.setOnClickListener(this);
        setupCart();
        mAllCheckView.setOnClickListener(this);
        mSettlementButton.setOnClickListener(this);
        return inflate;
    }

    private void setupCart() {
        mCartAdapter = new CartAdapter(getActivity(), mCartEntityList);
        mCartExpandableListView.setAdapter(mCartAdapter);
        mCartAdapter.setOnCartListener(this);

        mCartEntityList.addAll(getData());
        mCartAdapter.notifyDataSetChanged();
        for (int i = 0; i < mCartEntityList.size(); i++) {
            mCartExpandableListView.expandGroup(i);
        }
    }

    private ArrayList<CartEntity> getData() {
        String json = DQBDAS.getJson(getActivity(), fileName);
        try {
            JSONObject jsonObject = new JSONObject(json);
            int length = jsonObject.length();
            cartEntityList = new ArrayList<CartEntity>();
            for (int i = 0; i < length; i++) {


                cartEntity = new CartEntity();
                ArrayList<ProductEntity> productEntityList = new ArrayList<ProductEntity>();
                for (int j = 0; j < Math.random() * 2 + 1; j++) {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setProductPrice((float) (Math.random() * 100));
                    productEntity.setProductCount(1);
                    productEntityList.add(productEntity);
                }
                cartEntity.setProductEntityList(productEntityList);
                cartEntityList.add(cartEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartEntityList;
    }


    private void setCheckGroup(int groupPosition, boolean check) {
        int len = mCartEntityList.get(groupPosition).getProductEntityList().size();
        mCartEntityList.get(groupPosition).setGroupCheck(check);
        for (int i = 0; i < len; i++) {
            mCartEntityList.get(groupPosition).getProductEntityList().get(i).setChildCheck(check);
        }
    }

    private void setCheckChild(int groupPosition, int childPosition, boolean check) {
        mCartEntityList.get(groupPosition).getProductEntityList().get(childPosition).setChildCheck(check);

        int len = mCartEntityList.get(groupPosition).getProductEntityList().size();
        boolean groupCheck = true;
        for (int i = 0; i < len; i++) {
            if (!mCartEntityList.get(groupPosition).getProductEntityList().get(i).isChildCheck()) {
                groupCheck = false;
            }
        }
        mCartEntityList.get(groupPosition).setGroupCheck(groupCheck);
    }

    private void handleAllCost() {
        allCost = 0f;
        int allCount = 0;
        int groupLen = mCartEntityList.size();
        for (int i = 0; i < groupLen; i++) {
            int childLen = mCartEntityList.get(i).getProductEntityList().size();
            for (int j = 0; j < childLen; j++) {
                if (mCartEntityList.get(i).getProductEntityList().get(j).isChildCheck()) {
                    allCost += mCartEntityList.get(i).getProductEntityList().get(j).getProductPrice() * mCartEntityList.get(i).getProductEntityList().get(j).getProductCount();
                    allCount++;
                }
            }
        }
        mAllCostView.setText("￥" + String.format("%.2f", allCost));
        mSettlementButton.setText("结算(" + allCount + ")");
        if (mCartEntityList.isEmpty()) {
            mLayoutBottom.setVisibility(View.INVISIBLE);
        }
    }

    private void deleteProduct(int groupPosition, int childPosition) {
        mCartEntityList.get(groupPosition).getProductEntityList().remove(childPosition);
        if (mCartEntityList.get(groupPosition).getProductEntityList().isEmpty()) {
            mCartEntityList.remove(groupPosition);
        }
    }

    @Override
    protected void initList() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zxsc_gouwuche_back:
                getActivity().finish();
                break;
            case R.id.all_check:
                isAllCheck = !isAllCheck;
                setAllCheck(isAllCheck);
                break;
            case R.id.settlement:
                if (!verifyCart()) {
                    Toast.makeText(getActivity(), R.string.select_tip, Toast.LENGTH_SHORT).show();
                }else {
                    ToSi.show(getActivity(), String.format("%.2f", allCost));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void groupCheck(int groupPosition) {
        setCheckGroup(groupPosition, !mCartEntityList.get(groupPosition).isGroupCheck());
        handleAllCost();
        mCartAdapter.notifyDataSetChanged();
        updateAllState();
    }

    @Override
    public void groupClick() {
        mCartAdapter.notifyDataSetChanged();
    }

    @Override
    public void groupEdit(int groupPosition) {
        mCartEntityList.get(groupPosition).setGroupEdit(
                !mCartEntityList.get(groupPosition).isGroupEdit());
        mCartAdapter.notifyDataSetChanged();
    }

    @Override
    public void childCheck(int groupPosition, int childPosition) {
        setCheckChild(groupPosition, childPosition,
                !mCartEntityList.get(groupPosition).getProductEntityList().get(childPosition)
                        .isChildCheck());
        handleAllCost();
        mCartAdapter.notifyDataSetChanged();
        updateAllState();
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {
        deleteProduct(groupPosition, childPosition);
        handleAllCost();
        mCartAdapter.notifyDataSetChanged();
    }

    @Override
    public void childIncrease(int groupPosition, int childPosition) {
        mCartEntityList.get(groupPosition).getProductEntityList().get(childPosition).setProductCount
                (mCartEntityList.get(groupPosition).getProductEntityList().get(childPosition)
                        .getProductCount() + 1);
        mCartAdapter.notifyDataSetChanged();
    }

    @Override
    public void childReduce(int groupPosition, int childPosition) {
        if (mCartEntityList.get(groupPosition).getProductEntityList().get(childPosition).getProductCount() <= 1)
            return;
        mCartEntityList.get(groupPosition).getProductEntityList().get(childPosition).setProductCount
                (mCartEntityList.get(groupPosition).getProductEntityList().get(childPosition).getProductCount() - 1);
        mCartAdapter.notifyDataSetChanged();
    }

    @Override
    public void childClick() {
        mCartAdapter.notifyDataSetChanged();
    }

    @Override
    public void childEdit() {
        mCartAdapter.notifyDataSetChanged();
    }

    private void setAllCheck(boolean check) {
        int groupLen = mCartEntityList.size();
        for (int i = 0; i < groupLen; i++) {
            setCheckGroup(i, check);
        }
        handleAllCost();
        mCartAdapter.notifyDataSetChanged();
    }

    private boolean verifyCart() {
        int groupLen = mCartEntityList.size();
        for (int i = 0; i < groupLen; i++) {
            int childLen = mCartEntityList.get(i).getProductEntityList().size();
            for (int j = 0; j < childLen; j++) {
                if (mCartEntityList.get(i).getProductEntityList().get(j).isChildCheck()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void updateAllState() {
        int groupLen = mCartEntityList.size();
        isAllCheck = true;
        for (int i = 0; i < groupLen; i++) {
            if (!mCartEntityList.get(i).isGroupCheck()) {
                isAllCheck = false;
            }
        }
        mAllCheckView.setChecked(isAllCheck);
    }

}
