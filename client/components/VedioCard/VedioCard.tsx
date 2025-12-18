import Image from "next/image";
import styles from "./VedioCard.module.css";
import React from "react";
const VedioCard = (): React.ReactElement => {
  return (
    <div>
      <Image
        id="vedio-logo"
        src={"/hq720.jpg"}
        width={0}
        height={0}
        sizes="100vw"
        style={{ width: "100%", height: "auto" }}
        alt="Image for"
        className={styles.vedioLogo}
      />
      <div className="mt-2 flex gap-2">
        <Image
          id="vedio-logo"
          src={"/hq720.jpg"}
          width={0}
          height={0}
          sizes="100vw"
          style={{ width: "4em", aspectRatio: "1/1", borderRadius: "100%" }}
          alt="Image for"
          className={`${styles.vedioLogo} rounded-full`}
        />
        <div>
          <div>This is the recon tool you need in 2006</div>
          <small>The XSS Rat</small>
          <div className="flex gap-2">
            <small>306 view</small>
            <small>.</small>
            <small>4 days to go</small>
          </div>
        </div>
      </div>
    </div>
  );
};

export default VedioCard;
