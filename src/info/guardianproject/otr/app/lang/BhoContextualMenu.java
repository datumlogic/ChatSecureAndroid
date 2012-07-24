package info.guardianproject.otr.app.lang;

import info.guardianproject.otr.app.im.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class BhoContextualMenu extends AlertDialog.Builder {
	Context context;
	BhoAdapter ba;
	Map<Integer, String> options;
	
	public BhoContextualMenu(Context context) {
		super(context);
		this.context = context;	
	}
	
	public void add(int key, String label) {
	    if(options == null)
	        options = new HashMap<Integer, String>();
	    
	    options.put(key, label);
	}
	
	public BhoAdapter setOpts() {
	    ba = new BhoAdapter(options);
	    return ba;
	}
	
	public BhoAdapter getAdapter() {
	    return ba;
	}
	
	public class BhoAdapter extends BaseAdapter {
		Map<Integer, String> options;
		LayoutInflater li;
		
		public BhoAdapter(Map<Integer, String> options) {
			li = LayoutInflater.from(context);
			this.options = options;
		}
		
		public int getKeyByPosition(int position) {
			int key = -1;
			int match = 0;
			Iterator<Entry<Integer, String>> i = options.entrySet().iterator();
			while(i.hasNext()) {
				Entry<Integer, String> e = i.next();
				if(match == position)
					key = e.getKey();
				
				match++;
			}
			
			return key;
		}
		
		public String getLabelByPosition(int position) {
			String label = "";
			int match = 0;
			Iterator<Entry<Integer, String>> i = options.entrySet().iterator();
			while(i.hasNext()) {
				Entry<Integer, String> e = i.next();
				if(match == position)
					label = e.getValue();
				
				match++;
			}
			return label;
		}
		
		@Override
		public int getCount() {
			return options.size();
		}

		@Override
		public Object getItem(int position) {
			return options.get(getKeyByPosition(position));
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = li.inflate(R.layout.bho_context_menu_item, null);
			BhoTextView label = (BhoTextView) convertView.findViewById(R.id.menuItemText);
			label.setText(getLabelByPosition(position));
			return convertView;
		}
		
	}
}
