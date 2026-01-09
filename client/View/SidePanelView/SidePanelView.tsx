"use client";

import SidePanel from "@/components/SidePanels/SidePanel/SidePanel";
import TopNav from "@/components/TopNav/TopNav";
import { useState, useEffect } from "react";
import SidePanelOverlayView from "../SidePanelOverlayView/SidePanelOverlayView";

const SidePanelView = ({
  children,
}: {
  children: React.ReactNode;
}): React.ReactElement => {
  const [labelVisible, setLabelVisible] = useState(false);
  function toggleSideBarLabels() {
    setLabelVisible((isVisible) => !isVisible);
  }
  const [width, setWidth] = useState<number | null>(null);

  useEffect(() => {
    const handleResize = () => setWidth(window.innerWidth);
    handleResize();
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);
  return !width || width >= 500 ? (
    <div className="h-screen w-screen flex flex-col">
      <TopNav menuButtonClickAction={toggleSideBarLabels} />
      <div className="flex-1 flex min-h-0">
        <SidePanel areLabelVisible={labelVisible} />
        <div className="overflow-y-scroll w-full h-full">{children}</div>
      </div>
    </div>
  ) : (
    <>
      <SidePanelOverlayView>{children}</SidePanelOverlayView>
    </>
  );
};

export default SidePanelView;
