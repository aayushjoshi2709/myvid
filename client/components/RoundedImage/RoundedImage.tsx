import React from "react";
import Image from "next/image";

interface RoundedImageProps {
  imageUrl: string;
  id?: string;
  style?: object;
}
const RoundedImage = (props: RoundedImageProps): React.ReactElement => {
  return (
    <div>
      <Image
        id={props.id || "rounded-image"}
        src={props.imageUrl}
        width={0}
        height={0}
        sizes="100vw"
        style={{
          width: "4em",
          aspectRatio: "1/1",
          borderRadius: "100%",
          ...props.style,
        }}
        alt="Image for"
        className={`rounded-full`}
      />
    </div>
  );
};

export default RoundedImage;
