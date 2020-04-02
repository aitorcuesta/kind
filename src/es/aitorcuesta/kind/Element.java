package es.aitorcuesta.kind;

public class Element {
    
    private String key;
    private String value;
    
    public Element(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        return "Element [key=" + key + ", value=" + value + "]";
    }

}
