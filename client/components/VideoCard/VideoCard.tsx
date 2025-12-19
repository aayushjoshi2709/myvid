import Image from "next/image";
import styles from "./VideoCard.module.css";
import React from "react";
import Link from "next/link";
import RoundedImage from "../RoundedImage/RoundedImage";

interface ThumbNailStyles {
  size?: string;
  width?: string;
  height?: string;
  aspectRatio?: string;
}
interface VideoCardProps {
  id: string;
  thumbnail: string;
  thumbnailStyle?: ThumbNailStyles;
  channelLogo: string;
  title: string;
  channelName: string;
  createdAt: string;
  viewCount: number;
  hideChannelLogo?: boolean;
  videoInfoAlignment?: "vertical" | "horizontal";
}

const VideoCard = (props: VideoCardProps): React.ReactElement => {
  return (
    <div
      className={
        props.videoInfoAlignment === "horizontal"
          ? "flex flex-row gap-2 p-1"
          : "flex flex-col gap-2"
      }
    >
      <Link href={`/watch/${props.id}`}>
        <Image
          id="video-logo"
          src={props.thumbnail}
          width={0}
          height={0}
          sizes={props.thumbnailStyle?.size || "100vw"}
          style={{
            width: props.thumbnailStyle?.width || "100%",
            height: props.thumbnailStyle?.height || "auto",
            aspectRatio: props.thumbnailStyle?.aspectRatio || "16/9",
          }}
          alt="Image for"
          className={styles.videoLogo}
        />
      </Link>
      <div className="mt-2 flex gap-2">
        {!props.hideChannelLogo ? (
          <RoundedImage imageUrl={props.channelLogo} />
        ) : (
          ""
        )}
        <div>
          <Link href={`/watch/${props.id}`}>
            <div>{props.title}</div>
          </Link>

          <small>{props.channelName}</small>
          <div className="flex gap-2">
            <small>{props.viewCount} view</small>
            <small>.</small>
            <small>{props.createdAt}</small>
          </div>
        </div>
      </div>
    </div>
  );
};

export default VideoCard;
