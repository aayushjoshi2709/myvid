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

interface ChannelInfo {
  logo: string;
  name: string;
  id: string;
}

interface VideoCardProps {
  id: string;
  thumbnail: string;
  thumbnailStyle?: ThumbNailStyles;
  channel: ChannelInfo;
  title: string;
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
          <RoundedImage imageUrl={props.channel.logo} />
        ) : (
          ""
        )}
        <div className="flex-row gap-0.2">
          <Link href={`/watch/${props.id}`} className="m-0 p-0">
            <div className="font-semibold line-clamp-2 m-0 p-0">
              {props.title}
            </div>
          </Link>

          <Link href={`/channel/${props.channel.id}`}>
            <small className="m-0">{props.channel.name}</small>
          </Link>
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
