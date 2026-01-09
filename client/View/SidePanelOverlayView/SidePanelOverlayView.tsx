"use client";

import SidePanelOverlay from "@/components/SidePanels/SidePanelOverlay/SidePanelOverlay";
import TopNav from "@/components/TopNav/TopNav";
import { useState } from "react";

const SidePanelOverlayView = ({
  children,
}: {
  children: React.ReactNode;
}): React.ReactElement => {
  const [sidePanelVisible, setSidePanelVisible] = useState(false);
  function toggleSideBar() {
    setSidePanelVisible((isVisible) => !isVisible);
  }

  return (
    <div className="h-screen w-screen flex flex-col">
      <TopNav menuButtonClickAction={toggleSideBar} />
      <div className="flex-1 flex min-h-0">
        {sidePanelVisible ? (
          <SidePanelOverlay
            isVisible={sidePanelVisible}
            setIsVisible={setSidePanelVisible}
          />
        ) : (
          ""
        )}
        <div className="overflow-y-scroll w-full h-full">{children}</div>
      </div>
    </div>
  );
};

export default SidePanelOverlayView;
