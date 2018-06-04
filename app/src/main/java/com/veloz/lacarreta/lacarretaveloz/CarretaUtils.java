package com.veloz.lacarreta.lacarretaveloz;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarretaUtils {


    public static List<LatLng> decode(String encodedPath) {
        int len = encodedPath.length();
        List<LatLng> path = new ArrayList();
        int index = 0;
        int lat = 0;
        int lng = 0;

        while(index < len) {
            int result = 1;
            int shift = 0;

            try {

                int b;
                do {
                    b = encodedPath.charAt(index++) - 63 - 1;
                    result += b << shift;
                    shift += 5;
                } while (b >= 31);

                lat += (result & 1) != 0 ? ~(result >> 1) : result >> 1;
                result = 1;
                shift = 0;

                do {
                    b = encodedPath.charAt(index++) - 63 - 1;
                    result += b << shift;
                    shift += 5;
                } while (b >= 31);

                lng += (result & 1) != 0 ? ~(result >> 1) : result >> 1;
                path.add(new LatLng((double) lat * 1.0E-5D, (double) lng * 1.0E-5D));
            }catch(Exception e){

            }
        }

        return path;
    }


}
