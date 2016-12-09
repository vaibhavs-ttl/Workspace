<<<<<<< HEAD
package com.ttl.helper;

import java.util.Arrays;
import java.util.List;

import android.widget.AutoCompleteTextView;

public class Validator implements AutoCompleteTextView.Validator {
	String[] valid ; 
	public Validator(List<String> values)
	{
		//if(values!=null)
		valid = new String[values.size()];
			valid = values.toArray(valid);
	}
    @Override
    public boolean isValid(CharSequence text) {
      //  Log.v("Test", "Checking if valid: "+ text);
    	
        Arrays.sort(valid);
        if (Arrays.binarySearch(valid, text.toString()) > 0) {
            return true;
        }

        return false;
    }

    @Override
    public CharSequence fixText(CharSequence invalidText) {
      //  Log.v("Test", "Returning fixed text");

        /* I'm just returning an empty string here, so the field will be blanked,
         * but you could put any kind of action here, like popping up a dialog?
         * 
         * Whatever value you return here must be in the list of valid words.
         */
        return "";
    }
=======
package com.ttl.helper;

import java.util.Arrays;
import java.util.List;

import android.widget.AutoCompleteTextView;

public class Validator implements AutoCompleteTextView.Validator {
	String[] valid ; 
	public Validator(List<String> values)
	{
		
		valid = new String[values.size()];
			valid = values.toArray(valid);
	}
    @Override
    public boolean isValid(CharSequence text) {
      
    	
        Arrays.sort(valid);
        if (Arrays.binarySearch(valid, text.toString()) > 0) {
            return true;
        }

        return false;
    }

    @Override
    public CharSequence fixText(CharSequence invalidText) {
              return "";
    }
>>>>>>> TMSC QA 3.4V
}