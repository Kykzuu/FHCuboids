package pl.freehc.fhcuboids;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CuboidHelper {

    public static List<CuboidModel> GetAllCuboids() {
        if(App.getInst().cuboidCache.size() != 1) {
            CuboidDTO cuboidDTO = new CuboidDTO();
            App.getInst().cuboidCache.put("cuboids", cuboidDTO.GetAll());
            return cuboidDTO.GetAll();
        }
        return App.getInst().cuboidCache.get("cuboids");
    }

    public static boolean IsPlayerHasCuboid(Player player) {
        List<CuboidModel> cuboids = CuboidHelper.GetAllCuboids();
        return cuboids
                .stream()
                .anyMatch(c -> c.getOwnerUUID().equals(player.getUniqueId()));
    }

    public static void DeleteCuboid(CuboidModel cuboid) {
        CuboidDTO cuboidDTO = new CuboidDTO();
        cuboidDTO.Delete(cuboid);
        App.getInst().cuboidCache.remove("cuboids");
    }

    public static void CreateCuboid(CuboidModel cuboid) {
        CuboidDTO cuboidDTO = new CuboidDTO();
        cuboidDTO.Add(cuboid);
        App.getInst().cuboidCache.remove("cuboids");
    }

    public static void AddPlayerToCuboid(CuboidModel cuboidModel, Player player) {
        cuboidModel.getFriendsUUID().add(player.getUniqueId());
        CuboidDTO cuboidDTO = new CuboidDTO();
        cuboidDTO.Update(cuboidModel);
        App.getInst().cuboidCache.remove("cuboids");
    }

    public static void DeletePlayerFromCuboid(CuboidModel cuboidModel, Player player) {
        cuboidModel.getFriendsUUID().remove(player.getUniqueId());
        CuboidDTO cuboidDTO = new CuboidDTO();
        cuboidDTO.Update(cuboidModel);
        App.getInst().cuboidCache.remove("cuboids");
    }

    public static void DeletePlayerFromCuboidByUUID(CuboidModel cuboidModel, UUID playerUUID) {
        cuboidModel.getFriendsUUID().remove(playerUUID);
        CuboidDTO cuboidDTO = new CuboidDTO();
        cuboidDTO.Update(cuboidModel);
        App.getInst().cuboidCache.remove("cuboids");
    }


    public static CuboidModel GetCuboid(Player player) {
        List<CuboidModel> cuboids = CuboidHelper.GetAllCuboids();
        return cuboids
                .stream()
                .filter(x -> x.getOwnerUUID().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);
    }

    public static CuboidModel GetCuboid(Location location) {
        List<CuboidModel> cuboids = CuboidHelper.GetAllCuboids();
        return cuboids
                .stream()
                .filter(x -> location.getX() >= x.getMiX())
                .filter(x -> location.getX() <= x.getMaX())
                .filter(x -> location.getZ() >= x.getMiZ())
                .filter(x -> location.getZ() <= x.getMaZ())
                .findFirst()
                .orElse(null);
    }

    public static boolean UpdateHomeLocation(CuboidModel cuboidModel, Location location){
        cuboidModel.setjajX(location.getX());
        cuboidModel.setjajY(location.getY());
        cuboidModel.setjajZ(location.getZ());
        CuboidDTO cuboidDTO = new CuboidDTO();
        cuboidDTO.Update(cuboidModel);
        App.getInst().cuboidCache.remove("cuboids");
        return true;
    }

    public static boolean IsOnAnyCuboidArea(Location location){

        List<CuboidModel> cuboids = CuboidHelper.GetAllCuboids();
        return cuboids
                .stream()
                .anyMatch(x -> location.getX() >= x.getMiX() && location.getX() <= x.getMaX()
                                && location.getZ() >= x.getMiZ() && location.getZ() <= x.getMaZ());
    }

    public static boolean HasPermissionToCuboid(CuboidModel cuboid, Player player) {
        if(cuboid.getOwnerUUID().equals(player.getUniqueId())){
            return true;
        }

        return cuboid.getFriendsUUID().stream().anyMatch(x -> x.equals(player.getUniqueId()));
    }

    public static List<CuboidModel> GetCuboidsWherePlayerIsAdded(Player player) {
        List<CuboidModel> cuboids = CuboidHelper.GetAllCuboids();
        return cuboids
                .stream()
                .filter(x ->
                        x.getFriendsUUID().stream().anyMatch(c -> c.equals(player.getUniqueId()))
                ).collect(Collectors.toList());

    }


    public static String GetPrefixedText(String text) {
        return App.getInst().getConfig().getString("PrefixedText").replace('&', '§')+text;
    }

    public static String GetPrefixedBulletText(String text) {
        return App.getInst().getConfig().getString("PrefixedBulletText").replace('&', '§')+text;
    }

}
