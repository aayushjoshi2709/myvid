"use client";

import { MediaPlayer, MediaOutlet } from "@vidstack/react";

interface MediaPlayerProps {
  src: string;
  poster: string;
}

const MediaPlayerWrapper = ({ src, poster }: MediaPlayerProps) => {
  return (
    <MediaPlayer
      src={src}
      poster={poster}
      controls
      preload="metadata"
      className="aspect-video w-full rounded-xl overflow-hidden bg-black"
    >
      <MediaOutlet />
    </MediaPlayer>
  );
};

export default MediaPlayerWrapper;
