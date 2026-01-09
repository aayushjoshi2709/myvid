import React from "react";
import Image from "next/image";

interface RoundedImageProps {
  imageUrl: string;
  id?: string;
  style?: object;
}
const RoundedImage = (props: RoundedImageProps): React.ReactElement => {
  const isDev = process.env.NODE_ENV == "development";
  return (
    <div>
      <Image
        id={props.id || "rounded-image"}
        src={props.imageUrl}
        width={0}
        height={0}
        sizes="100vw"
        style={{
          ...props.style,
        }}
        unoptimized={isDev}
        alt="Image for"
        className={`rounded-full w-14 h-14 object-cover`}
      />
    </div>
  );
};

export default RoundedImage;
