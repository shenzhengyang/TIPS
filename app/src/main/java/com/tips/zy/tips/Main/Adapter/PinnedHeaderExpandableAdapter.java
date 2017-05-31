package com.tips.zy.tips.Main.Adapter;

import android.content.Context;
import android.content.Intent;
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

import com.tips.zy.tips.AddPeople.Activity.PeopleInfoAllActivity;
import com.tips.zy.tips.Main.Entity.Group;
import com.tips.zy.tips.Main.View.PinnedHeaderExpandableListView;
import com.tips.zy.tips.R;
import com.tips.zy.tips.Main.View.ItemMenuView;

import java.util.ArrayList;
import java.util.List;

import static android.view.FrameMetrics.ANIMATION_DURATION;


public class PinnedHeaderExpandableAdapter extends  BaseExpandableListAdapter implements PinnedHeaderExpandableListView.HeaderAdapter {
	/*private String[][] childrenData;
	private String[] groupData;*/
	private List<Group> groups=new ArrayList<>();
	private Context context;
	private PinnedHeaderExpandableListView listView;
	private LayoutInflater inflater;
	
	public PinnedHeaderExpandableAdapter(List<Group> groups
			,Context context,PinnedHeaderExpandableListView listView){
		/*this.groupData = groupData;
		this.childrenData = childrenData;*/
		this.groups=groups;
		this.context = context;
		this.listView = listView;
		inflater = LayoutInflater.from(this.context);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groups.get(groupPosition).getPeoples().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {
		View view = null;  
        if (convertView != null) {  
            view = convertView;  
        } else {  
            view = createChildrenView();  
        }
		View delete = view.findViewById(R.id.delete);
		LinearLayout item = (LinearLayout) view.findViewById(R.id.item);
		Log.d("childData",groups.get(groupPosition).getPeoples().get(childPosition).getP_Id()+"");
		//item.setText("childrenData[groupPosition][childPosition]");
		item.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "单击了 item", Toast.LENGTH_SHORT).show();
				Intent intent=PeopleInfoAllActivity.CreateIntent(context);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("P_Id",groups.get(groupPosition).getPeoples().get(childPosition).getP_Id());
				context.startActivity(intent);
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
		ImageView Icon= (ImageView) view.findViewById(R.id.P_Icon);
		TextView Name= (TextView) view.findViewById(R.id.P_Name);
		TextView Hobby= (TextView) view.findViewById(R.id.P_Hobby);
		Icon.setImageResource(groups.get(groupPosition).getPeoples().get(childPosition).getIcon());
		Name.setText(groups.get(groupPosition).getPeoples().get(childPosition).getP_Name());
		Hobby.setText(groups.get(groupPosition).getPeoples().get(childPosition).getP_Hobby());

		return view;
//        TextView text = (TextView)view.findViewById(R.id.childto);
//        text.setText(childrenData[groupPosition][childPosition]);
//        return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groups.get(groupPosition).getPeoples().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition).getGroupName();
	}

	@Override
	public int getGroupCount() {
		return groups.size();
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
			iv.setImageResource(R.mipmap.jiantou_xiala_xia);
		}
		else{
			iv.setImageResource(R.mipmap.jiantou_you);
		}
        
        TextView text = (TextView)view.findViewById(R.id.groupto);
        text.setText(groups.get(groupPosition).getGroupName());
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
		String groupData =  this.groups.get(groupPosition).getGroupName();
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
