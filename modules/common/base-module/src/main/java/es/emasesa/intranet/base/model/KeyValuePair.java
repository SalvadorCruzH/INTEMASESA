package es.emasesa.intranet.base.model;

import java.io.Serializable;
import java.util.Objects;

public class KeyValuePair implements Comparable<KeyValuePair>, Serializable {

    private static final long serialVersionUID = -7969204938635750907L;

    public KeyValuePair() {
        this(null, null);
    }

    public KeyValuePair(String key, String value) {
        _key = key;
        _value = value;
    }

    @Override
    public int compareTo(KeyValuePair kvp) {
        return _key.compareTo(kvp.getK());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof KeyValuePair)) {
            return false;
        }

        KeyValuePair kvp = (KeyValuePair)obj;

        if (Objects.equals(_key, kvp._key)) {
            return true;
        }

        return false;
    }

    public String getK() {
        return _key;
    }

    public String getV() {
        return _value;
    }

    @Override
    public int hashCode() {
        if (_key != null) {
            return _key.hashCode();
        }

        return 0;
    }

    public void setK(String key) {
        _key = key;
    }

    public void setV(String value) {
        _value = value;
    }

    private String _key;
    private String _value;

}