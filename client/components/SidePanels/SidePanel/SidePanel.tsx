import SidePanelActions from "../SidePanelActions/SidePanelActions";

interface SidePanelProps {
  areLabelVisible: boolean;
}

const SidePanel = ({ areLabelVisible }: SidePanelProps): React.ReactElement => {
  return (
    <aside className="w-fit bg-white h-full  border-r  border-gray-300">
      <SidePanelActions areLabelVisible={areLabelVisible} />
    </aside>
  );
};

export default SidePanel;
