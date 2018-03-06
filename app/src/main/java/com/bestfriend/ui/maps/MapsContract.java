package com.bestfriend.ui.maps;

import android.support.v7.widget.RecyclerView;

import com.bestfriend.ui.base.BaseView;

/**
 * Created by Avishay on 06/03/2018.
 */

public interface MapsContract
{
	interface View extends BaseView
	{
		
		RecyclerView getRvContacts();
		
		boolean mayRequestPermissions();
		
	}
	
	interface Presenter {
		
		void loadContacts();
		
	}
}


