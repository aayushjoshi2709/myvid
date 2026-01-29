"use client";
import { CommentBody } from "@/common/interfaces/Comment";
import RoundedImage from "@/components/RoundedImage/RoundedImage";
import ThumbUpAltRounded from "@mui/icons-material/ThumbUpAltRounded";
import ThumbDownAltRounded from "@mui/icons-material/ThumbDownAltRounded";
import { AddComment } from "../AddComment/AddComment";
import { useEffect, useState } from "react";
import CommentList from "../CommentList/CommentList";

const Comment = ({ comment }: { comment: CommentBody }) => {
  const [addReply, setAddReply] = useState<boolean>(false);
  const [showReplies, setShowReplies] = useState<boolean>(false);
  const [fetchComments, setFetchComments] = useState<boolean>(false);
  const [newCommentAdded, setNewCommentAdded] = useState<boolean>(false);

  useEffect(() => {
    if (showReplies) {
      async function updateFetchComments() {
        setFetchComments(true);
      }
      updateFetchComments();
    }
  }, [showReplies]);

  useEffect(() => {
    if (newCommentAdded) {
      async function updateFetchComments() {
        if (!showReplies) {
          setShowReplies(true);
        } else {
          setFetchComments(true);
        }
        setNewCommentAdded(false);
        setAddReply(!addReply);
      }
      updateFetchComments();
    }
  }, [newCommentAdded, showReplies, addReply]);

  return (
    <ul className="flex gap-4 w-full mb-1">
      <RoundedImage
        style={{
          height: "2.5rem",
          width: "2.5rem",
        }}
        imageUrl={comment.author.profilePicUrl ?? ""}
      />
      <div className="justify-center items-center w-fullh-full flex-1 ">
        <p className="text-gray-500 font-semibold m-0 p-0 leading-5">
          @{comment.author.username}
        </p>
        <p className="m-0 p-0 leading-5 mb-2">{comment.message}</p>
        <div className="w-full flex gap-2">
          <span className="m-1">
            <ThumbUpAltRounded />
          </span>
          <span className="m-1">
            <ThumbDownAltRounded />
          </span>
          <button
            className="m-1 mx-2 font-bold"
            onClick={() => setAddReply(!addReply)}
          >
            Reply
          </button>
        </div>
        {addReply ? (
          <AddComment
            setFetchComments={setNewCommentAdded}
            videoId={comment.videoId}
            parentCommentId={comment.id}
            onClose={() => setAddReply(false)}
          />
        ) : (
          ""
        )}
        {showReplies ? (
          <div className="mt-2">
            <CommentList
              videoId={comment.videoId}
              parentCommentId={comment.id}
              fetchComments={fetchComments}
              setFetchComments={setFetchComments}
            />
          </div>
        ) : comment.replyCount > 0 ? (
          <button onClick={() => setShowReplies(true)} className="mt-1">
            Show replies...
          </button>
        ) : (
          ""
        )}
      </div>
    </ul>
  );
};

export default Comment;
