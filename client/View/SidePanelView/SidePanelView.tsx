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
    <div>
      <TopNav menuButtonClickAction={toggleSideBarLabels} />
      <div className="flex flex-row">
        <SidePanel areLabelVisible={labelVisible} />
        {children}
      </div>
    </div>
  ) : (
    <>
      <SidePanelOverlayView>{children}</SidePanelOverlayView>
    </>
  );
};

export default SidePanelView;
