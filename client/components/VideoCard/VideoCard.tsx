import Image from "next/image";
import styles from "./VideoCard.module.css";
import React from "react";
import Link from "next/link";
import RoundedImage from "../RoundedImage/RoundedImage";
import VedioDetailsInterface from "@/common/interfaces/VedioDetails";
import { formatDistanceToNow } from "date-fns";
import numeral from "numeral";

interface ThumbNailStyles {
  size?: string;
  width?: string | number;
  height?: string | number;
  aspectRatio?: string;
  minWidth?: string | number;
}

interface VideoCardProps extends VedioDetailsInterface {
  thumbnailStyle?: ThumbNailStyles;
  channelIconStyle?: object;
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
            width: "100%",
            height: "auto",
            aspectRatio: "16/9",
            ...props.thumbnailStyle,
          }}
          alt="Image for"
          className={styles.videoLogo}
        />
      </Link>
      <div
        className={
          props.videoInfoAlignment === "horizontal"
            ? "mt-0 gap-0 flex flex-row"
            : "mt-2 gap-2 flex flex-row"
        }
      >
        {!props.hideChannelLogo ? (
          <RoundedImage
            imageUrl={props.channel.logo}
            style={props.channelIconStyle}
          />
        ) : (
          ""
        )}
        <div className="flex-row gap-0 flex-1 w-full">
          <Link href={`/watch/${props.id}`} className="m-0 p-0">
            <p className="font-semibold line-clamp-2 m-0 p-0 leading-tight">
              {props.title}
            </p>
          </Link>

          <Link href={`/channel/${props.channel.id}`}>
            <small className="m-0 p-0 leading-tight">
              {props.channel.name}
            </small>
          </Link>
          <div className="flex gap-1">
            <small className="m-0 p-0 leading-tight">
              {numeral(props.viewCount).format("0.0a").toUpperCase()} view
            </small>
            <small className="m-0 p-0 leading-tight">.</small>
            <small className="m-0 p-0 leading-tight">
              {formatDistanceToNow(new Date(props.createdAt), {
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
