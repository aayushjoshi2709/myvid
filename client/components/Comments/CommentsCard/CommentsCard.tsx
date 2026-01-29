"use client";
import CommentList from "../CommentList/CommentList";
import { AddComment } from "../AddComment/AddComment";

import { useState } from "react";
const CommentsCard = ({ videoId }: { videoId: string }): React.ReactElement => {
  const [fetchComments, setFetchComments] = useState<boolean>(true);
  return (
    <div className=" my-4 rounded-2xl">
      <h2 className="text-xl mb-2">Comments</h2>
      <AddComment videoId={videoId} setFetchComments={setFetchComments} />
      <div className="flex gap-2">
        <CommentList videoId={videoId} fetchComments={fetchComments} setFetchComments={setFetchComments} />
      </div>
    </div>
  );
};

export default CommentsCard;
