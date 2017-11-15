package us.mifeng.zhongxingcheng.liaotian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupCacheInfo;
import com.tencent.qcloud.presentation.event.GroupEvent;
import com.tencent.qcloud.presentation.presenter.GroupManagerPresenter;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import us.mifeng.zhongxingcheng.R;
import us.mifeng.zhongxingcheng.liaotian.adapter.ProfileSummaryAdapter;
import us.mifeng.zhongxingcheng.liaotian.model.GroupInfo;
import us.mifeng.zhongxingcheng.liaotian.model.GroupProfile;
import us.mifeng.zhongxingcheng.liaotian.model.ProfileSummary;


public class GroupListActivity extends Activity implements Observer {

    private ProfileSummaryAdapter adapter;
    private ListView listView;
    private String type;
    private List<ProfileSummary> list;
    private final int CREATE_GROUP_CODE = 100;
    private final int GROUP_MEM_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        type = getIntent().getStringExtra("type");
        setTitle();
        listView = (ListView) findViewById(R.id.list);
        ImageView back = (ImageView) findViewById(R.id.ql_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list = GroupInfo.getInstance().getGroupListByType(type);
        adapter = new ProfileSummaryAdapter(this, R.layout.item_profile_summary, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.get(position).onClick(GroupListActivity.this);
            }
        });
        ImageView iv_add = (ImageView) findViewById(R.id.groupListTitle);
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupListActivity.this, ChooseFriendActivity.class);
                startActivityForResult(intent, GROUP_MEM_CODE);
            }
        });
        GroupEvent.getInstance().addObserver(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (GROUP_MEM_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                GroupManagerPresenter.createGroup("<未知 1 >",
                        type,
                        data.getStringArrayListExtra("select"),
                        new TIMValueCallBack<String>() {
                            @Override
                            public void onError(int i, String s) {
                                if (i == 80001) {
                                    Toast.makeText(GroupListActivity.this, getString(R.string.create_group_fail_because_wording), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(GroupListActivity.this, getString(R.string.create_group_fail), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onSuccess(String s) {
                                Toast.makeText(GroupListActivity.this, getString(R.string.create_group_succeed), Toast.LENGTH_SHORT).show();
                                //finish();
                            }
                        }
                );
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GroupEvent.getInstance().deleteObserver(this);
    }

    private void setTitle() {
        /*TemplateTitle title = (TemplateTitle) findViewById(R.id.groupListTitle);
        title.setTitleText(GroupInfo.getTypeName(type));
        title.setMoreImgAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupListActivity.this, CreateGroupActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });*/
    }


    /**
     * This method is called if the specified {@code Observable} object's
     * {@code notifyObservers} method is called (because the {@code Observable}
     * object has been updated.
     *
     * @param observable the {@link Observable} object.
     * @param data       the data passed to {@link Observable#notifyObservers(Object)}.
     */
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof GroupEvent) {
            if (data instanceof GroupEvent.NotifyCmd) {
                GroupEvent.NotifyCmd cmd = (GroupEvent.NotifyCmd) data;
                switch (cmd.type) {
                    case DEL:
                        delGroup((String) cmd.data);
                        break;
                    case ADD:
                        addGroup((TIMGroupCacheInfo) cmd.data);
                        break;
                    case UPDATE:
                        updateGroup((TIMGroupCacheInfo) cmd.data);
                        break;
                }
            }
        }
    }

    private void delGroup(String groupId) {
        Iterator<ProfileSummary> it = list.iterator();
        while (it.hasNext()) {
            ProfileSummary item = it.next();
            if (item.getIdentify().equals(groupId)) {
                it.remove();
                adapter.notifyDataSetChanged();
                return;
            }
        }
    }


    private void addGroup(TIMGroupCacheInfo info) {
        if (info != null && info.getGroupInfo().getGroupType().equals(type)) {
            GroupProfile profile = new GroupProfile(info);
            list.add(profile);
            adapter.notifyDataSetChanged();
        }

    }

    private void updateGroup(TIMGroupCacheInfo info) {
        delGroup(info.getGroupInfo().getGroupId());
        addGroup(info);
    }
}
