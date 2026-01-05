import Image from "next/image";
import styles from "./VideoCard.module.css";
import React from "react";
import Link from "next/link";
import RoundedImage from "../RoundedImage/RoundedImage";
import VideoDetailsInterface from "@/common/interfaces/VedioDetails";
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
  vedioData: VideoDetailsInterface;
  thumbnailStyle?: ThumbNailStyles;
  userIconStyle?: object;
  hideUserIcon?: boolean;
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
      <Link href={`/watch/${props.vedioData.id}`}>
        <Image
          id="video-logo"
          src={props.vedioData.thumbnail}
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
        {!props.hideUserIcon ? (
          <RoundedImage
            imageUrl={props.vedioData.createdBy.profilePic}
            style={props.userIconStyle}
          />
        ) : (
          ""
        )}
        <div className="flex-row gap-0 flex-1 w-full">
          <Link href={`/watch/${props.vedioData.id}`} className="m-0 p-0">
            <p className="font-semibold line-clamp-2 m-0 p-0 leading-tight text-lg">
              {props.vedioData.title}
            </p>
          </Link>

          <Link href={`/channel/${props.vedioData.createdBy.id}`}>
            <small className="m-0 p-0 leading-tight text-sm">
              {props.vedioData.createdBy.name}
            </small>
          </Link>
          <div className="flex-auto gap-1">
            <small className="m-0 p-0 leading-tight text-sm">
              {numeral(props.vedioData.viewCount).format("0.0a").toUpperCase()}{" "}
              view
            </small>
            <small className="m-0 p-0 leading-tight text-sm"> | </small>
            <small className="m-0 p-0 leading-tight text-sm">
              {formatDistanceToNow(new Date(props.vedioData.createdAt), {
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
