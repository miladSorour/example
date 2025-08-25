package org.milad.example.reflection;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityPopulator1 {

    public static void main(String[] args) throws Exception {
        EntityPopulator1 populator = new EntityPopulator1();
        populator.TestReflection();
    }

    public void TestReflection() throws Exception {

        Post post = new Post();

        Field field = Post.class.getDeclaredField("comments");
        field.setAccessible(true);

        Comment comment = new Comment();
        comment.id = 1L;
        Comment comment1 = new Comment();
        comment1.id = 2L;

        List<Comment> fakeList = new ArrayList<>();
        fakeList.add(comment);
        fakeList.add(comment1);

        field.set(post, fakeList);

        System.out.println(post.getComments());

    }


    public static class Post {
        private Long id;
        private List<Comment> comments;

        public List<Comment> getComments() {
            return comments;
        }
    }

    public static class Comment {
        private Long id;
        private Long postId;

        @Override
        public String toString() {
            return "Comment{" +
                    "id=" + id +
                    ", postId=" + postId +
                    '}';
        }
    }
}
