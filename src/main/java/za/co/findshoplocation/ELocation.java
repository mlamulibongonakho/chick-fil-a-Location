package za.co.findshoplocation;

public enum ELocation {
    NEW_YORK("New York"),
    NEW_BERN("New Bern"),
    NEW_TOWN("New Town"),
    WOODLAND("Woodland hills"),
    CLEVELAND("Cleveland, ga"),
    CLEMSON("Clemson, sc"),
    JOHNSON("Johnson city, tn"),
    NEW_JERSEY("New Jersey"),

    BLEAUFORT("beaufort, sc");
    String name;

    ELocation(String name) {
        this.name = name;
    }
}
