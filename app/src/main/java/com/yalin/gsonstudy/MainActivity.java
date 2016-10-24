package com.yalin.gsonstudy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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
                new ActionItem("1.BasicUsage", BasicUsageActivity.class),
                new ActionItem("2.NestedObjects", NestedObjectsActivity.class),
                new ActionItem("3.MappingArrayAndList", ArraysAndListActivity.class),
                new ActionItem("4.MappingOfMap", MappingOfMapActivity.class),
                new ActionItem("5.MappingOfSets", MappingOfSetsActivity.class),
                new ActionItem("6.MappingOfNullValue", MappingOfNullValueActivity.class),
                new ActionItem("7.IgnoreFiled", IgnoreFieldActivity.class),
                new ActionItem("8.SerializedNameAnnotation", SerializedNameAnnotationActivity.class),
                new ActionItem("9.GsonBuilderNamingPolicies", GsonBuilderNamingPoliciesActivity.class),
                new ActionItem("10.ForceSerializationNullValue", ForceSerializationNullValueActivity.class),
                new ActionItem("11.ExclusionStrategies", ExclusionStrategiesActivity.class),
                new ActionItem("12.LenientUsage", LenientUsageActivity.class),
                new ActionItem("13.SpecialValueOfFloatDouble", SpecialValueOfFloatDoubleActivity.class),
                new ActionItem("14.SerializeEnum", SerializeEnumActivity.class),
                new ActionItem("15.CircularReferences", CircularReferencesActivity.class),
                new ActionItem("16.Generics", GenericsActivity.class),
                new ActionItem("17.CustomSerializationPart1", CustomSerializationPart1Activity.class),
                new ActionItem("18.CustomDeserializationBasic", CustomDeserializationBasicActivity.class),
                new ActionItem("19.CustomInstanceCreator", CustomInstanceCreatorActivity.class),
                new ActionItem("20.JsonAdapterAnnotation", JsonAdapterAnnotationActivity.class),
                new ActionItem("21.DeserializeOfPolymorphic", DeserializeOfPolymorphicActivity.class)
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
        private LayoutInflater mInflater;
        private List<ActionItem> mActions;

        ItemAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
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
                convertView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(getItem(position).title);
            return convertView;
        }
    }
}
