package es.emasesa.intranet.base.model;

public class CustomMetadata {
    // TYPE
    public static final int TYPE_STRING = 0; // Cast (String)
    public static final int TYPE_STRING_LIST = 1; // Cast (String[])
    public static final int TYPE_DATE = 2; // Cast (Date)

    // REPEAT
    public static final boolean REPEAT_FIELD_YES = Boolean.TRUE;
    public static final boolean REPEAT_FIELD_NO = Boolean.FALSE;

    private String name;
    private Object value;
    private int type;
    private boolean repeat;

    private CustomMetadata(){
        // private default constructor
    }

    public CustomMetadata(final int type,
                          final boolean repeat,
                          String name,
                          Object value){
        this.type = type;
        this.repeat = repeat;
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    public boolean isRepeat() {
        return repeat;
    }
}
