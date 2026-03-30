import java.util.ArrayList;

public class Tag {
    private String tag;
    private ArrayList<Post> posts;

    public Tag(String tag) {

        this.tag = tag;
        this.posts = new ArrayList<>();
    }

    public String getTagName() {
        return tag;
    }

    public ArrayList<Post> getTagPosts() {
        return posts;
    }
    public void addTagPost(Post p) {
        for (Post post : posts) {
            if (post.getPostID() == p.getPostID()) {
                return;
            }
        }
        posts.add(p);
    }

}
