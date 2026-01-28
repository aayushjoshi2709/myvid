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
    <div className="relative w-16 h-16 rounded-full overflow-hidden">
      <Image
        src={props.imageUrl}
        alt="Image"
        fill
        className="object-cover"
        unoptimized={isDev}
      />
    </div>
  );
};

export default RoundedImage;
