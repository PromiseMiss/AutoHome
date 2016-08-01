package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoData {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.project.main.autohome.model.db");
        Entity entity = schema.addEntity("AutoHomeBean");
        entity.addIdProperty().autoincrement().primaryKey();
        entity.addStringProperty("title");
        entity.addStringProperty("content");
        entity.addStringProperty("price");
        entity.addStringProperty("imageUrl");
        entity.addStringProperty("Url");
        entity.addStringProperty("info");
        entity.addStringProperty("classify");
        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
