package com.tips.zy.tips.Main.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tips.zy.tips.Main.Entity.People;
import com.tips.zy.tips.Main.View.ItemMenuView;
import com.tips.zy.tips.R;
import com.tips.zy.tips.test.DemoActivity;

import java.util.List;

import zuo.biao.library.util.Log;

/**
 * Created by Zaric on 2015/1/29.
 */
public class ItemMenuAdapter extends BaseAdapter {
    private static final int ANIMATION_DURATION = 300;
    private Context context;
    private LayoutInflater inflater;
    private List<People> peoples;
    public ItemMenuAdapter(){
        super();
    }
    public ItemMenuAdapter(Context context,List<People> peoples){
        this.context=context;
        this.inflater=LayoutInflater.from(context);

        this.peoples=peoples;
    }

    @Override
    public int getCount() {
        if(peoples==null){
            return 0;
        }
        else{
            return peoples.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return position;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        ItemMenuView view = getItemView(position, (ItemMenuView) convertView, parent);
        view.setFocusManager((ItemMenuView.ISlideFocusManager) parent);
        view.hideMenu();
        return view;
    }

    public void deleteItem(final int pos, final ItemMenuView view){
        final int initialHeight = view.getMeasuredHeight();

        Animation anim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                }
                else {
                    view.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onDeleteItem(pos);
                view.clearAnimation();
                view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        anim.setDuration(ANIMATION_DURATION);
        view.startAnimation(anim);
    }

    public void onDeleteItem(int pos){
        peoples.remove(pos);
        notifyDataSetChanged();
    }

    public ItemMenuView getItemView(final int position, ItemMenuView convertView, ViewGroup parent){
        if(convertView==null){
            convertView = (ItemMenuView) inflater.inflate(R.layout.item_view, null);
        }
        View delete = convertView.findViewById(R.id.delete);
        LinearLayout item = (LinearLayout) convertView.findViewById(R.id.item);
        //item.setText(arrays.get(position));
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "单击了 "+position, Toast.LENGTH_SHORT).show();
            }
        });

        View info = convertView.findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "单击了信息", Toast.LENGTH_SHORT).show();
            }
        });

        final ItemMenuView finalConvertView = convertView;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalConvertView.hideMenu();
                deleteItem(position, finalConvertView);
            }
        });
        return convertView;
    };
}
