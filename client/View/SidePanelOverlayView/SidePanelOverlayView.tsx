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
    <div>
      <TopNav menuButtonClickAction={toggleSideBar} />
      <div className="flex flex-row">
        {sidePanelVisible ? (
          <SidePanelOverlay
            isVisible={sidePanelVisible}
            setIsVisible={setSidePanelVisible}
          />
        ) : (
          ""
        )}
        {children}
      </div>
    </div>
  );
};

export default SidePanelOverlayView;
