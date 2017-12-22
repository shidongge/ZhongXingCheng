package us.mifeng.zhongxingcheng.wpxq_ceshi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import us.mifeng.zhongxingcheng.R;

/**
 * 图文详情webview的Fragment
 */
public class GoodsInfoWebFragment extends Fragment {
    public WebView wv_detail;
    private WebSettings webSettings;
    private LayoutInflater inflater;
    private String id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        View rootView = inflater.inflate(R.layout.fragment_item_info_web, null);
        id = getActivity().getIntent().getStringExtra("id");
        initWebView(rootView);
        return rootView;
    }

    public void initWebView(View rootView) {
        String url = "http://192.168.1.123:1002/shop/appShopGoodsInfo?goodsId="+id;
        wv_detail = (WebView) rootView.findViewById(R.id.wv_detail);
        wv_detail.setFocusable(false);
        wv_detail.loadUrl(url);
        webSettings = wv_detail.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setBlockNetworkImage(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv_detail.setWebViewClient(new GoodsDetailWebViewClient());
    }

    private class GoodsDetailWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webSettings.setBlockNetworkImage(false);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }
    }
}
