import Image from "next/image";
import styles from "./VideoCard.module.css";
import React from "react";
import Link from "next/link";
import RoundedImage from "../RoundedImage/RoundedImage";
import VideoDetailsInterface from "@/common/interfaces/VideoDetails";
import { formatDistanceToNow } from "date-fns";
import numeral from "numeral";

interface ThumbNailStyles {
  size?: string;
  width?: string | number;
  height?: string | number;
  aspectRatio?: string;
  minWidth?: string | number;
}

interface VideoCardProps {
  videoData: VideoDetailsInterface;
  thumbnailStyle?: ThumbNailStyles;
  userIconStyle?: object;
  hideUserIcon?: boolean;
  videoInfoAlignment?: "vertical" | "horizontal";
}

const VideoCard = (props: VideoCardProps): React.ReactElement => {
  const isDev = process.env.NODE_ENV === "development";
  return (
    <div
      className={
        props.videoInfoAlignment === "horizontal"
          ? "flex flex-row gap-2 p-1"
          : "flex flex-col gap-2"
      }
    >
      <Link href={`/watch/${props.videoData.id}`}>
        <Image
          id="video-logo"
          src={props.videoData.thumbnailUrl}
          width={0}
          height={0}
          sizes={props.thumbnailStyle?.size || "100vw"}
          style={{
            width: "100%",
            height: "auto",
            aspectRatio: "16/9",
            ...props.thumbnailStyle,
          }}
          alt="Image for"
          className={styles.videoLogo}
          unoptimized={isDev}
        />
      </Link>
      <div
        className={
          props.videoInfoAlignment === "horizontal"
            ? "mt-0 gap-0 flex flex-row"
            : "mt-2 gap-2 flex flex-row"
        }
      >
        {!props.hideUserIcon ? (
          <RoundedImage
            imageUrl={props.videoData.createdBy.profilePicUrl}
            style={props.userIconStyle}
          />
        ) : (
          ""
        )}
        <div className="flex-row gap-0 flex-1 w-full">
          <Link href={`/watch/${props.videoData.id}`} className="m-0 p-0">
            <p className="font-semibold line-clamp-2 m-0 p-0 leading-tight text-lg">
              {props.videoData.title}
            </p>
          </Link>

          <Link href={`/channel/${props.videoData.createdBy.id}`}>
            <small className="m-0 p-0 leading-tight text-sm">
              {props.videoData.createdBy.username}
            </small>
          </Link>
          <div className="flex-auto gap-1">
            <small className="m-0 p-0 leading-tight text-sm">
              {numeral(props.videoData.viewCount).format("0.0a").toUpperCase()}{" "}
              view
            </small>
            <small className="m-0 p-0 leading-tight text-sm"> | </small>
            <small className="m-0 p-0 leading-tight text-sm">
              {formatDistanceToNow(new Date(props.videoData.createdAt), {
                addSuffix: false,
              })}
            </small>
          </div>
        </div>
      </div>
    </div>
  );
};

export default VideoCard;
