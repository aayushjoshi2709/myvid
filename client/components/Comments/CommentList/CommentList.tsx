import { CommentBody } from "@/common/interfaces/Comment";
import Comment from "../Comment/Comment";
const CommentList = ({ comments }: { comments: CommentBody[] }) => {
  return (
    <div>
      {comments.map((comment) => {
        return <Comment key={comment.id} comment={comment} />;
      })}
    </div>
  );
};

export default CommentList;
