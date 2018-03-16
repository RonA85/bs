package com.bestfriend.ui.maps;

import android.support.v7.widget.RecyclerView;

import com.bestfriend.model.User;
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
		
		RecyclerView getRvUsersGarden();
//
//		boolean mayRequestPermissions();

		GoogleMap getMap();

		ClusterManager getClusterManager();

        void moveToProfileScreen(User user);

//		void setUsersAsMarkersOnMap();
		
	}
	
	interface Presenter {
		
		void loadUsers();

		void createDog();
		
	}
}


