"use client";

import { MediaPlayer, MediaProvider } from "@vidstack/react";
import {
  defaultLayoutIcons,
  DefaultVideoLayout,
} from "@vidstack/react/player/layouts/default";
import "@vidstack/react/player/styles/default/theme.css";
import "@vidstack/react/player/styles/default/layouts/video.css";

interface MediaPlayerProps {
  src: string;
  poster: string;
  autoplay?: boolean;
}

const MediaPlayerWrapper = ({
  src,
  poster,
  autoplay = true,
}: MediaPlayerProps) => {
  return (
    <div className="w-full aspect-video bg-black rounded-xl overflow-hidden">
      <MediaPlayer
        src={{
          src: src,
          type: "application/x-mpegurl",
        }}
        poster={poster}
        autoplay={autoplay}
        muted={autoplay}
        playsInline
        className="w-full h-full"
        streamType="on-demand"
        load="eager"
      >
        <MediaProvider />
        <DefaultVideoLayout icons={defaultLayoutIcons} noModal />
      </MediaPlayer>
    </div>
  );
};

export default MediaPlayerWrapper;
