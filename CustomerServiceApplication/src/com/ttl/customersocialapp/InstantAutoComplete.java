<<<<<<< HEAD
package com.ttl.customersocialapp;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class InstantAutoComplete extends AutoCompleteTextView {
	


    public InstantAutoComplete(Context context) {
        super(context);
    }

    public InstantAutoComplete(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public InstantAutoComplete(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
            Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
       /* if (focused) {
            performFiltering(getText(), 0);
        }*/
        if (focused) {
            if (getText().toString().length() == 0) {
                // We want to trigger the drop down, replace the text.
           //   setText(loc.CityName);
            }
        }
    }

	@Override
	public void setValidator(Validator validator) {
		// TODO Auto-generated method stub
		super.setValidator(validator);
	}
    
    
}
=======
package com.ttl.customersocialapp;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class InstantAutoComplete extends AutoCompleteTextView {
	


    public InstantAutoComplete(Context context) {
        super(context);
    }

    public InstantAutoComplete(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public InstantAutoComplete(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
            Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
       
        if (focused) {
            if (getText().toString().length() == 0) {
       
            }
        }
    }

	@Override
	public void setValidator(Validator validator) {
		
		super.setValidator(validator);
	}
    
    
}
>>>>>>> TMSC QA 3.4V
