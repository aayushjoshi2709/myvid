"use client";

import SidePanel from "@/components/SidePanels/SidePanel/SidePanel";
import TopNav from "@/components/TopNav/TopNav";
import { useState } from "react";

const SidePanelView = ({
  children,
}: {
  children: React.ReactNode;
}): React.ReactElement => {
  const [labelVisible, setLabelVisible] = useState(false);
  function toggleSideBarLabels() {
    setLabelVisible((isVisible) => !isVisible);
  }
  return (
    <div>
      <TopNav menuButtonClickAction={toggleSideBarLabels} />
      <div className="flex flex-row">
        <SidePanel areLabelVisible={labelVisible} />
        {children}
      </div>
    </div>
  );
};

export default SidePanelView;
