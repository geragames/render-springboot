package com.fich.sarh.plantofpositions.domain.model;

import com.fich.sarh.common.CharacterPlant;
import com.fich.sarh.common.PlantStatus;

public class PlantProjectionDTO {

    private final String id;
    private final String currentStatusID;
    private final String characterplantID;
    private final String nameSubUnit;
    private final String firstname;
    private final String lastname;
    private final String document;
    private final String namePosition;

    public PlantProjectionDTO(
            String id,
            String currentStatusID,
            String characterplantID,
            String nameSubUnit,
            String firstname,
            String lastname,
            String document,
            String namePosition
    ) {
        this.id = id;
        this.currentStatusID = currentStatusID;
        this.characterplantID = characterplantID;
        this.nameSubUnit = nameSubUnit;
        this.firstname = firstname;
        this.lastname = lastname;
        this.document = document;
        this.namePosition = namePosition;
    }

    public String getId() { return id; }
    public String getCurrentStatusID() { return currentStatusID; }
    public  String getCharacterplantID() { return characterplantID; }
    public String getNameSubUnit() { return nameSubUnit; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getDocument() { return document; }
    public String getNamePosition() { return namePosition; }
}


/*
package com.fich.sarh.plantofpositions.domain.model;

public interface PlantProjectionDTO {

    String getId();
    String getCurrentStatusID();
    String getCharacterplantID();
    String getNameSubUnit();
    String getFirstname();
    String getLastname();
    String getDocument();
    String getNamePosition();
}*/
