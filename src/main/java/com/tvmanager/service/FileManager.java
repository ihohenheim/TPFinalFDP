
import com.tvmanager.model.TV;
import com.tvmanager.model.TVGroup;
import java.io;
import java.util.List;
import java.util.Map;

public class FileManager {

    private static final String FILE_PATH = "src/main/resources/data/tv_data.txt";

    public static void saveData(Map<String, TVGroup> groups) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (TVGroup group : groups.values()) {
                writer.write(group.getGroupId() + "," + group.getDescription());
                writer.newLine();
                for (TV tv : groupackage com.tvmanager.service;
p.getTvs()) {
                    writer.write(tv.getId() + "," + tv.getDescription() + "," + tv.getGroup() + "," + tv.getVolume()
                            + "," + tv.getChannel() + "," + tv.isOn());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(Map<String, TVGroup> groups) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            TVGroup currentGroup = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    currentGroup = new TVGroup(parts[0], parts[1]);
                    groups.put(currentGroup.getGroupId(), currentGroup);
                } else if (parts.length == 6 && currentGroup != null) {
                    TV tv = new TV(parts[1], parts[2]);
                    tv.setId(parts[0]);
                    tv.setVolume(Integer.parseInt(parts[3]));
                    tv.setChannel(Integer.parseInt(parts[4]));
                    if (Boolean.parseBoolean(parts[5])) {
                        tv.togglePower();
                    }
                    currentGroup.addTV(tv);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
