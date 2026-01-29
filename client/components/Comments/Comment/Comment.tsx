import { CommentBody } from "@/common/interfaces/Comment";

const Comment = ({ comment }: { comment: CommentBody }) => {
  return (
    <div id={"comment" + comment.id}>
      <p>{comment.message}</p>
      <p>{comment.author.username}</p>
    </div>
  );
};

export default Comment;
