import { CommentBody } from "@/common/interfaces/Comment";
import Comment from "../Comment/Comment";
import { Dispatch, SetStateAction, useEffect, useState } from "react";
import axios from "axios";

interface CommentListProps {
  fetchComments: boolean;
  videoId: string;
  parentCommentId?: string | null;
  setFetchComments: Dispatch<SetStateAction<boolean>>;
}

const CommentList = ({
  fetchComments,
  videoId,
  parentCommentId = null,
  setFetchComments,
}: CommentListProps) => {
  const [comments, setComments] = useState<CommentBody[]>([]);
  useEffect(() => {
    if (fetchComments) {
      async function getComments() {
        let path = `/api/video/${videoId}/comment`
        if(parentCommentId){
          path += `?parentCommentId=${parentCommentId}`
        }
        const response = await axios.get(path);
        setComments(response.data.data);
        setFetchComments(false);
      }
      getComments();
    }
  }, [videoId, fetchComments, setFetchComments, parentCommentId]);

  return (
    <li className="list-none w-full">
      {comments.map((comment) => {
        return <Comment key={comment.id} comment={comment} />;
      })}
    </li>
  );
};

export default CommentList;
