import CommentList from "../CommentList/CommentList";
import { CommentBody } from "@/common/interfaces/Comment";
import { AddComment } from "../AddComment/AddComment";
import axios from "axios";
const CommentsCard = async ({
  videoId,
}: {
  videoId: string;
}): Promise<React.ReactElement> => {
  console.log(videoId)
  const response = await axios.get(
    `${process.env.HOST_URL}/api/v1/video/${videoId}/comment`,
  );
  const comments: CommentBody[] = response.data;
  return (
    <div className=" my-4 rounded-2xl">
      <h2 className="text-xl mb-2">Comments</h2>
      <AddComment videoId={videoId} />
      <div className="flex gap-2">
        <CommentList comments={comments} />
      </div>
    </div>
  );
};

export default CommentsCard;
