import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Pair> list;

    public Dictionary() {
        list = new ArrayList<Pair>();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public boolean contains(String key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public String get(String key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                return list.get(i).getValue();
            }
        }
        return null;
    }

    public void remove(String key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                list.remove(i);
                return;
            }
        }
    }

    public void put(String key, String value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                list.remove(i);
                break;
            }
        }
        list.add(new Pair(key, value));
    }

    public String toString() {
        String result = "The dictionary contains " + list.size() + " items.";
        for (int i = 0; i < list.size(); i++) {
            result += "\n\n" + (i + 1) + ":\n" + list.get(i).toString();
        }
        return result;
    }
}