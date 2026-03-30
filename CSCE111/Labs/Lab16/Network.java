import java.util.ArrayList;

public class Network {

    private ArrayList<User> users;
    private ArrayList<Post> posts;
    private ArrayList<Tag> tags;
    private int postCounter;

    public Network() {
        this.users = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.postCounter = 0;
    }

    private User addUser(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username.toLowerCase())) {
                return user;
            }
        }

        User newUser = new User(username);
        users.add(newUser);
        System.out.println("Added user " + username);
        return newUser;
    }

    private Tag addTag(String tag) {
        for (Tag t : tags) {
            if (t.getTagName().equals(tag)) {
                return t;
            }
        }

        Tag newTag = new Tag(tag);
        tags.add(newTag);
        System.out.println("Added tag " + tag);
        return newTag;
    }

    public void addPost(String username, String postText) {
        User user = addUser(username);

        Post post = new Post(postCounter, username, postText);
        postCounter++;

        user.addUserPost(post);

        ArrayList<String> postTags = post.findTags();
        for (String tag : postTags) {
            Tag tagObj = addTag(tag);
            tagObj.addTagPost(post);
        }

        posts.add(post);
    }

    public ArrayList<Post> getPostsByUser(String user) {
        ArrayList<Post> empty = new ArrayList<>();

        for (User u : users) {
            if (u.getUserName().equals(user.toLowerCase())) {
                return u.getUserPosts();
            }
        }

        return empty;
    }

    public ArrayList<Post> getPostsWithTag(String tag) {
        ArrayList<Post> empty = new ArrayList<>();

        for (Tag t : tags) {
            if (t.getTagName().equals(tag)) {
                return t.getTagPosts();
            }
        }

        return empty;
    }

    public String getMostPopularHashtag() {
        int maxPosts = 0;
        String bestTag = "";

        for (Tag t : tags) {
            if (t.getTagPosts().size() > maxPosts) {
                maxPosts = t.getTagPosts().size();
                bestTag = t.getTagName();
            }
        }

        return bestTag;
    }
}
