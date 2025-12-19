import VedioDetailsInterface from "@/common/interfaces/VedioDetails";
import React from "react";
import { formatDistanceToNow } from "date-fns";
import numeral from "numeral";
const DescriptionCard = (props: VedioDetailsInterface): React.ReactElement => {
  return (
    <div className="p-2 bg-gray-100 my-4 rounded-2xl px-4">
      <div className="flex gap-2">
        <p>
          <strong>
            {numeral(props.viewCount).format("0.0a").toUpperCase()} views
          </strong>
        </p>
        <p>
          <strong>
            {formatDistanceToNow(new Date(props.createdAt), {
              addSuffix: false,
            })}
          </strong>
        </p>
      </div>
      <p>{props.description}</p>
    </div>
  );
};

export default DescriptionCard;
