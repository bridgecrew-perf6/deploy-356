import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;

public class JavaJsonHandler {
    private static FileWriter fileToWrite;

    public static void main(String[] args) throws Exception {
        String filename = "./json/DeploymentPlan.json";
        try {
            String contents = new String((Files.readAllBytes(Paths.get(filename))));
            JSONObject o = new JSONObject(contents);

            JSONObject deploy = new JSONObject(o.getJSONObject("Deploy").toString());

            System.out.println(deploy.getString("PackageName"));
            deploy.put("PackageName", "testando");
            deploy.append("teste", "teste");
            System.out.println(deploy.getString("PackageName"));

            System.out.println(deploy.getString("Name"));
            System.out.println(deploy.getString("_comment"));

            fileToWrite = new FileWriter("./json/DeploymentPlanNew.json");
            fileToWrite.write(deploy.toString());
            fileToWrite.flush();
            fileToWrite.close();

            JSONArray task = o.getJSONArray("Task");
            for (int i = 0; i < task.length(); i++) {
                JSONObject myTask = new JSONObject(task.get(i).toString());
                System.out.println(myTask.getString("TaskJsonFile"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
