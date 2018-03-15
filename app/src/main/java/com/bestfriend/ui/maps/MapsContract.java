package com.bestfriend.ui.maps;

import android.support.v7.widget.RecyclerView;

import com.bestfriend.ui.base.BaseView;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Avishay on 06/03/2018.
 */

public interface MapsContract
{
	interface View extends BaseView
	{
		
//		RecyclerView getRvContacts();
//
//		boolean mayRequestPermissions();

		GoogleMap getMap();

//		void setUsersAsMarkersOnMap();
		
	}
	
	interface Presenter {
		
		void loadUsers();
		
	}
}


