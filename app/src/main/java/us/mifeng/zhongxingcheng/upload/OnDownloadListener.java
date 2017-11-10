package us.mifeng.zhongxingcheng.upload;

/**
 * Created by user on 2017/7/24.
 */

public interface OnDownloadListener {
    /**
     * 下载成功
     */
    void onDownloadSuccess();

    /**
     * @param progress 下载进度
     */
    void onDownloading(int progress);

    /**
     * 下载失败
     */
    void onDownloadFailed();
}
