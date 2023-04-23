package pl.freehc.fhcuboids.hooks;

import org.bukkit.Bukkit;
import org.dynmap.DynmapAPI;
import org.dynmap.markers.AreaMarker;
import org.dynmap.markers.MarkerSet;
import pl.freehc.fhcuboids.App;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

public class DynmapHook {
    public void RegisterCuboidMarker(CuboidModel cuboid) {
        DynmapAPI dynmap = App.getInst().dynmapApi;
        MarkerSet markerSet = dynmap.getMarkerAPI().getMarkerSet("Cuboids");
        if(dynmap.getMarkerAPI().getMarkerSet("Cuboids") == null){
            markerSet = dynmap.getMarkerAPI().createMarkerSet("Cuboids", "Cuboid", dynmap.getMarkerAPI().getMarkerIcons(), false);
        }
        String markerid = "cuboid_" + cuboid.getID();
        AreaMarker am = markerSet.createAreaMarker(markerid, markerid, false, "world", new double[1000], new double[1000], false);
        double[] d1 = {cuboid.getMiX(), cuboid.getMaX()};
        double[] d2 = {cuboid.getMiZ(), cuboid.getMaZ()};
        am.setCornerLocations(d1, d2);
        am.setLabel("Cuboid " + cuboid.getOwnerNickname());
        am.setDescription("Cuboid " + cuboid.getOwnerNickname());
    }


}
