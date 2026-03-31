import java.util.ArrayList;

public class Post {

    int postID;
    String username;
    String postText;

    public Post(int postID, String username, String text) {
        this.postID = postID;
        this.username = username.toLowerCase();
        this.postText = text;
    }

    public int getPostID() {
        return postID;
    }

    public String getPostUser() {
        return username;
    }

    public String getPostText() {
        return postText;
    }

    public ArrayList<String> findTags() {
        ArrayList<String> tags = new ArrayList<>();

        for (int i = 0; i < postText.length(); i++) {
            if (postText.charAt(i) == '#') {
                String tag = "";
                int j = i + 1;

                while (
                    j < postText.length() &&
                    Character.isLetterOrDigit(postText.charAt(j))
                ) {
                    tag += Character.toLowerCase(postText.charAt(j));
                    j++;
                }

                if (!tag.equals("")) {
                    tags.add(tag);
                }
            }
        }

        return tags;
    }
}
