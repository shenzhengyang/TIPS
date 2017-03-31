package com.tips.zy.tips.Main.Adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tips.zy.tips.Main.View.PinnedHeaderExpandableListView;
import com.tips.zy.tips.R;
import com.tips.zy.tips.Main.View.ItemMenuView;

import static android.view.FrameMetrics.ANIMATION_DURATION;


public class PinnedHeaderExpandableAdapter extends  BaseExpandableListAdapter implements PinnedHeaderExpandableListView.HeaderAdapter {
	private String[][] childrenData;
	private String[] groupData;
	private Context context;
	private PinnedHeaderExpandableListView listView;
	private LayoutInflater inflater;
	
	public PinnedHeaderExpandableAdapter(String[][] childrenData,String[] groupData
			,Context context,PinnedHeaderExpandableListView listView){
		this.groupData = groupData; 
		this.childrenData = childrenData;
		this.context = context;
		this.listView = listView;
		inflater = LayoutInflater.from(this.context);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childrenData[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view = null;  
        if (convertView != null) {  
            view = convertView;  
        } else {  
            view = createChildrenView();  
        }
		View delete = view.findViewById(R.id.delete);
		LinearLayout item = (LinearLayout) view.findViewById(R.id.item);
		Log.d("childData",childrenData[groupPosition][childPosition]);
		//item.setText("childrenData[groupPosition][childPosition]");
		item.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "单击了 item", Toast.LENGTH_SHORT).show();
			}
		});

		View info = view.findViewById(R.id.info);
		info.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "单击了信息", Toast.LENGTH_SHORT).show();
			}
		});

		final ItemMenuView finalConvertView = (ItemMenuView) view;
		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finalConvertView.hideMenu();
				//deleteItem(groupPosition,childPosition,finalConvertView);
			}
		});
		return view;

//        TextView text = (TextView)view.findViewById(R.id.childto);
//        text.setText(childrenData[groupPosition][childPosition]);
//        return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childrenData[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupData[groupPosition];
	}

	@Override
	public int getGroupCount() {
		return groupData.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view = null;  
        if (convertView != null) {  
            view = convertView;  
        } else {  
            view = createGroupView();  
        } 
        
        ImageView iv = (ImageView)view.findViewById(R.id.groupIcon);
		
		if (isExpanded) {
			iv.setImageResource(R.mipmap.btn_browser2);
		}
		else{
			iv.setImageResource(R.mipmap.btn_browser);
		}
        
        TextView text = (TextView)view.findViewById(R.id.groupto);
        text.setText(groupData[groupPosition]);  
        return view;  
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	private View createChildrenView() {
		return inflater.inflate(R.layout.item_view, null);
	}
	
	private View createGroupView() {
		return inflater.inflate(R.layout.group, null);
	}

	@Override
	public int getHeaderState(int groupPosition, int childPosition) {
		final int childCount = getChildrenCount(groupPosition);
		if (childPosition == childCount - 1) {
			return PINNED_HEADER_PUSHED_UP;
		} else if (childPosition == -1
				&& !listView.isGroupExpanded(groupPosition)) {
			return PINNED_HEADER_GONE;
		} else {
			return PINNED_HEADER_VISIBLE;
		}
	}

	@Override
	public void configureHeader(View header, int groupPosition,
			int childPosition, int alpha) {
		String groupData =  this.groupData[groupPosition];
		((TextView) header.findViewById(R.id.groupto)).setText(groupData);
		
	}
	
	private SparseIntArray groupStatusMap = new SparseIntArray(); 
	
	@Override
	public void setGroupClickStatus(int groupPosition, int status) {
		groupStatusMap.put(groupPosition, status);
	}

	@Override
	public int getGroupClickStatus(int groupPosition) {
		if (groupStatusMap.keyAt(groupPosition)>=0) {
			return groupStatusMap.get(groupPosition);
		} else {
			return 0;
		}
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
				//onDeleteItem(pos);
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
//	public void onDeleteItem(int pos){
//		childrenData.remove();
//		notifyDataSetChanged();
//	}


}
