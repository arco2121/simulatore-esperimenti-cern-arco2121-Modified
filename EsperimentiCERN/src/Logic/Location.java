package Logic;

public class Location {
    private String key;
    private String name;
    private String description;
    private String query;

    public Location(String key, String name, String description, String query) {
        this.key = key;
        this.name = name;
        this.description = description;
        this.query = query;
    }

    // Getters
    public String getKey() { return key; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getQuery() { return query; }

    // Setters
    public void setKey(String key) { this.key = key; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setQuery(String query) { this.query = query; }

    @Override
    public String toString() {
        return "Location{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}