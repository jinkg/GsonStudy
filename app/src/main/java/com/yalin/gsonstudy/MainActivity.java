package com.yalin.gsonstudy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list);
        mAdapter = new ItemAdapter(this);
        list.setAdapter(mAdapter);

        setData();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActionItem item = mAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, item.actionClass);
                startActivity(intent);
            }
        });
    }

    private void setData() {
        ActionItem[] actionArrays = new ActionItem[]{
                new ActionItem("BasicUsage", BasicUsageActivity.class),
                new ActionItem("NestedObjects", NestedObjectsActivity.class)
        };
        mAdapter.setActions(Arrays.asList(actionArrays));
    }

    private class ActionItem {
        String title;
        Class actionClass;

        ActionItem(String title, Class actionClass) {
            this.title = title;
            this.actionClass = actionClass;
        }
    }

    private class ItemAdapter extends BaseAdapter {
        private Context mContext;
        private List<ActionItem> mActions;

        ItemAdapter(Context context) {
            mContext = context;
        }

        void setActions(List<ActionItem> actionItems) {
            mActions = actionItems;
        }

        @Override
        public int getCount() {
            return mActions == null ? 0 : mActions.size();
        }

        @Override
        public ActionItem getItem(int position) {
            return mActions.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new TextView(mContext);
            }
            TextView button = (TextView) convertView;
            button.setBackgroundColor(Color.WHITE);
            button.setGravity(Gravity.CENTER);
            button.setPadding(20, 20, 20, 20);
            button.setText(getItem(position).title);
            return convertView;
        }
    }
}
