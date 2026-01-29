"use client";
import { AddCommentBody } from "@/common/interfaces/Comment";
import axios from "axios";
import Link from "next/link";
import {
  useState,
  FormEvent,
  useEffect,
  Dispatch,
  SetStateAction,
} from "react";
interface AddCommentParams {
  parentCommentId?: string;
  videoId: string;
  setFetchComments: Dispatch<SetStateAction<boolean>>;
  onClose?: () => void;
}

export const AddComment = ({
  videoId,
  parentCommentId,
  setFetchComments,
  onClose,
}: AddCommentParams) => {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  useEffect(() => {
    fetch("/api/user/isLoggedIn", {
      credentials: "include",
    })
      .then((res) => {
        setIsLoggedIn(res.ok);
      })
      .catch(() => setIsLoggedIn(false));
  }, []);

  const [messageText, setMessageText] = useState<string>("");
  const addComment = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    async function addComment(message: string) {
      setIsLoading(true);

      const body: AddCommentBody = {
        message: message,
      };
      if (parentCommentId) {
        body.parentCommentId = parentCommentId;
      }
      await axios.post(`/api/video/${videoId}/comment`, body);
      setFetchComments(true);
      setIsLoading(false);
    }
    addComment(messageText);
  };
  return (
    <div>
      <div className="w-full flex-row gap-4 mb-4">
        <form onSubmit={(e) => addComment(e)}>
          <input
            type="text"
            onChange={(e) => {
              setMessageText(e.target.value);
            }}
            value={messageText}
            placeholder="Type you comment here..."
            className="w-full p-2 border-b-2 focus:outline-none"
          />
          <div className="mt-2 flex gap-4 justify-end">
            {isLoggedIn ? (
              <>
                <input
                  type="submit"
                  value={isLoading ? "Adding Comment..." : "Add Comment"}
                />
                <input onClick={onClose} type="reset" value="Cancel" />
              </>
            ) : (
              <Link href="/login">
                <input type="submit" value="Sign In To Add Comment" />
              </Link>
            )}
          </div>
        </form>
      </div>
    </div>
  );
};
