import java.util.ArrayList;

public class User {
    // private vars
    private String name;
    private ArrayList<Post> posts;


    // constructor
    public User(String name) {
        // Convert the username to lowercase and store the lowercase version in the username attribute.
        this.name = name.toLowerCase();
        // Initialize the posts to an empty ArrayList<Post>
        this.posts = new ArrayList<>();
    }

    public String getUserName() {
        return this.name;

    }

    public ArrayList<Post> getUserPosts() {
        return this.posts;
    }

    public void addUserPost(Post p) {
        this.posts.add(p);
    }


}