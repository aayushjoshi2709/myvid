"use client";

import { MediaPlayer, MediaOutlet } from "@vidstack/react";
import "./MediaPlayer.css";
interface MediaPlayerProps {
  src: string;
  poster: string;
}

const MediaPlayerWrapper = ({ src, poster }: MediaPlayerProps) => {
  return (
    <div className="w-full aspect-video bg-black rounded-xl overflow-hidden flex items-center justify-center">
      <MediaPlayer
        src={src}
        poster={poster}
        controls
        preload="metadata"
        className="w-full h-full"
      >
        <MediaOutlet />
      </MediaPlayer>
    </div>
  );
};

export default MediaPlayerWrapper;
