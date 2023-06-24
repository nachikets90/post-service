# post-service
**Sample JSON**

**We are assuming one to may relationship between document and Post

Method Type : Post[Adding new Post]
URL: http://localhost:8084/api/v1/posts/post
{
    "userId": 1,
    "title": "First Post",
    "body": "First Post",
    "fileName": "ADHAR.pdf"
    
}

Method Type : Get[Get all post attached with document]
URL: http://localhost:8084/api/v1/posts/file/{fileName}


Method Type : Post[Adding new comment]
http://localhost:8084/api/v1/posts/comment
{
    "postID": 1,
    "userId": 1,
    "comment_message": "New Comment"
}


Method Type : get[get all comments attached with postid]
http://localhost:8084/api/v1/posts/comment/{postID}
