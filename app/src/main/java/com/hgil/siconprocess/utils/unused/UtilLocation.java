package com.hgil.siconprocess.utils.unused;

/**
 * Created by mohan.giri on 01-03-2017.
 */

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import java.util.List;

public class UtilLocation {
    public static Location getLastKnownLoaction(boolean enabledProvidersOnly, Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location utilLocation = null;
        List<String> providers = manager.getProviders(enabledProvidersOnly);
        for (String provider : providers) {
            utilLocation = manager.getLastKnownLocation(provider);
            if (utilLocation != null) return utilLocation;
        }
        return null;
    }
}
