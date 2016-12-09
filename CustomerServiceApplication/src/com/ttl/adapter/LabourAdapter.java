package com.ttl.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ttl.communication.GlobalAccessObject;
import com.ttl.customersocialapp.R;
import com.ttl.model.LabourModel;

public class LabourAdapter extends BaseAdapter implements Filterable{

	
	private Context context;
	private boolean isChanged=false;
	private ArrayList<LabourModel> mDataFiltered;//the filtered data
	private ArrayList<LabourModel> labour_list=new ArrayList<>();
	private ArrayList<LabourModel> checked_list=new ArrayList<>();
	public static  ArrayList<LabourModel> selected_data=new ArrayList<>();
	private TextView show_empty_text;
	public LabourAdapter(Context context,ArrayList<LabourModel> labour_list,TextView show_empty_text)
	{
		
	//	super(context, R.layout.labour_parts_custom_row, labour_list);
		this.labour_list=labour_list;
		this.context=context;
		mDataFiltered=labour_list;
		this.show_empty_text=show_empty_text;
		checked_list=GlobalAccessObject.getLabour_obj();
		
		
	}
	
	@Override
	public int getCount() {

		
	
		return mDataFiltered.size();
	//	return labour_list.size();
	
	}

	

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

/*		ViewHolder holder=new ViewHolder();
		
		if (convertView==null) {
		
			LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView=inflater.inflate(R.layout.labour_parts_custom_row, parent,false);
			
			holder.labour_desc=(TextView)convertView.findViewById(R.id.labour_desc);
			holder.labour_qty=(TextView)convertView.findViewById(R.id.labour_qty);
			holder.select_data=(CheckBox)convertView.findViewById(R.id.labour_select_box);
			holder.labourPartRow=(LinearLayout)convertView.findViewById(R.id.labourPartRow);
			
			convertView.setTag(holder);
			
			
		}
		else
		{
			
			holder=(ViewHolder)convertView.getTag();
			
		}
		
		if ((position%2)==0) {
			
	
			holder.labourPartRow.setBackgroundColor(Color.parseColor("#508dba"));
		
		}
		else
		{
	
			holder.labourPartRow.setBackgroundColor(Color.parseColor("#387caf"));
		
		}
		
		
		
		
		holder.labour_desc.setText(labour_list.get(position).getLabourDescription());
		holder.labour_qty.setText(labour_list.get(position).getDefaultQty());
		
		holder.select_data.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
				LabourModel labourModel = null;
				if (isChecked) {
					
					labourModel=new LabourModel();
					
					labourModel.setDefaultQty(labour_list.get(position).getDefaultQty());
					labourModel.setLabourDescription(labour_list.get(position).getLabourDescription());
					labourModel.setType("Labour");
					labourModel.setLabourType(labour_list.get(position).getLabourType());
					labourModel.setBillingHours(labour_list.get(position).getBillingHours());
					labourModel.setCheckedPosition(position);
					labourModel.setChecked_state(true);
					selected_data.add(labourModel);
					GlobalAccessObject.setLabour_obj(selected_data);
					isChanged=true;
					
				}
				else 
				{
					isChanged=false;
					selected_data.remove(labourModel);
					GlobalAccessObject.NullifyLabour_obj();
				}
				
			}
		});

		
		if (checked_list!=null) {
			
			
			
				if (position<checked_list.size()) {
				
				int pos=checked_list.get(position).getCheckedPosition();
				holder.select_data.setChecked(true);
			
		}
		}
*/
		
		
		ViewHolder holder=null;
		
		
		if (convertView==null) {
			
			LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView=inflater.inflate(R.layout.labour_parts_custom_row, parent,false);
			
			holder=new ViewHolder();
			
			holder.labourPartRow=(LinearLayout)convertView.findViewById(R.id.labourPartRow);
			holder.labour_desc=(TextView)convertView.findViewById(R.id.labour_desc);
			holder.labour_qty=(TextView)convertView.findViewById(R.id.labour_qty);
			holder.select_data=(CheckBox)convertView.findViewById(R.id.labour_select_box);

			holder.select_data.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					
					
					CheckBox buttonView=(CheckBox)v;
					int getPosition=(Integer)buttonView.getTag();
					labour_list.get(getPosition).setSelected_state(buttonView.isChecked());
					
					LabourModel labourModel=null;
						
						if (buttonView.isChecked()) {
						
						labourModel=new LabourModel();
						labourModel.setDefaultQty(labour_list.get(position).getDefaultQty());
						labourModel.setLabourDescription(labour_list.get(position).getLabourDescription());
						labourModel.setType("Labour");
						labourModel.setLabourType(labour_list.get(position).getLabourType());
						labourModel.setBillingHours(labour_list.get(position).getBillingHours());
						labourModel.setCheckedPosition(getPosition);
						selected_data.add(labourModel);
					//	GlobalAccessObject.setLabour_obj(selected_data);
						
						GlobalAccessObject.setLabour_obj(labourModel);
						
						Log.v("item added", "item added");
					}
					else 
					{
						
						
						
						selected_data.remove(labourModel);
					
						
						//	GlobalAccessObject.NullifyLabour_obj();
						GlobalAccessObject.removeLabour_obj(GlobalAccessObject.getLabour_obj().get(getPosition));
						
						Log.v("item removed", "item removed");
						labourModel=null;
					}
					
					
						
						
						
						
				}
			});
			
			
			
			convertView.setTag(holder);
			convertView.setTag(R.id.labourPartRow, holder.labourPartRow);
			convertView.setTag(R.id.labour_desc, holder.labour_desc);
			convertView.setTag(R.id.labour_qty, holder.labour_qty);
			convertView.setTag(R.id.labour_select_box, holder.select_data);
		}
		else
		{
			holder=(ViewHolder)convertView.getTag();
		}
		
		
		
		
		
		
		
		

		if ((position%2)==0) {
			
			
			holder.labourPartRow.setBackgroundColor(Color.parseColor("#508dba"));
		
		}
		else
		{
	
			holder.labourPartRow.setBackgroundColor(Color.parseColor("#387caf"));
		
		}
		
		
		holder.select_data.setTag(position);
		
		holder.labour_desc.setText(labour_list.get(position).getLabourDescription());
		holder.labour_qty.setText(labour_list.get(position).getDefaultQty());
		
		holder.select_data.setChecked(labour_list.get(position).isSelected_state());
			
		if (checked_list!=null) {
			
			
			
			if (position<checked_list.size()) {

				int pos=checked_list.get(position).getCheckedPosition();
			
				holder.select_data.setChecked(true);
				
				
			}
		}
		
		
		
		
		return convertView;
	}

	
	
	
	static class ViewHolder
	{
		
		TextView labour_desc;
		TextView labour_qty;
		CheckBox select_data;
		LinearLayout labourPartRow;
		
	}

	
	@Override
	public Filter getFilter() {
	
		return new Filter() {
	
			
			
			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
			
				

						
			mDataFiltered=(ArrayList<LabourModel>)results.values;
			
			
			if (mDataFiltered.size()==0) {
				
				show_empty_text.setVisibility(View.VISIBLE);
				Log.v("if called", "called");
				
			}
			else
			{
				show_empty_text.setVisibility(View.GONE);
				Log.v("else called", "called");
			}
			
			//	labour_list=mDataFiltered=(ArrayList<LabourModel>)results.values;
				
		/*	for (int j = 0; j < mDataFiltered.size(); j++) {
				
				
				Log.v("mDataFiltered", mDataFiltered.get(j).getLabourDescription().toLowerCase());
				
			}*/
				
			notifyDataSetChanged();
			
			}
			
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
			
				
			//	Log.v("entered character", ""+constraint);
				
				String filterString=constraint.toString().toLowerCase();
				FilterResults results=new FilterResults();
				
				final List<LabourModel> list=labour_list;
				
				int count=list.size();
				final ArrayList<LabourModel> nList=new ArrayList<>(count);
				
			//	if (!TextUtils.isEmpty(constraint)) {
					
			
				
				for(int i=0;i<count;i++)
				{
					
				
				//	Log.v("inside loop"+" "+i, list.get(i).getLabourDescription().toLowerCase());
					
					if (list.get(i).getLabourDescription().toLowerCase().startsWith(filterString)) {
						
						nList.add(list.get(i));
				
					//	Log.v("inside if", list.get(i).getLabourDescription().toLowerCase());
					}
					
					
				
					
					
				}
				
				
							
				results.values=nList;
				results.count=nList.size();
				
				
				//}
				/*else
				{
					
					results.values=labour_list;
					results.count=labour_list.size();
					
				}*/
				
				
				
			//	notifyDataSetChanged();
				
				
				return results;
			}
		};
	}

	@Override
	public Object getItem(int position) {
		
		return mDataFiltered.get(position);
	//	return labour_list.get(position);
	
	}


	
}
