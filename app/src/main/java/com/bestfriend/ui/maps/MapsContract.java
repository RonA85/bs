package com.bestfriend.ui.maps;

import android.support.v7.widget.RecyclerView;

import com.bestfriend.ui.base.BaseView;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.ClusterManager;

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

		ClusterManager getClusterManager();

//		void setUsersAsMarkersOnMap();
		
	}
	
	interface Presenter {
		
		void loadUsers();
		
	}
}


