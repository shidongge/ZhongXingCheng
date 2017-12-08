package us.mifeng.zhongxingcheng.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import us.mifeng.zhongxingcheng.R;

/**
 * Created by shido on 2017/12/8.
 */

/**
 * 会员中心界面
 */
public class HYZX extends Activity implements View.OnClickListener {

    private Button kaitong;
    private AlertDialog dialog;
    private TextView yinian;
    private TextView yongjiu;
    private TextView feiyong;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyzx);
        initView();
    }

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.hyzx_back);
        kaitong = (Button) findViewById(R.id.hyzx_kaitong);
        kaitong.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hyzx_kaitong:
                View inflate = LayoutInflater.from(HYZX.this).inflate(R.layout.dialog_gmhy, null);
                feiyong = (TextView) inflate.findViewById(R.id.gmhy_feiyong);
                yinian = (TextView) inflate.findViewById(R.id.gmhy_yinian);
                yongjiu = (TextView) inflate.findViewById(R.id.gmhy_yongjiu);
                ImageView guanbi = (ImageView) inflate.findViewById(R.id.gmhy_guanbi);
                Button ljkt = (Button) inflate.findViewById(R.id.gmhy_ljkt);
                dialog = new AlertDialog.Builder(this).create();
                dialog.setView(inflate);
                dialog.show();
                WindowManager windowManager = getWindowManager();
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Window window = dialog.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.height = (int) (defaultDisplay.getHeight() * 0.49);
                attributes.width = defaultDisplay.getWidth();
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.anim_menu_bottombar);
                dialog.getWindow().setAttributes(attributes);
                guanbi.setOnClickListener(this);
                ljkt.setOnClickListener(this);
                yongjiu.setOnClickListener(this);
                yinian.setOnClickListener(this);
                break;

            case R.id.gmhy_guanbi:
                dialog.hide();
                break;
            case R.id.gmhy_yongjiu:
                yinian.setBackgroundColor(Color.parseColor("#ffffff"));
                feiyong.setText("1000元");
                yongjiu.setBackgroundDrawable(getResources().getDrawable(R.drawable.spzx_shuliang));
                break;
            case R.id.gmhy_yinian:
                feiyong.setText("130元");
                yongjiu.setBackgroundColor(Color.parseColor("#ffffff"));
                yinian.setBackgroundDrawable(getResources().getDrawable(R.drawable.spzx_shuliang));
                break;
            case R.id.gmhy_ljkt:
                View contentView = LayoutInflater.from(HYZX.this).inflate(R.layout.dialog_zhifu, null);
                LinearLayout wx = (LinearLayout) contentView.findViewById(R.id.zhifu_weixin);
                LinearLayout zhifubao = (LinearLayout) contentView.findViewById(R.id.zhifu_zhifubao);
                LinearLayout kuaiqian = (LinearLayout) contentView.findViewById(R.id.zhifu_kuaiqian);
                AlertDialog dialog2 = new AlertDialog.Builder(this).create();
                dialog2.setView(contentView);
                dialog2.show();
                WindowManager windowManager2 = getWindowManager();
                Display defaultDisplay2 = windowManager2.getDefaultDisplay();
                Window window2 = dialog2.getWindow();
                WindowManager.LayoutParams attributes2 = window2.getAttributes();
                attributes2.height = (int) (defaultDisplay2.getHeight() * 0.12);
                attributes2.width = defaultDisplay2.getWidth();
                window2.setGravity(Gravity.BOTTOM);
                window2.setWindowAnimations(R.style.anim_menu_bottombar);
                dialog2.getWindow().setAttributes(attributes2);
                wx.setOnClickListener(this);
                zhifubao.setOnClickListener(this);
                kuaiqian.setOnClickListener(this);
                break;
            case R.id.zhifu_kuaiqian:
                startActivity(new Intent(HYZX.this,ZhiFu.class));
                break;
            case R.id.hyzx_back:
                finish();
                break;
            default:
                break;
        }
    }
}
