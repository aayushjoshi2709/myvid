import VideoDetailsInterface from "@/common/interfaces/videoDetails";
import React from "react";
import { formatDistanceToNow } from "date-fns";
import numeral from "numeral";
const DescriptionCard = (props: VideoDetailsInterface): React.ReactElement => {
  return (
    <div className="p-2 bg-gray-100 my-4 rounded-2xl px-4">
      <div className="flex gap-2">
        <p className="text-sm">
          <strong>
            {numeral(props.viewCount).format("0.0a").toUpperCase()} views
          </strong>
        </p>
        <p className="text-sm">
          <strong>
            {formatDistanceToNow(new Date(props.createdAt), {
              addSuffix: false,
            })}
          </strong>
        </p>
      </div>
      <p className="text-md">{props.description}</p>
    </div>
  );
};

export default DescriptionCard;
